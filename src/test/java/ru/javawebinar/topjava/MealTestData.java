package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int MEAL_B_ID = START_SEQ + 2;
    public static final int MEAL_L_ID = START_SEQ + 3;
    public static final int MEAL_S_ID = START_SEQ + 4;
    public static final int MEAL_A_ID = START_SEQ + 5;

    public static final LocalDate START_DATE = LocalDate.of(2020, Month.OCTOBER, 1);
    public static final LocalDate END_DATE = LocalDate.of(2020, Month.OCTOBER, 1);

    public static final User user = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);

    public static final Meal meal_b_user = new Meal(MEAL_B_ID, LocalDateTime.of(2020, Month.OCTOBER, 1, 10, 0), "Завтрак", 500);
    public static final Meal meal_l_user = new Meal(MEAL_L_ID, LocalDateTime.of(2020, Month.OCTOBER, 1, 15, 0), "Обед", 1000);
    public static final Meal meal_s_user = new Meal(MEAL_S_ID, LocalDateTime.of(2020, Month.OCTOBER, 1, 20, 0), "Ужин", 500);

    public static final Meal meal_b_admin = new Meal(MEAL_A_ID, LocalDateTime.of(2020, Month.OCTOBER, 1, 10, 0), "Завтрак", 1000);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.JANUARY,1,1,0), "new", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal_b_user);
        updated.setDateTime(LocalDateTime.now());
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, (Meal) Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
