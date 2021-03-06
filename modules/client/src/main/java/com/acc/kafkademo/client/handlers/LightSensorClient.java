package com.acc.kafkademo.client.handlers;

import com.acc.kafkademo.common.models.IotMessageModel;
import com.acc.kafkademo.common.models.LightSensorMessageModel;
import com.acc.kafkademo.common.models.ThermostatMessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Scope("prototype")
public class LightSensorClient extends BaseClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(LightSensorClient.class);
    private Random rnd = new Random();

    @Autowired
    public LightSensorClient(@Value("${kafka.topic.producer.sun.sensor.clients.name}") String topicName,
                             @Value("${kafka.brokers}") String brokers,
                             @Value("${iot.device.client.sun.sensor.name}") String clientId,
                             ThreadPoolTaskExecutor taskExecutor,
                             @Value("${iot.device.client.sun.sensor.partition.number}") Integer partitionNumber) {
        super(topicName, brokers, clientId, taskExecutor, partitionNumber);
        LOGGER.info(this.getClass().getSimpleName()+" created.");
    }

    @Override
    public void run() {
        LOGGER.info("Thread id: "+getClientId());
        startClient();
        while(isRunning()) {
            try {
                Thread.sleep(1000);
                IotMessageModel message = new LightSensorMessageModel(getClientId(), getCurrentLightLevel());
//                sendMessageToSpecificPartition(message, getPartitionNumber());
                sendMessageRandomPartition(message);
                LOGGER.info(getClientId()+" sending message");
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
                stopClient();
            }
        }
    }

    private int getCurrentLightLevel() {
        return rnd.nextInt(50)+40;
    }
}
