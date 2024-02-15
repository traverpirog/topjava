package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        Meal foundedMeal = repository.get(meal.getId());
        if (foundedMeal == null || userId != foundedMeal.getUserId()) {
            return null;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(foundedMeal.getId(), (id, oldMeal) -> {
            meal.setUserId(oldMeal.getUserId());
            return meal;
        });
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Meal meal = repository.get(id);
        if (meal == null || userId != meal.getUserId()) {
            return false;
        }
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = repository.get(id);
        if (meal == null || userId != meal.getUserId()) {
            return null;
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllByDateTime(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getAllByDateTime");
        return repository.values().stream().filter(meal -> meal.getUserId() == userId &&
                        DateTimeUtil.isBetweenHalfOpen(meal.getDate(), startDate, endDate) &&
                        DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime)
                )
                .sorted(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}

