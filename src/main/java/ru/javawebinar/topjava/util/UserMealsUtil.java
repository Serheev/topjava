package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> mealWithExcessList = new ArrayList<>();

        Map<String, Integer> calorieCounterList = new HashMap<>();
        String mealKey;

        for (UserMeal meal : meals) {
            mealKey = meal.getDateTime().getYear() + "-" + meal.getDateTime().getMonth().getValue() + "-" + meal.getDateTime().getDayOfMonth();
            calorieCounterList.computeIfPresent(mealKey, (a, b) -> b + meal.getCalories());
            calorieCounterList.putIfAbsent(mealKey, meal.getCalories());
        }

        for (UserMeal meal : meals) {
            mealKey = meal.getDateTime().getYear() + "-" + meal.getDateTime().getMonth().getValue() + "-" + meal.getDateTime().getDayOfMonth();
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                mealWithExcessList.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), caloriesPerDay - calorieCounterList.get(mealKey) < 0));
            }
        }

        return mealWithExcessList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<String, Integer> calorieCounterList = meals.stream()
                .collect(Collectors.toMap(key -> key.getDateTime().getYear() + "-" + key.getDateTime().getMonth().getValue() + "-" + key.getDateTime().getDayOfMonth(), UserMeal::getCalories, (a1, a2) -> a1 + a2.intValue()));

        return meals.stream()
                .filter(e -> TimeUtil.isBetweenHalfOpen(e.getDateTime().toLocalTime(), startTime, endTime))
                .map(e -> new UserMealWithExcess(e.getDateTime(), e.getDescription(), e.getCalories(), caloriesPerDay - calorieCounterList.get(e.getDateTime().getYear() + "-" + e.getDateTime().getMonth().getValue() + "-" + e.getDateTime().getDayOfMonth()) < 0))
                .collect(Collectors.toList());
    }
}
