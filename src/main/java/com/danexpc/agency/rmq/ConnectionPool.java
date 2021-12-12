package com.danexpc.agency.rmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionPool {

    private static final ConnectionFactory factory;

    static {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("test");
        factory.setPassword("test");
    }

    public static ConnectionFactory getInstance() {
        return factory;
    }

    public Connection getConnection() throws IOException, TimeoutException {
        return factory.newConnection();
    }
}
