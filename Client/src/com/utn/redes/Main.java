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
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the server IP: ");
        ip = scanner.nextLine();
        System.out.println("Enter the port: ");
        port = scanner.nextInt();

        System.out.println("Attempting to connect to server " + ip + " on the port: " + port + "\n");

        try {
            Client client = new Client(new Socket(ip,port));
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
