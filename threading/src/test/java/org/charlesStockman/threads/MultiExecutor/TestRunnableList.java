package org.charlesStockman.threads.MultiExecutor;

import org.charlesStockman.threads.MultiExector.RunnableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to Test RunnableList
 */
public class TestRunnableList {

        @Test
        public void verifyjThreadIsInterruptable() throws InterruptedException {

        // Get the list of runnables
        RunnableList runnableList = new RunnableList();
        Runnable runnable = runnableList.get().get(0);

        // Create the thread and start it
        Thread thread = new Thread(runnable);
        thread.start();
        Assertions.assertTrue(thread.isAlive());

        // Let this thread sleep for a period of time and then interrupt it.
        Thread.sleep(2000l);
        thread.interrupt();

        // Wait again to make sure that the thread is interrupted and destory it.
        Assertions.assertFalse(thread.isAlive());
    }

}
