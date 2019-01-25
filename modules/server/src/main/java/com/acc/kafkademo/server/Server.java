package com.acc.kafkademo.server;

import com.acc.kafkademo.server.config.AppConfig;
import com.acc.kafkademo.server.handlers.BaseConsumer;
import com.acc.kafkademo.server.handlers.ThermostatConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;

public class Server {

    public static void main( String[] args ) {
        SpringApplication application = new SpringApplication(AppConfig.class);
        ApplicationContext context = application.run();
        TaskExecutor taskExecutor = context.getBean(TaskExecutor.class);
        BaseConsumer baseConsumer = context.getBean(ThermostatConsumer.class);
        taskExecutor.execute(baseConsumer);
        BaseConsumer baseConsumer1 = context.getBean(ThermostatConsumer.class);
        taskExecutor.execute(baseConsumer1);
    }

}
