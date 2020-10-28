package ru.javawebinar.topjava.service;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class TestLogger extends Stopwatch {
    private static StringBuilder totalLog = new StringBuilder();

    public static StringBuilder getTotalLog() {
        return totalLog;
    }

    @Override
    protected void finished(long nanos, Description description) {
        String testName = description.getMethodName();
        Logger logger = getLogger(description.getTestClass());
        String log = String.format("Test %-30s spent %d ms\n", testName, TimeUnit.NANOSECONDS.toMillis(nanos));
        logger.debug(log);
        totalLog = totalLog.append(log);
    }

    public void writeTotalLog() {
        Logger logger = getLogger(getClass());
        logger.debug(String.valueOf(totalLog));
    }
}
