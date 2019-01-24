package com.acc.kafkademo.common.models;

import java.io.Serializable;

public class IotMessageModel implements Serializable {
    private String clientId;
    private Integer temperature;
    private Integer lowThreshold;
    private Integer highThreshold;

    public IotMessageModel() {
    }

    public IotMessageModel(String clientId) {
        this.clientId = clientId;
    }

    public IotMessageModel(String clientId, Integer temperature, Integer lowThreshold, Integer highThreshold) {
        this.clientId = clientId;
        this.temperature = temperature;
        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getLowThreshold() {
        return lowThreshold;
    }

    public void setLowThreshold(Integer lowThreshold) {
        this.lowThreshold = lowThreshold;
    }

    public Integer getHighThreshold() {
        return highThreshold;
    }

    public void setHighThreshold(Integer highThreshold) {
        this.highThreshold = highThreshold;
    }

    @Override
    public String toString() {
        return "IotMessageModel{" +
                "clientId=" + clientId +
                ", temperature=" + temperature +
                ", lowThreshold=" + lowThreshold +
                ", highThreshold=" + highThreshold +
                '}';
    }
}
