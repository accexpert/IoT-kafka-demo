package com.acc.kafkademo.common.utils;

import com.acc.kafkademo.common.models.IotMessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class IotJsonSerializer implements Serializer<IotMessageModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IotJsonSerializer.class);
    private Gson gson;

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        gson = new Gson();
    }

    @Override
    public byte[] serialize(String s, IotMessageModel iotMessageModel) {
        try {
            return gson.toJson(iotMessageModel).getBytes();
        } catch (Exception e) {
            LOGGER.error("Cannot serialize object. "+e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {

    }
}
