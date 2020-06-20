package com.utn.redes;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        Integer port = 3000;
        Integer idSession = 0;
        System.out.println("Starting server...\n");
        try {
            //Create a serverSocket listening to the port
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("[OK]\n");
            while (true) {
                //Accept the connection whit the client and create the correspondent socket
                Socket clientSocket = serverSocket.accept();
                System.out.println("New incoming connection");
                // increase the session number by 1
                idSession++;
                //Create a thread to handle the communication with the client
                new ClientHandlerThread(clientSocket, idSession);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
