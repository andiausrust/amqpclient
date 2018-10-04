package com.andi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static String QUEUE_NAME = "hallo";

    public static void main(String[] args) throws IOException, TimeoutException {

        // Connect to connection factory
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        // create a new connection
        Connection connection = factory.newConnection();

        // use channel to communicate with rabbitmq
        Channel channel = connection.createChannel();

        // settings of the queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // prepare the message
        String message = "Hello World";

        // publish the message
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println(">>Seng -> '" + message + "'");

        channel.close();
        connection.close();
    }

}
