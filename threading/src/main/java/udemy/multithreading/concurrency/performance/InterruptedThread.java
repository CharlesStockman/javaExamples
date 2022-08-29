package udemy.multithreading.concurrency.performance;

import java.util.EnumSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

/**
 * For this exercise a thread will be interrupted.  This technique can be used when the application 
 * needs the best answer in a certain amount.  For example sharping an image or calculating a value
 * to make the problem as accurate as possible.
 * 
 */
 public class InterruptedThread {

    /**
     * A enumeration providing the number of milliseconds each thread should execute for.
     */
    enum TIME_THREAD_EXECUTES {
        HALF_SECOND_IN_MILLISECONDS(500), 
        ONE_AND_A_HALF_MILLISECONDS(1000), 
        TWO_AND_A_HALF_MILLISECONDS(1500);

        // Number of milliseconds to use for the test case
        private Integer milliseconds;

        TIME_THREAD_EXECUTES(int milliseconds) {
            this.milliseconds = milliseconds;
        }

        // An toString that will print all the enum values for the TIME_THREAD_EXECUTES
        // An implementation of toString() which is made static so it can used without 
        // initialing the enum
        public static String toStringStatic() {
            StringBuffer buffer = new StringBuffer("");
            
            EnumSet.allOf(TIME_THREAD_EXECUTES.class).forEach( testTimes ->
                buffer.append("\tName = " + testTimes.name() + " and value = " + testTimes.milliseconds + "\n"));

            return buffer.toString();
        }
    }

    /**
     * Creates and Starts the thread and does the initial configuration
     * 
     * @param name -- The name of the task
     */
    private Thread startThread(String name ) {

        // Create Thread
        Thread thread = new Thread(getRunnable());

        // Initializing the Thread
        thread.setName(name);
        //thread.setDaemon(true);
        thread.start();
        return thread;
    }

    /**
     * Creates the body of a thread which will be interrupted to provide an example of how to 
     * interrupt a thread
     * 
     * @return  The code that will be executed by the thread
     */
    public Runnable getRunnable() {
        Runnable runnable = () -> {
            Integer value = 0; 
            while (true==true) {
                if ( Thread.currentThread().isInterrupted() == false ) {
                    value++;
                }
                else {
                    System.out.println(
                        String.format("For the thread with name %s number of increments %d before interruption",
                        Thread.currentThread().getName(), value));
                    break;
                }

            }
        };
        return runnable;

    }

    /**
     * Create the Scheduler that will send the interruption to the thread 
     * 
     * @param thread -- The thread that will be interrupted
     * @param delay  -- milliseconds before the thread is interrupted.
     */
    public void createExperiment(Thread thread, Integer delay) {
        /**
         * The class that will executed when the scheduler reach the milliseconds
         * that was provided to the schedular 
         */
        class MyThreadTask extends TimerTask {

            // The thread that will be interrupted 
            Thread thread = null;

            MyThreadTask(Thread thread ) { this.thread = thread; }

            @Override
            public void run() { thread.interrupt(); } 

        }

        Timer timer = new Timer();
        timer.schedule(new MyThreadTask(thread), delay);
    }
    
    public static void main(String... args) throws InterruptedException {

        InterruptedThread interruptedThread = new InterruptedThread();
        List<Thread> threads = new ArrayList<>();

        System.out.println("The Test Cases are:");
        System.out.println(TIME_THREAD_EXECUTES.toStringStatic());

        EnumSet.allOf(TIME_THREAD_EXECUTES.class).forEach( testTimes -> {

            Thread thread = interruptedThread.startThread(testTimes.name());
            threads.add(thread);
            interruptedThread.createExperiment(thread, testTimes.milliseconds);
            
        });

        // Waits for each thread that was created by this routine to dies and continue on 
        // with execution when all threads are dead.
        threads.forEach( thread -> { 
            try {
                thread.join(); 
            } catch(InterruptedException exception ) { 
                System.out.println(exception.getMessage());
            } 
        });

        System.out.println("Stopping main program since all threads created by this routine have been destroyed");
    }
 }