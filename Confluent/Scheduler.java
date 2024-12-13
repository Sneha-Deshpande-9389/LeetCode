package Confluent;
/*
Implement a scheduler in your language of choice. Theyll dive deep into concurrency.
I choose java and they basically had me reinvent timer task using primitives like locks, wait, notify etc.
 https://leetcode.com/discuss/interview-experience/1349287/confluent-senior-software-engineer
 */
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

class TaskScheduler {

    private static final Logger logger = Logger.getLogger(TaskScheduler.class.getName());
    private final ExecutorService executor;

    public TaskScheduler(int threadPoolSize) {
        executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void scheduleTask(Task task, ReentrantLock reentrantLock) {
        executor.execute(() -> {
            try {
                reentrantLock.lock();
                task.run();
            } catch(Exception exception) {
                logger.log(Level.SEVERE, "Execution got interrupted due to this error {0}", exception.getMessage());
            } finally {
                reentrantLock.unlock();
                logger.log(Level.INFO, "Lock released by task {0}", new Object[]{task.taskId});
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}

class Task implements Runnable {

    public static final Logger logger = Logger.getLogger(Task.class.getName());

    public final int taskId;
    public final long executionTime;

    Task(int taskId, long executionTime) {
        this.taskId = taskId;
        this.executionTime = executionTime;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        logger.log(Level.INFO, "{0}- Task {1} started at time {2}", new Object[] {Thread.currentThread().getName(), taskId, System.currentTimeMillis()});

        try {
            Thread.sleep(executionTime);
        } catch (InterruptedException e) {
            logger.log(Level.INFO, "{0} - Task {1} gets interrupted due to this error {2}", new Object[]{Thread.currentThread().getName(), taskId, e.getMessage()});
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        logger.log(Level.INFO, "{0} - Task {1} takes {2} ms", new Object[]{Thread.currentThread().getName(), taskId, duration});
    }
}

public class Scheduler {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler(10);
        ReentrantLock reentrantLock = new ReentrantLock();

        for(int i = 0; i < 20; i++) {
            Task task = new Task(i, i * 1000);
            taskScheduler.scheduleTask(task, reentrantLock);
        }
        taskScheduler.shutdown();

/*
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timedtask is running in thread: " + Thread.currentThread().getName());
            }
        };
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timedtask is running in thread: " + Thread.currentThread().getName());
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timedtask is running in thread: " + Thread.currentThread().getName());
            }
        };
        TimerTask task3 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timedtask is running in thread: " + Thread.currentThread().getName());
            }
        };
        System.out.println("current thread: " + Thread.currentThread().getName());
        timer.schedule(task, 2);
        timer.schedule(task1, 4);
        timer.schedule(task2, 10);
        timer.schedule(task3, 6);

 */

    }
}
