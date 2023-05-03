package ru.vldsab.pubsub;

import ru.vldsab.message.Message;
import ru.vldsab.subscriber.Subscriber;

import java.util.*;

public class PubSubService {
    private final Queue<Message> messages = new LinkedList<>();
    private final Map<String, Set<Subscriber>> topicToSubscribers = new HashMap<>();
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
    public void receive() {
        synchronized (this) {
            while (messages.size() < 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            Message message = messages.poll();
            String topic = message.getTopic();
            Set<Subscriber> topicSubscribers = topicToSubscribers.get(topic);
            topicSubscribers.forEach(
                    sub -> sub.getMessage(message)
            );
            notify();
        }
    }

    public void addSubscriber(String topic, Subscriber subscriber) {
        if (topicToSubscribers.get(topic) == null) {
            Set<Subscriber> curSet = new HashSet<>();
            curSet.add(subscriber);
            topicToSubscribers.put(topic, curSet);
        }
        else
            topicToSubscribers.get(topic).add(subscriber);
    }
}
