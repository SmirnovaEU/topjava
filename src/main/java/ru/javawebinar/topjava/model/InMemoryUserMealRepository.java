package ru.javawebinar.topjava.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserMealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private static AtomicInteger counter = new AtomicInteger(0);


}
