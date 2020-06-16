package com.utn.redes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String ip, message;
        Integer port;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            // TODO arreglar scanner salteandoce el scan del ip la segunda vez que llega
            //Enter the ip and port of the server to connect
            System.out.println("Enter the server IP: ");
            ip = scanner.nextLine();
            System.out.println("Enter the port: ");
            port = scanner.nextInt();

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
