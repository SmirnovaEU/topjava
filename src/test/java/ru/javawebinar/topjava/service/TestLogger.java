package ru.javawebinar.topjava.service;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class TestLogger extends Stopwatch {
    private static Logger logger;

    public static void logInfo(Description description, long nanos) {
        String testName = description.getMethodName();
        logger = getLogger(description.getTestClass());
        logger.debug(String.format("Test %s spent %d mcs", testName, nanos/1000000));
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, nanos);
    }
}
