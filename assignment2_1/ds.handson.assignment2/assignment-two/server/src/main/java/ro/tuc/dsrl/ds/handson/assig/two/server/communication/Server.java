package ro.tuc.dsrl.ds.handson.assig.two.server.communication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Registry;
import ro.tuc.dsrl.ds.handson.assig.two.server.services.SellingPriceService;
import ro.tuc.dsrl.ds.handson.assig.two.server.services.TaxService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 * Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-server
 * @Since: Sep 24, 2015
 * @Description: Thread which listens for incoming connections and creates a Session for each client.
 */
public class Server implements Runnable {
    private static final Log LOGGER = LogFactory.getLog(Server.class);

    private ServerSocket serverSocket;

    /**
     * Create a socket object from the ServerSocket to listen to and accept
     * connections.
     */
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        Registry.getInstance().registerEndpoint("ITaxService", new TaxService());
        Registry.getInstance().registerEndpoint("ISellingPriceService", new SellingPriceService());
        new Thread(this).start();
    }

    /**
     * Accepts connections from clients and assigns a thread to deal with the messages form and to the client
     */
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    Socket clientSocket;
                    clientSocket = serverSocket.accept();
                    Session cThread = new Session(clientSocket);
                    cThread.start();
                }
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }
    }

}