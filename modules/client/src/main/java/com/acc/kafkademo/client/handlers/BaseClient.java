package com.acc.kafkademo.client.handlers;

import com.acc.kafkademo.common.models.IotMessageModel;
import com.acc.kafkademo.common.utils.IotSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public abstract class BaseClient implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);
    private boolean isRunning;
    private String topicName;
    private String brokers;
    private KafkaProducer producer;
    private String clientId;
    protected ThreadPoolTaskExecutor taskExecutor;
    private Integer partitionNumber;
    private static int i = 1;

    public BaseClient(String topicName, String brokers, String clientId, ThreadPoolTaskExecutor taskExecutor,
                      Integer partitionNumber) {
        this.topicName = topicName;
        this.brokers = brokers;
        this.clientId = clientId+(i++);
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IotSerializer.class.getName());
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, this.clientId);
        props.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, PartitionHandler.class.getName());
        props.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MessageInterceptor.class.getName());
        producer = new KafkaProducer(props);
        this.topicName = topicName;
        this.brokers = brokers;
        this.taskExecutor = taskExecutor;
        this.partitionNumber = partitionNumber;
    }

    public void startClient() {
        LOGGER.info(this.clientId+" is starting");
        isRunning = true;
    }

    public void stopClient() {
        LOGGER.info(this.clientId+" is stoppping");
        isRunning = false;
//        producer.close();
    }

    public Integer getPartitionNumber() {
        return partitionNumber;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getClientId() {
        return clientId;
    }

    protected void sendMessageToPartition(IotMessageModel message, Integer partitionId) {
        ProducerRecord<Integer, IotMessageModel> record = new ProducerRecord(topicName, partitionId, message);
        try {
            RecordMetadata metadata = (RecordMetadata)producer.send(record).get();
            LOGGER.info("Record sent to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage());
        }
    }

    protected void sendMessage(IotMessageModel message) {
        ProducerRecord<Integer, IotMessageModel> record = new ProducerRecord(topicName, message);
        try {
            RecordMetadata metadata = (RecordMetadata)producer.send(record).get();
            LOGGER.info("Record sent to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
