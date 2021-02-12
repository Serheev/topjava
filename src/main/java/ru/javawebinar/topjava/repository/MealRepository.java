package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.Collection;

public interface MealRepository {
    Collection<MealTo> getAll();

    Meal getById(int mealId);

    Meal save(Meal meal);

    void delete(int mealId);
}
