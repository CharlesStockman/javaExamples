package udemy.multithreading.concurrency.performance;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * A class that takes list of Runnables and produces a list of threads and can also start them.
 * 
 * Note when running the main programming the order of threads and print statements being displayed 
 * on the screen will be probably out of order. 
*/
public class MultiExecutor {

    /**
     * Create the threads
     * 
     * @param threadList    A list of threads that are created.  Will be both an input/output parameter.
     * @param Runnable      The code that is executed by the thread
     * @param name          The name thread which is useful when using debugging tools
     */
    public void createThreads(List<Thread> threads, Runnable runnable, String name) {
        
        // Verify parameters
        if ( threads == null ) throw new InvalidParameterException("The threadList cannot be null");
        if ( runnable == null ) throw new InvalidParameterException("Runnable cannot be null -- The thread needs code to execute");
        if ( name == null || name.isBlank()) throw new InvalidParameterException("The thread name cannot null, empty or contain only spaces");

        // Create the threads 
        Thread thread = new Thread(runnable, name);

        // Basic Configuration
        thread.setName(name);
        threads.add(thread);
    }

    /**
     * Launch the threads
     * 
     * @param threads       The list of threads to be started
     * 
     * @exception InterruptedException -- The thread has been interrupted
     */

    public void execute(List<Thread> threads) throws InterruptedException {
        for ( Thread thread : threads ) 
            thread.start();  
    }
    
    /**
     * Starting point of the program that creates the runnables and calls the Thread Start
     * 
     * Put the code and the example in the same file to make it easier to use
     * @throws InterruptedException
     */
    public static void main(String... args) throws InterruptedException {

        MultiExecutor multiExecutor = new MultiExecutor();
        List<Thread> threadList = new ArrayList<>();

        //Data for showing the results of the execute member function
        //Use two different type of initialization since this was a teaching exercise.
        Runnable runnable1 = () -> { for ( int index = 200; index < 205; index++) 
            System.out.println("Name " + Thread.currentThread().getName() + "-- index = " + index); };
        multiExecutor.createThreads(threadList, runnable1, "runnable1");

        Runnable runnable2 = () -> { for ( int index = 300; index < 305; index++)
            System.out.println("Name " + Thread.currentThread().getName() + "-- index = " + index); };
        multiExecutor.createThreads(threadList, runnable2, "runnable2");

        Runnable runnable3 = () -> { for ( int index = 400; index < 405; index++) 
            System.out.println("Name " + Thread.currentThread().getName() + "-- index = " + index); };
        multiExecutor.createThreads(threadList, runnable3, "runnable3");

        multiExecutor.execute(threadList);
        System.out.println("Have executed first set of threads ( runnables 1 through 3)");

        // Data for showing the results of the executeWithSleep member function
        // Initialization by class instead of lambda
        threadList.clear();
        Runnable runnable4 = new DataForThreadSleep(1, 500)::createThreadBody;
        multiExecutor.createThreads(threadList, runnable4, "runnable4");

        Runnable runnable5 = new DataForThreadSleep(3, 600)::createThreadBody;
        multiExecutor.createThreads(threadList, runnable5, "runnable5");

        Runnable runnable6 = new DataForThreadSleep(5, 700)::createThreadBody;
        multiExecutor.createThreads(threadList, runnable6, "runnable6");

        multiExecutor.execute(threadList);
        System.out.println("These threads will sleep at the beginning. ( runnables 4 through 6)");


        // Extra classes to show that threads can be run after the main program has terminated 
        threadList.clear();
        Runnable runnable7 = new DataForThreadSleep(1, 500)::createThreadBody;
        multiExecutor.createThreads(threadList, runnable7, "runnable7");

        Runnable runnable8 = new DataForThreadSleep(3, 600)::createThreadBody;
        multiExecutor.createThreads(threadList, runnable8, "runnable8");

        Runnable runnable9 = new DataForThreadSleep(5, 700)::createThreadBody;
        multiExecutor.createThreads(threadList, runnable9, "runnable9");

        multiExecutor.execute(threadList);

        System.out.println("Main Routine has terminated -- ok because the threads are still running.");


    }
}

/**
 * Generates a routine that show that threads can sleep.  
 */
@AllArgsConstructor
@ToString
class DataForThreadSleep {

    // Number of seconds the thread will sleep for 
    private Integer numberOfSeconds;

    // The first number that will printed out in the thread
    private Integer startIndex;
    
    /**
     * Creates an instance of DataForThreadSleep
     */
    public DataForThreadSleep(int numberOfSeconds, int startIndex) {
        this.numberOfSeconds = numberOfSeconds;
        this.startIndex = startIndex;
    }

    /**
     * The code the thread will executed which shows the sleep command
     * 
     * @exception InterruptedException -- The thread has been interrupted 
     */
    public void createThreadBody()
    {
        try {
            Thread.sleep(1000 * this.numberOfSeconds); 
            for ( Integer index = startIndex; index < this.startIndex + 5; index++) 
                System.out.println("Name " + Thread.currentThread().getName() + "-- index = " + index);
        } catch(InterruptedException exception ) {
            System.out.println(exception.toString());
        }
    }
}