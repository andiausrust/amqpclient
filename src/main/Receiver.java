package main;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;



public class Receiver {

    private static String QUEUE_NAME = "hallo";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, (Map)null);

        System.out.println("Waiting for messages ... ");

        Consumer consumer = new DefaultConsumer(channel){

            public void handleDelivery(String consumerTag, Envelope envelope,
                                        AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println(">>Received: " + new String(body));
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
