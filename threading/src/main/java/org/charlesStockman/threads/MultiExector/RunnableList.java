package org.charlesStockman.threads.MultiExector;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a list of runnable
 */
public class RunnableList {
    private ArrayList<Runnable> runnables;

    /**
     * Retrieve a list of Runnables which the routine creates if needed.
     *
     * @return A list of runnables
     */
    public List<Runnable> get() {
        if ( runnables == null ) {
            runnables = new ArrayList<>();
            runnables.add( new TaskRunnable());
        }

        return runnables;
    };

    /**
     * A class that provides a runnable for a thread for experimentation
     */
    private class TaskRunnable implements java.lang.Runnable {

        @Override
        public void run() {
            if (Thread.currentThread().isInterrupted() == false) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    System.out.println("This exception happens because sleep was interrupted and it the normal flow.");
                }
            }
        }
    }

}
