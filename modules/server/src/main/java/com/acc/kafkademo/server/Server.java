package com.acc.kafkademo.server;

import com.acc.kafkademo.server.config.AppConfig;
import org.springframework.boot.SpringApplication;

public class Server {

    public static void main( String[] args ) {
        SpringApplication.run(AppConfig.class);
    }

}
