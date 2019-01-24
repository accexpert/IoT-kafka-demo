package com.acc.kafkademo.server.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConnection.class);
    private List<Runnable> consumerList;

    public KafkaConnection() {
        consumerList = new ArrayList<>();
        LOGGER.info(this.getClass().getSimpleName()+" created.");
    }

    @PreDestroy
    public void destroy() {

    }
}
