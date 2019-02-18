package messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.DVD;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DVDSender {
    private final static String QUEUE_NAME = "dvds";

    public static void sendNewDVD(DVD dvd) {
        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            channel.basicPublish("", QUEUE_NAME, null, dvd.serialize());
            System.out.println(" [x] Sent '" + dvd.toString() + "'");

            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
