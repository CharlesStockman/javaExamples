package org.charlesStockman.threads.MultiExector;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A specialization of the ThreadFactory that will set the name
 */
public class ThreadFactorySetName implements ThreadFactory {

    AtomicLong differentiator = new AtomicLong(0);

    String className = this.getClass().getSimpleName();

    private static ThreadFactorySetName factory;
    static {
        factory = new ThreadFactorySetName();
    }

    /**
     * Returns an instance of the factory that will create a thread and set the name.
     *
     * @return A factory
     */
    public static ThreadFactorySetName getInstance() {
        return factory;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = Executors.defaultThreadFactory().newThread(runnable);
        thread.setName(generateUniqueNameWithDifferentiator());
        return thread;
    }

    /**
     * Creates a generic name ( the class name ) with the differentiator
     * ( a number ) that make name unique
     *
     * @return A string that is a unique name
     */
    private String generateUniqueNameWithDifferentiator() {
        String name = className + "_" + differentiator.getAndIncrement();
        return name;
    }
}

