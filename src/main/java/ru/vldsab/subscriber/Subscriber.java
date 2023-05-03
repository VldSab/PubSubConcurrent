package ru.vldsab.subscriber;

import ru.vldsab.message.Message;
import ru.vldsab.pubsub.PubSubService;

import java.util.List;

public interface Subscriber {
     void getMessage(Message message);

     List<Message> allMessages();

     void subscribe(PubSubService pubSubService, String topic);

}
