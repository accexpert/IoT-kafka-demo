package com.acc.kafkademo.common.models;

public class ThermostatMessageModel extends IotMessageModel {
    private Integer temperature;
    private Integer lowThreshold;
    private Integer highThreshold;

    public ThermostatMessageModel(String clientId, Integer temperature, Integer lowThreshold, Integer highThreshold) {
        super(clientId);
        this.temperature = temperature;
        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
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
        return "ThermostatMessageModel{" +
                "clientId=" + getClientId() +
                "temperature=" + temperature +
                ", lowThreshold=" + lowThreshold +
                ", highThreshold=" + highThreshold +
                '}';
    }
}
