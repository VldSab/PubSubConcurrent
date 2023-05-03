package ru.vldsab.subscriber;

import ru.vldsab.message.Message;
import ru.vldsab.pubsub.PubSubService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubscriberImpl implements Subscriber {
    private final List<Message> messages = new ArrayList<>();
    private static int UID = 0;
    private final int curUID;
    public SubscriberImpl() {
        UID++;
        curUID = UID;
    }
    @Override
    public void getMessage(Message message) {
        System.out.println("UID: " + curUID + " В топике " + message.getTopic() + " получено сообщение: " + message.getMessage());
        messages.add(message);
        System.out.println("Всего сообщений " + messages.size());
        System.out.println();
    }

    public void subscribe(PubSubService pubSubService, String topic) {
        pubSubService.addSubscriber(topic, this);
    }

    @Override
    public List<Message> allMessages() {
        return messages;
    }
}
