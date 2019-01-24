package com.acc.kafkademo.common.utils;

import com.acc.kafkademo.common.models.IotMessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class IotDeserializer implements Deserializer<IotMessageModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IotSerializer.class);

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public IotMessageModel deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(bytes, IotMessageModel.class);
        } catch (Exception e) {
            LOGGER.error("Cannot deserialize the object. "+e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {
    }
}
