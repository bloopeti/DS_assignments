package ro.tuc.dsrl.ds.handson.assig.three.producer.start;

import model.DVD;
import ro.tuc.dsrl.ds.handson.assig.three.producer.connection.QueueServerConnection;

import java.io.IOException;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 * Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-one-client
 * @Since: Sep 1, 2015
 * @Description: Starting point for the Producer Client application. This
 * application will send several messages to be inserted
 * in the queue server (i.e. to be sent via email by a consumer).
 */
public class ClientStart {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;

    private ClientStart() {
    }

    public static void main(String[] args) {
        QueueServerConnection queue = new QueueServerConnection(HOST, PORT);

        try {
            for (int i = 0; i < 5; i++) {
//                queue.writeMessage("this is email number " + i);
                queue.writeDVD(new DVD("Dummy title" + i, 2000 + i, 50));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
