package ru.vldsab.pubsub;

import ru.vldsab.message.Message;
import ru.vldsab.subscriber.Subscriber;

import java.util.*;

public class PubSubService {
    private final Queue<Message> messages = new LinkedList<>();

    public void publish(Message message) {
        synchronized (this) {
            while (messages.size() >= 5) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            messages.offer(message);
            System.out.println("Message added");
            System.out.println("Queue size " + messages.size());
            System.out.println();
            notify();
        }
    }

    // TODO devide subscribers by topics
    public void receive(Subscriber subscriber) {
        synchronized (this) {
            while (messages.size() < 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            Message message = messages.poll();
            subscriber.getMessage(message);
            notify();
        }
    }

}
