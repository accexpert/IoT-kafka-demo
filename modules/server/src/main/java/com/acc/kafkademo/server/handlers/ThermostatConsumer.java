package com.acc.kafkademo.server.handlers;

import com.acc.kafkademo.common.models.IotMessageModel;
import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
@Scope("prototype")
public class ThermostatConsumer extends BaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThermostatConsumer.class);

    @Autowired
    public ThermostatConsumer(@Value("${kafka.topic.consume.clients.name}") String topicName,
                              @Value("${kafka.consumer.group.name.thermostats}") String groupName,
                              @Value("${kafka.brokers}") String brokers,
                              @Value("${kafka.max.number.of.messages.to.read}") String maxNumberOfMessageToRead,
                              @Value("${kafka.enable.autocommit}") String enableAutocommit,
                              @Value("${kafka.auto.offset.reset}") String offsetReset) {
        super(topicName, groupName, brokers, maxNumberOfMessageToRead, enableAutocommit, offsetReset);
        LOGGER.info(this.getClass().getSimpleName()+" created.");
    }

    @Override
    public void run() {
        startConsumer();
        while(isRunning()) {
            readAndCommitSync();
        }
    }

    private void readAndCommitSync() {
        try {
            ConsumerRecords<String, IotMessageModel> records = getRecord();
            if (null!=records) {
                Iterator it = records.iterator();
                while(it.hasNext()) {
                    ConsumerRecord<String, IotMessageModel> record= (ConsumerRecord<String, IotMessageModel>)it.next();
                    LOGGER.info(record.toString());
                }
                getConsumer().commitSync();
            }
        } catch (CommitFailedException ex) {
            LOGGER.error("Committing the offsets failed");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            stopConsumer();
        }
    }

    private void readAndCommitAsync() {
        try {
            ConsumerRecords<String, IotMessageModel> records = getRecord();
            if (null!=records) {
                Iterator it = records.iterator();
                while(it.hasNext()) {
                    ConsumerRecord<String, IotMessageModel> record= (ConsumerRecord<String, IotMessageModel>)it.next();
                    LOGGER.info(record.toString());
                }
                getConsumer().commitAsync((Map<TopicPartition, OffsetAndMetadata> offsets, Exception ex)->{
                    if(null != ex) {
                        LOGGER.error("There was an error committing the offset. Handle flow.");
                    } else {
                        LOGGER.info("Offset committed successfully.");
                    }
                });
            }
        } catch (CommitFailedException ex) {
            LOGGER.error("Committing the offsets failed. Handle flow.");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            stopConsumer();
        }
    }
}
