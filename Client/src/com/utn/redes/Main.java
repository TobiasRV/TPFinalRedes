package com.utn.redes;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String ip, message, scannedPort;
        Integer port;

        while (true) {
            Scanner scanner = new Scanner(System.in);

            //Enter the ip and port of the server to connect
            System.out.println("Enter the server IP (default = localhost): ");
            ip = scanner.nextLine();
            if(ip.isEmpty()){
                ip = "localhost";
            }
            System.out.println("Enter the port (default 3000): ");
            scannedPort = scanner.nextLine();
            if(scannedPort.isEmpty()){
                port = 3000;
            }else{
                port = Integer.valueOf(scannedPort);
            }
            System.out.println("Attempting to connect to server " + ip + " on the port: " + port + "\n");

            try {
                //create a new client with the socket pointing to the server
                Client client = new Client(new Socket(ip, port));
                //start the interactions with the server
                client.startClient();
            } catch (IOException e) {
                System.out.println("Error connecting to the server.");
            }
        }
    }
}
