package ru.vldsab.subscriber;

import ru.vldsab.message.Message;

import java.util.ArrayList;
import java.util.List;

public class SubscriberImpl implements Subscriber{
    private static final List<Message> messages = new ArrayList<>();


    @Override
    public void getMessage(Message message) {
        System.out.println("В топике " + message.getTopic() + " получено сообщение: " + message.getMessage());
        messages.add(message);
        System.out.println("Всего сообщений " + messages.size());
        System.out.println();
    }

    @Override
    public List<Message> allMessages() {
        return messages;
    }
}
