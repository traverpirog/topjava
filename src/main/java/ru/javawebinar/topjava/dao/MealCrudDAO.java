package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealCrudDAO extends CrudDAO<Meal> {
    List<MealTo> getAllMealTo();
}
