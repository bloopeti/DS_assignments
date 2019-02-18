package messaging;

import com.rabbitmq.client.*;
import mailing.Mailer;
import model.DVD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Receiver {

    private final static String QUEUE_NAME = "dvds";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        final Mailer mailer = new Mailer("peter.zavaczki.tucn@gmail.com", "P4$$word");
        final List<String> clients = new ArrayList<String>();
        clients.add("peter.zavaczki.tucn@gmail.com");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                DVD dvd = new DVD(body);
                String message = dvd.toString(); // new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

                BufferedWriter writer = new BufferedWriter(new FileWriter("DVDs_received.txt", true));
                writer.append("Got dvd: " + message);
                writer.append('\n');
                writer.close();

                System.out.println("Sending mail: " + message);

                mailer.sendMassMail(clients,"Dummy Mail Title", message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
