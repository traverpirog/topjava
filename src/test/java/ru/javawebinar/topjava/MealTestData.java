package ru.javawebinar.topjava;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_1 = START_SEQ + 3;
    public static final int MEAL_ID_2 = START_SEQ + 4;
    public static final int MEAL_ID_3 = START_SEQ + 5;
    public static final int MEAL_ID_4 = START_SEQ + 6;
    public static final int MEAL_ID_5 = START_SEQ + 7;
    public static final int MEAL_ID_6 = START_SEQ + 8;
    public static final int MEAL_ID_7 = START_SEQ + 9;
    public static final int MEAL_ID_8 = START_SEQ + 10;
    public static final int MEAL_ID_9 = START_SEQ + 11;
    public static final int MEAL_ID_NOT_FOUND = START_SEQ + 100;
    public static final LocalDate START_LOCAL_DATE = LocalDate.of(2024, 2, 16);
    public static final LocalDate END_LOCAL_DATE = LocalDate.of(2024, 2, 17);
    public static final Meal meal = new Meal(MEAL_ID_1, LocalDateTime.of(2024, 2, 17, 0, 0, 0), "Завтрак", 900);
    public static final Meal meal_2 = new Meal(MEAL_ID_2, LocalDateTime.of(2024, 2, 17, 14, 21, 0), "Обед", 750);
    public static final Meal meal_3 = new Meal(MEAL_ID_3, LocalDateTime.of(2024, 2, 17, 17, 40, 0), "Ужин", 450);
    public static final Meal meal_4 = new Meal(MEAL_ID_4, LocalDateTime.of(2024, 2, 16, 10, 0, 0), "Завтрак", 500);
    public static final Meal meal_5 = new Meal(MEAL_ID_5, LocalDateTime.of(2024, 2, 16, 13, 40, 0), "Обед", 500);
    public static final Meal meal_6 = new Meal(MEAL_ID_6, LocalDateTime.of(2024, 2, 16, 18, 40, 0), "Ужин", 250);
    public static final Meal meal_7 = new Meal(MEAL_ID_7, LocalDateTime.of(2024, 2, 15, 9, 32, 0), "Завтрак", 900);
    public static final Meal meal_8 = new Meal(MEAL_ID_8, LocalDateTime.of(2024, 2, 15, 14, 21, 0), "Обед", 750);
    public static final Meal meal_9 = new Meal(MEAL_ID_9, LocalDateTime.of(2024, 2, 15, 17, 40, 0), "Ужин", 450);

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
        assertThat(actual).usingRecursiveComparison(RecursiveComparisonConfiguration
                .builder()
                .withComparatorForType(Comparator.comparing(o -> o.truncatedTo(ChronoUnit.SECONDS)), LocalDateTime.class)
                .build()
        ).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
