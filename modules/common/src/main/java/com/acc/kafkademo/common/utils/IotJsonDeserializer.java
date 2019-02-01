package com.acc.kafkademo.common.utils;

import com.acc.kafkademo.common.models.IotMessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class IotJsonDeserializer implements Deserializer<IotMessageModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IotSerializer.class);
    private Gson gson;

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        gson = new Gson();
    }

    @Override
    public IotMessageModel deserialize(String s, byte[] bytes) {
        try {
            return gson.fromJson(String.valueOf(bytes), IotMessageModel.class);
        } catch (Exception e) {
            LOGGER.error("Cannot deserialize the object. "+e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {
    }
}
