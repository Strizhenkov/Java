package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import entites.ControllerUnit;
import entites.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class App 
{
    public static void main(String[] args) throws IOException, TimeoutException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ControllerUnit control = (ControllerUnit) context.getBean("controllerUnit");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[x] Received '" + message + "'");
            control.LoadCommand(message);
        };
        channel.basicConsume("QUEUE_NAME", true, deliverCallback, consumerTag -> { });
        //control.LoadCommand("GET All Admin");
        //control.LoadCommand("GET All Karl $2a$10$rBB8jC6vfrtPg0aOyHgSyeU2tMaKVTaXygqVnNFGCpaopKxOStnDK");
        //control.LoadCommand("GET All Karl 123");
        System.out.println(control.Run());
    }
}