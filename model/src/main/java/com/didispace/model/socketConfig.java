package com.didispace.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class socketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
    	System.out.println("---------------------------------------------------------------------");

        System.out.println("========ServerEndpointExporter类型配置到bean========");

        System.out.println("-----------------------webSocket.java里使用了该bean----------------------------------------------");
        
        return new ServerEndpointExporter();
    }
}