package ru.vldsab.subscriber;

import ru.vldsab.message.Message;

import java.util.List;

public interface Subscriber {
     void getMessage(Message message);

     List<Message> allMessages();

}
