package start;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.DVD;

public class Sender {
    private final static String QUEUE_NAME = "dvds";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DVD dvd = new DVD("DVD1", 1990, 10.0);

        channel.basicPublish("", QUEUE_NAME, null, dvd.serialize());
        System.out.println(" [x] Sent '" + dvd.toString() + "'");

        channel.close();
        connection.close();
    }
}
