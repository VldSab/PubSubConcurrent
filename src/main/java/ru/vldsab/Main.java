package ru.vldsab;

import ru.vldsab.message.Message;
import ru.vldsab.publisher.Publisher;
import ru.vldsab.publisher.PublisherImpl;
import ru.vldsab.pubsub.PubSubService;
import ru.vldsab.subscriber.Subscriber;
import ru.vldsab.subscriber.SubscriberImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final String topic1 = "unique";
        PubSubService pubSub = new PubSubService();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable publish = () -> {
            Publisher publisher = new PublisherImpl();
            for (int i = 0; i < 10; ++i) {
                Message message = new Message("СООБЩЕНИЕ " + i, "unique");
                publisher.publish(message, pubSub);
            }
        };

        Runnable receive = () -> {
            Subscriber subscriber1 = new SubscriberImpl();
            Subscriber subscriber2 = new SubscriberImpl();
            subscriber1.subscribe(pubSub, topic1);
            subscriber2.subscribe(pubSub, topic1);
            for (int i = 0; i < 20; ++i) {
                pubSub.receive();
            }
        };

        executor.execute(publish);
        executor.execute(publish);
        executor.execute(receive);

        executor.shutdown();


    }
}