package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class UserInterface {

    public void Start() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare("QUEUE_NAME", false, false, false, null);
            String message = "GET All Karl $2a$10$rBB8jC6vfrtPg0aOyHgSyeU2tMaKVTaXygqVnNFGCpaopKxOStnDK";
            channel.basicPublish("", "QUEUE_NAME", null, message.getBytes());
            System.out.println("[x] Sent '" + message + "'");
        }
        System.out.println("END");
    }
}
