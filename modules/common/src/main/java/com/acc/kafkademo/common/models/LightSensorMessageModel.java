package com.acc.kafkademo.common.models;

public class LightSensorMessageModel extends IotMessageModel {
    private Integer lightValue;

    public LightSensorMessageModel(String clientId, Integer lightValue) {
        super(clientId);
        this.lightValue = lightValue;
    }

    public Integer getLightValue() {
        return lightValue;
    }

    public void setLightValue(Integer lightValue) {
        this.lightValue = lightValue;
    }

    @Override
    public String toString() {
        return "LightSensorMessageModel{" +
                "clientId=" + getClientId() +
                "lightValue=" + lightValue +
                '}';
    }
}
