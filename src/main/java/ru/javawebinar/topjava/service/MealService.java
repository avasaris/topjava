package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId, meal -> true);
    }

    public void update(Meal meal, int mealId, int userId) {
        if (get(mealId, userId) == null) {
            throw new IllegalArgumentException("Meal id=" + mealId + " did not belongs to User id=" + userId);
        }
        meal.setUserId(userId);
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return repository.getAll(userId, meal -> DateTimeUtil.isBetweenDays(meal.getDate(), startDate, endDate)
                && DateTimeUtil.isBetweenTimes(meal.getTime(), startTime, endTime));
    }
}