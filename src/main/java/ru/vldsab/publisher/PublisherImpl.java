package ru.vldsab.publisher;

import ru.vldsab.message.Message;
import ru.vldsab.pubsub.PubSubService;

public class PublisherImpl implements Publisher {
    @Override
    public void publish(Message message, PubSubService pubSubService) {
        pubSubService.publish(message);
    }

}
