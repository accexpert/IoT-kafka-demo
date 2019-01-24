package com.acc.kafkademo.client;

import com.acc.kafkademo.client.config.AppConfig;
import com.acc.kafkademo.client.handlers.BaseClient;
import com.acc.kafkademo.client.handlers.LightSensorClient;
import com.acc.kafkademo.client.handlers.ThermostatClient;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;

import java.io.IOException;

public class Client {

    public static void main( String[] args ) {
        SpringApplication application = new SpringApplication(AppConfig.class);
        ApplicationContext context = application.run();
        BaseClient baseClient = context.getBean(ThermostatClient.class);
        TaskExecutor taskExecutor = context.getBean(TaskExecutor.class);
        taskExecutor.execute(baseClient);
        BaseClient baseClient1 = context.getBean(LightSensorClient.class);
        taskExecutor.execute(baseClient1);
//        BaseClient baseClient2 = context.getBean(ThermostatClient.class);
//        taskExecutor.execute(baseClient2);
    }

}
