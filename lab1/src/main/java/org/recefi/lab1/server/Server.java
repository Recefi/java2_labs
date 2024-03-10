package org.recefi.lab1.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ExecutorService service = Executors.newCachedThreadPool();

    public void StartServer() {
        int port = 7474;
        InetAddress ip = null;
        ServerSocket sSock;

        int numRooms = 0;

        try {
            ip = InetAddress.getLocalHost();
            sSock = new ServerSocket(port, 0, ip);
            System.out.println("Server started.");

            while (true) {
                Socket cSock1 = sSock.accept();
                System.out.println("New client 0 connected.");
                Socket cSock2 = sSock.accept();
                System.out.println("New client 1 connected.");

                SController room = new SController(numRooms++, cSock1, cSock2);
                service.submit(room);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Server().StartServer();
    }
}
