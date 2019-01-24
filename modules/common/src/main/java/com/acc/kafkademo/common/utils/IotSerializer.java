package com.acc.kafkademo.common.utils;

import com.acc.kafkademo.common.models.IotMessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class IotSerializer implements Serializer<IotMessageModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IotSerializer.class);

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, IotMessageModel iotMessageModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsBytes(iotMessageModel);
        } catch (JsonProcessingException e) {
            LOGGER.error("Cannot serialize object. "+e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {

    }
}
