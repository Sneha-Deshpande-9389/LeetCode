package JavaBasicsJava8;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


record Item(String producer, int id) {}
class ItemQueue {

    private final BlockingQueue<Item> queue;

    public ItemQueue() {
        queue = new LinkedBlockingQueue<>(5);
    }

    public void put(Item item) throws InterruptedException {
        queue.put(item);
    }

    public Item take() throws InterruptedException {
        return queue.take();
    }
}

@Data
class Producer {

    private static final SecureRandom RANDOM = new SecureRandom();
    private final ItemQueue queue;
    private final String name;
    private int itemId;

    public Producer(String name, ItemQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void produce() throws InterruptedException {

        var item = new Item(name, itemId++);
        queue.put(item);
        Thread.sleep(RANDOM.nextInt(2000));
    }
}

@Slf4j
class Consumer {

    private final ItemQueue queue;
    private final String name;

    public Consumer(String name, ItemQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void consume() throws InterruptedException {
        var item = queue.take();
        System.out.println("Consumer [" + name +"] consume item ["  + item.id() +"] produced by [" + item.producer() + "]");

    }
}

public class ProducerConsumer {


    public static void main(String[] args) {

        var queue = new ItemQueue();

        var executorService = Executors.newFixedThreadPool(5);
        for (var i = 0; i < 2; i++) {

            final var producer = new Producer("Producer_" + i, queue);
            executorService.submit(() -> {
                while (true) {
                    producer.produce();
                }
            });
        }

        for (var i = 0; i < 3; i++) {
            final var consumer = new Consumer("Consumer_" + i, queue);
            executorService.submit(() -> {
                while (true) {
                    consumer.consume();
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            System.out.println("Error waiting for ExecutorService shutdown");
        }
    }
}
