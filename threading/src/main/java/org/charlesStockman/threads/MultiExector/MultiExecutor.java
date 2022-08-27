package org.charlesStockman.threads.MultiExector;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * The client of this tasks will create a list of Runnable Task and have a 
 * function to exeucte the tasks.
 */
public class MultiExecutor {

    /**
     * Launch the threads
     * 
     * @param runnables     The payload of the thread
     * @param useJoin       (f) -- The useJoin is not used 
     */
    public void execute(Boolean useJoin, Runnable... runnables) {
        Thread thread;
        for ( Runnable runnable : runnables ) {
            try {
                thread = new Thread(runnable);
                thread.start();
                if ( useJoin) thread.join();
            } catch ( InterruptedException exception ) {
                System.out.println(exception.toString());
            }
        }
        
    }
    
    /**
     * Starting point of the program that creates the runnables and calls the Thread Start
     * 
     * Put the code and the example in the same file to make it easier to use
     */
    public static void main(String... args) {

        MultiExecutor multiExecutor = new MultiExecutor();

        //Data for showing the results of the execute member function
        //Use two different type of initialization since this was a teaching exercise.
        Runnable runnable1 = () -> { for ( int index = 200; index < 205; index++) System.out.println("index = " + index); };
        Runnable runnable2 = () -> { for ( int index = 300; index < 305; index++) System.out.println("index = " + index); };
        Runnable runnable3 = () -> { for ( int index = 400; index < 405; index++) System.out.println("index = " + index); };
        multiExecutor.execute(true, runnable1, runnable2, runnable3 );
        System.out.println("Have completed all inital thread ( runnables 1 through 3)");

        // Data for showing the results of the executeWithSleep member function
        // Intializatio by class instead of lambda
        Runnable runnable4 = new DataForThreadSleep(1, 500)::createThreadBody;
        Runnable runnable5 = new DataForThreadSleep(3, 600)::createThreadBody;
        Runnable runnable6 = new DataForThreadSleep(5, 700)::createThreadBody;
        multiExecutor.execute(true, runnable4, runnable5, runnable6);

        // Data for showing the results of the exe0cuteWithSleep member function
        // Extra classes to show that threads can be run after the main program has terminated 
        Runnable runnable7 = new DataForThreadSleep(1, 500)::createThreadBody;
        Runnable runnable8 = new DataForThreadSleep(3, 600)::createThreadBody;
        Runnable runnable9 = new DataForThreadSleep(5, 700)::createThreadBody;
        multiExecutor.execute(false, runnable7, runnable8, runnable9);

        System.out.println("Main Routine has terminated.");


    }
}

/**
 * Generates a routine that show that threads can sleep.  
 */
@AllArgsConstructor
@ToString
class DataForThreadSleep {

    // Number of secoonds the thread will sleep for 
    private Integer numberOfSeconds;

    // The first number that will printed out in the thread
    private Integer startIndex;
    
    /**
     * Creates an instance of DataForThreadSleelp
     */
    public DataForThreadSleep(int numberOfSeconds, int startIndex) {
        this.numberOfSeconds = numberOfSeconds;
        this.startIndex = startIndex;

        System.out.println(String.format("The class %s has been intialized to %s", getClass().getName(),toString() ));
    }

    /**
     * The code the thread will execute which shows the sleep command
     * 
     * @exception InterupredException -- The thread has been interupted 
     */
    public void createThreadBody()
    {
        try {
            Thread.sleep(1000 * this.numberOfSeconds); 
            for ( Integer index = startIndex; index < this.startIndex + 5; index++) 
                System.out.println("index = " + index);
        } catch(InterruptedException exception ) {
            System.out.println(exception.toString());
        }
    }
}