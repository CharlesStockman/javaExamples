package org.charlesStockman.threads;

/**
 * A class for functions to help with Threading
 */
public class Utils {
    /**
     * Finds a thread with the specific name
     * <p>
     * Programming Note that findAny returns an optional.
     *
     * @param threadName The name of the thread which cannot be null or empty.
     * @return The first thread with the name.
     */
    public static Thread findThreadByName(String threadName) {
        Thread thread =
                Thread.getAllStackTraces().keySet().stream().
                        filter((Thread aThread) -> aThread.getName().equals(threadName)).
                        findAny().orElseGet(null);
        return thread;
    }
}
