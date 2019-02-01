package com.acc.kafkademo.common.models;

import java.io.Serializable;

public class IotMessageModel implements Serializable {
    private String clientId;

    public IotMessageModel() {
    }

    public IotMessageModel(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "IotMessageModel{" +
                "clientId='" + clientId + '\'' +
                '}';
    }
}
