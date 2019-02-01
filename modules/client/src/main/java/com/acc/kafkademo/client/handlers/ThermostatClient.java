package com.acc.kafkademo.client.handlers;

import com.acc.kafkademo.common.models.IotMessageModel;
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
public class ThermostatClient extends BaseClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThermostatClient.class);
    private int counter;
    private Random rnd = new Random();

    @Autowired
    public ThermostatClient(@Value("${kafka.topic.producer.thermostat.clients.name}") String topicName,
                            @Value("${kafka.brokers}") String brokers,
                            @Value("${iot.device.client.thermostat.name}") String clientId,
                            ThreadPoolTaskExecutor taskExecutor,
                            @Value("${iot.device.client.thermostat.partition.number}") Integer partitionNumber) {
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
                IotMessageModel message = new ThermostatMessageModel(getClientId(), getCurrentTemperature(), 21, 24);
//                sendMessageToSpecificPartition(message, getPartitionNumber());
                sendMessageRandomPartition(message);
                LOGGER.info(getClientId()+" sending message");
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
                stopClient();
            }
        }
    }

    /**
     * Return every 5 second a temperature spike
     * @return
     */
    private int getCurrentTemperature() {
        if( (++counter > 4) ) {
            counter = 0;
            return rnd.nextInt(10)+30;
        }
        return 23;
    }

}
