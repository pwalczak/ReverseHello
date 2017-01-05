package com.company;
import org.apache.log4j.Logger;

//    Write a program called ReverseHello.java that creates a thread (let's call it Thread 1). Thread 1 creates
//            nother thread (Thread 2); Thread 2 creates Thread 3; and so on, up to Thread 50.
//    Each thread should print "Hello from Thread <num>!", but you should structure your program such
//    that the threads print their greetings in reverse order.



public class Main implements Runnable {

    private static int currentNumberOfThreads = 0;
    public static final int MAXIMUM_NUMBER_OF_THREADS = 50;
    private int threadNumber = 0;

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
	// write your code here
        createThreadSequence();
    }

    public static Thread createAnotherThread()
    {
        Main m = new Main();
        m.threadNumber = ++currentNumberOfThreads;
        return new Thread(m);
    }

    public static void createThreadSequence()
    {
        if(currentNumberOfThreads < MAXIMUM_NUMBER_OF_THREADS) {
            Thread myThread = createAnotherThread();
            myThread.start();
            try {
                myThread.join();
            } catch (InterruptedException e) {
                logger.info(e);
            }
        }
    }


    public void run() {
        createThreadSequence();
        logger.error("Hello from Thread " + threadNumber);
    }
}
