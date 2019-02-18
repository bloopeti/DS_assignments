package ro.tuc.dsrl.ds.handson.assig.three.consumer.start;

import model.DVD;
import ro.tuc.dsrl.ds.handson.assig.three.consumer.connection.QueueServerConnection;
import ro.tuc.dsrl.ds.handson.assig.three.consumer.service.MailService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 * Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-one-client
 * @Since: Sep 1, 2015
 * @Description: Starting point for the Consumer Client application. This application
 * will run in an infinite loop and retrieve messages from the queue server
 * and send e-mails with them as they come.
 */
public class ClientStart {

    private ClientStart() {
    }

    public static void main(String[] args) {
        QueueServerConnection queue = new QueueServerConnection("localhost", 8888);

        MailService mailService = new MailService("peter.zavaczki.tucn@gmail.com", "TODO: Insert password here");
//        String message;
        DVD dvd;

//        File file = new File("DVDs_received.txt");

        while (true) {
            try {
                dvd = queue.readDVD();
                String str = "DVD Title=" + dvd.getTitle() + ", DVD Year=" + dvd.getYear() + ", DVD Price=" + dvd.getPrice();

                BufferedWriter writer = new BufferedWriter(new FileWriter("DVDs_received.txt", true));
                writer.append("Got dvd: " + str);
                writer.append('\n');

                writer.close();

                System.out.println("Sending mail: " + str);
                mailService.sendMail("peter.zavaczki.tucn@gmail.com","Dummy Mail Title", str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
