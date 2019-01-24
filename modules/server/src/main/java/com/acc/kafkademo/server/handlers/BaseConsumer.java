package com.acc.kafkademo.server.handlers;

import com.acc.kafkademo.common.models.IotMessageModel;
import com.acc.kafkademo.common.utils.IotDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

public abstract class BaseConsumer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseConsumer.class);
    private boolean isRunning;
    private String topicName;
    private String groupName;
    private String brokers;
    private KafkaConsumer<String, IotMessageModel> consumer;

    public BaseConsumer(String topicName, String groupName, String brokers, String maxNumberOfMessageToRead,
                        String enableAutocommit, String offsetReset) {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        props.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxNumberOfMessageToRead);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IotDeserializer.class.getName());
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutocommit);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);
        LOGGER.info(offsetReset);
        consumer = new KafkaConsumer(props);
        consumer.subscribe(Collections.singletonList(topicName));
        this.topicName = topicName;
        this.brokers = brokers;
        this.groupName = groupName;
    }

    public final void startConsumer() {
        LOGGER.info(this.groupName+" is starting");
        isRunning = true;
    }

    public final void stopConsumer() {
        LOGGER.info(this.groupName+" is stopping");
        consumer.unsubscribe();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    protected ConsumerRecords<String, IotMessageModel> getRecord() {
        ConsumerRecords<String, IotMessageModel> records = consumer.poll(100);
//        LOGGER.info("Number of messages: "+records.count());

        return records;
    }
}
