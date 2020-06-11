package com.utn.redes;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        Integer port = 8888;
        Integer idSession = 0;
        System.out.println("Starting server...\n");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("[OK]\n");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                idSession++;
                new ClientHandlerThread(clientSocket, idSession);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
