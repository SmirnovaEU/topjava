package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles1(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesPerDate = new HashMap<>();
        for (UserMeal meal : meals) {
            LocalDate currentDate = meal.getDateTime().toLocalDate();
            sumCaloriesPerDate.put(currentDate, sumCaloriesPerDate.getOrDefault(currentDate, 0) + meal.getCalories());
        }

        List<UserMealWithExcess> excessMeals = new ArrayList<>();
        for (UserMeal meal : meals) {
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                excessMeals.add(convertUserMeals(meal, sumCaloriesPerDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return excessMeals;
    }

    //Cycles Optional2
    public static List<UserMealWithExcess> filteredByCycles1(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesPerDate = new HashMap<>();
        Map<LocalDate, UserMealWithExcess> excessMeals = new HashMap<>();
        for (UserMeal meal : meals) {
            LocalDate currentDate = meal.getDateTime().toLocalDate();
            sumCaloriesPerDate.put(currentDate, sumCaloriesPerDate.getOrDefault(currentDate, 0) + meal.getCalories());
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                excessMeals.put(meal.getDateTime().toLocalDate(), convertUserMeals(meal, sumCaloriesPerDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            } else {
                UserMealWithExcess currentMealWithExcess = excessMeals.get(meal.getDateTime().toLocalDate());
                if (currentMealWithExcess == null) continue;
                excessMeals.put(meal.getDateTime().toLocalDate(),
                        new UserMealWithExcess(
                                currentMealWithExcess.getDateTime(),
                                currentMealWithExcess.getDescription(),
                                currentMealWithExcess.getCalories(),
                                sumCaloriesPerDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return new LinkedList<>(excessMeals.values());
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return meals.stream().collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate()))
                .entrySet().stream()
                .collect(Collectors
                        .toMap(e -> e.getValue().stream()
                                        .map(UserMeal::getCalories)
                                        .reduce(0, Integer::sum),
                                e -> e.getValue().stream()
                                        .filter(um -> TimeUtil.isBetweenHalfOpen(um.getDateTime().toLocalTime(), startTime, endTime))
                                        .collect(Collectors.toList()),
                                (list1, list2) -> {
                                    list1.addAll(list2);
                                    return list1;
                                }))
                .entrySet().stream()
                .flatMap(e -> e.getValue().stream()
                        .map(um -> convertUserMeals(um, e.getKey() > caloriesPerDay)))
                .collect(Collectors.toList());
    }

    private static UserMealWithExcess convertUserMeals(UserMeal um, boolean excess) {
        return new UserMealWithExcess(um.getDateTime(), um.getDescription(), um.getCalories(), excess);
    }

    //Simple streams
    public static List<UserMealWithExcess> filteredByStreams1(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> exceedDates = meals.stream()
                .collect(Collectors.toMap(um -> um.getDateTime().toLocalDate(), UserMeal::getCalories, Integer::sum));
        return meals.stream()
                .filter(um -> TimeUtil.isBetweenHalfOpen(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> convertUserMeals(um, exceedDates.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}

