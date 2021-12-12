package com.danexpc.agency.rmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
public class NotificationsSender {

    private final static String QUEUE_NAME = "notifications";

    private static final ConnectionFactory factory = ConnectionPool.getInstance();

    public static void send(String message) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            log.info(" [x] Sent '" + message + "'");

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
