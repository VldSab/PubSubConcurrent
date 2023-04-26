package ru.vldsab.publisher;

import ru.vldsab.message.Message;
import ru.vldsab.pubsub.PubSubService;

public interface Publisher {
    void publish(Message message, PubSubService pubSubService);
}
