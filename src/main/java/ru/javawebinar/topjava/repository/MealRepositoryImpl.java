package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepositoryImpl implements MealRepository {
    private static volatile MealRepositoryImpl instance;
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private int CALORIES_PER_DAY = 2000;

    {
        for (Meal meal : MealsUtil.MEALS) {
            save(meal);
        }
    }

    private MealRepositoryImpl() {
    }

    public static MealRepositoryImpl getInstance() {
        MealRepositoryImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (MealRepositoryImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MealRepositoryImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Collection<MealTo> getAll() {
        return MealsUtil.filteredByStreams(new ArrayList<Meal>(meals.values()), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        return meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }
}
