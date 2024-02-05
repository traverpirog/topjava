package ru.javawebinar.topjava.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static IdGenerator INSTANCE;
    private static AtomicInteger counter;

    private IdGenerator(int size) {
        counter = new AtomicInteger(size);
    }

    public static IdGenerator getInstance() {
        if (INSTANCE == null) {
            synchronized (IdGenerator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new IdGenerator(MealsUtil.getAll().size());
                }
            }
        }
        return INSTANCE;
    }

    public int getNextUniqueIndex() {
        return counter.getAndIncrement();
    }
}