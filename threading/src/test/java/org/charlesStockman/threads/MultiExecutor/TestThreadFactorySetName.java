package org.charlesStockman.threads.MultiExecutor;

import org.charlesStockman.threads.MultiExector.ThreadFactorySetName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestThreadFactorySetName {

    private ThreadFactorySetName factory = new ThreadFactorySetName();

    @Test
    public void setThreadName() {

        String firstTaskName = "ThreadFactorySetName_0";
        String secondTaskName = "ThreadFactorySetName_1";

        Runnable runnable = () -> System.out.println("Executing task");

        Thread firstThread = factory.newThread(runnable);
        Thread secondThread = factory.newThread(runnable);

        Assertions.assertEquals(firstTaskName, firstThread.getName());
        Assertions.assertEquals(secondTaskName, secondThread.getName());
    }
}
