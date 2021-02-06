package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealStorage implements Storage<Meal> {
    List<Meal> items = MealsUtil.initMeals();

    @Override
    public void add(Meal meal) {
        //TODO: meal ID should be private uniq counter. Using List.size() is totally wrong!
        meal.setId(items.size());
        items.add(meal);
    }

    @Override
    public void update(Meal meal) {
        int itemIndex = items.indexOf(get(meal.getId()));
        if (itemIndex != -1) {
            items.set(itemIndex, meal);
        }
    }

    @Override
    public void delete(int id) {
        items.remove(get(id));
    }

    @Override
    public Meal get(int id) {
        for (Meal item : items) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    @Override
    public List<Meal> getFiltered(Predicate<Meal> p) {
        return items.stream().filter(p).collect(Collectors.toList());
    }
}
