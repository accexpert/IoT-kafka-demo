package com.acc.kafkademo.client.handlers;

import com.acc.kafkademo.common.models.IotMessageModel;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MessageInterceptor implements ProducerInterceptor<Long, IotMessageModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageInterceptor.class);

    @Override
    public ProducerRecord<Long, IotMessageModel> onSend(ProducerRecord<Long, IotMessageModel> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
