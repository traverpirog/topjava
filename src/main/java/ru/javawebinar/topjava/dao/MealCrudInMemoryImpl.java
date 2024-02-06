package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.IdGenerator;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MealCrudInMemoryImpl implements MealCrudDAO {
    private static final Map<Integer, Meal> meals = MealsUtil.getAll();
    public static final IdGenerator idGenerator = new IdGenerator(meals.size() + 1);

    public MealCrudInMemoryImpl() {
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public List<MealTo> getAllMealTo() {
        return MealsUtil.getMealToList(getAll());
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public Meal create(Meal meal) {
        Meal newMeal = new Meal(idGenerator.getNextUniqueIndex(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        meals.put(newMeal.getId(), newMeal);
        return newMeal;
    }

    @Override
    public void update(Meal meal) {
        Meal foundedMeal = get(meal.getId());
        foundedMeal.setDateTime(meal.getDateTime());
        foundedMeal.setDescription(meal.getDescription());
        foundedMeal.setCalories(meal.getCalories());
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }
}
