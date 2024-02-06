package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealCrud implements Crud<Meal> {
    private final ConcurrentMap<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    {
        MealsUtil.getAll().forEach(this::create);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public Meal create(Meal meal) {
        Meal newMeal = new Meal(counter.getAndIncrement(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        meals.put(newMeal.getId(), newMeal);
        return newMeal;
    }

    @Override
    public void update(Meal meal) {
        meals.replace(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }
}
