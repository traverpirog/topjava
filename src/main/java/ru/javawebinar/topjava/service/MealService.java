package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getAll(int userId) {
        return new ArrayList<>(repository.getAll(userId));
    }

    public List<Meal> getAllByDateTime(int userId, LocalDate startDate, LocalDate endDate) {
        return new ArrayList<>(repository.getAllByDateTime(userId, startDate, endDate));
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Meal create(Meal meal, int userId) {
        meal.setUserId(userId);
        return repository.save(meal, userId);
    }

    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }
}