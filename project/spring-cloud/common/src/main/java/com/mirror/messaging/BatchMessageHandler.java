package com.mirror.messaging;

import java.util.List;

import com.mirror.message.AbstractMessage;

@FunctionalInterface
public interface BatchMessageHandler<T extends AbstractMessage> {

    void processMessages(List<T> messages);

}
