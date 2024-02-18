package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_1 = START_SEQ + 3;
    public static final int MEAL_ID_2 = START_SEQ + 4;
    public static final int MEAL_ID_3 = START_SEQ + 5;
    public static final int MEAL_ID_4 = START_SEQ + 6;
    public static final int MEAL_ID_5 = START_SEQ + 7;
    public static final int MEAL_ID_6 = START_SEQ + 8;
    public static final int MEAL_ID_NOT_FOUND = START_SEQ + 100;
    public static final LocalDate LOCAL_DATE = LocalDate.parse("2024-02-17");
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2024-02-17T09:32:00");
    public static final Meal meal = new Meal(MEAL_ID_1, LocalDateTime.parse("2024-02-17T09:32:00"), "Завтрак", 900);
    public static final Meal meal_2 = new Meal(MEAL_ID_2, LocalDateTime.parse("2024-02-17T14:21:00"), "Обед", 750);
    public static final Meal meal_3 = new Meal(MEAL_ID_3, LocalDateTime.parse("2024-02-17T17:40:00"), "Ужин", 450);
    public static final Meal meal_4 = new Meal(MEAL_ID_4, LocalDateTime.parse("2024-02-16T10:00:00"), "Завтрак", 500);
    public static final Meal meal_5 = new Meal(MEAL_ID_5, LocalDateTime.parse("2024-02-16T13:40:00"), "Обед", 500);
    public static final Meal meal_6 = new Meal(MEAL_ID_6, LocalDateTime.parse("2024-02-16T18:40:00"), "Ужин", 250);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "new meal", 275);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal);
        updated.setDateTime(LocalDateTime.now());
        updated.setDescription("Плотный завтрак");
        updated.setCalories(750);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
