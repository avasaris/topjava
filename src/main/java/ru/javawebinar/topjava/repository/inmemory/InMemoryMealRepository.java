package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Comparator<Meal> MEAL_COMPARATOR = Comparator.comparing(Meal::getDateTime);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals1.forEach(meal -> save(meal, 1));
        MealsUtil.meals2.forEach(meal -> save(meal, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.computeIfAbsent(userId, uid -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (get(id, userId) != null) {
            return repository.get(userId).remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId).getOrDefault(id, null);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getAll(userId, meal -> true);
    }

    @Override
    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return getAll(userId,
                meal -> DateTimeUtil.isBetweenDays(meal.getDate(), startDate, endDate)
                        && DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime));
    }

    private Collection<Meal> getAll(int userId, Predicate<Meal> predicate) {
        return repository.get(userId).values()
                .stream()
                .filter(predicate)
                .sorted(MEAL_COMPARATOR)
                .collect(Collectors.toList());
    }
}

