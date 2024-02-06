package ru.javawebinar.topjava.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger counter;

    public IdGenerator(int size) {
        counter = new AtomicInteger(size);
    }

    public int getNextUniqueIndex() {
        return counter.getAndIncrement();
    }
}