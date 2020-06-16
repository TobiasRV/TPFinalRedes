package com.utn.redes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandlerThread implements Runnable {

    Socket clientSocket;
    Integer idSession;


    public ClientHandlerThread(Socket clientSocket, Integer idSession) {
        this.clientSocket = clientSocket;
        this.idSession = idSession;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        String message;
        try {
            //Initializing the input and output
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Send a message to the client indicating that the connection was established
            bufferedWriter.write("Connection established.");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            //Printing the message from the server indicating that the connection was established
            message = bufferedReader.readLine();
            this.printClientMessage(message);
            //True if the socket connected and is not closed
            while ((clientSocket.isConnected()) && (!clientSocket.isClosed())) {
                //Read the message from the user
                message = bufferedReader.readLine();

                //If the message sent by the user is x the server disconnects if not print the message and input a response
                if ("X".equalsIgnoreCase(message)) {
                    bufferedWriter.write("Connection finished by the user.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    disconnect();
                    System.out.println("Connection finished by the user");
                    break;
                } else {
                    printClientMessage(message);
                    System.out.println("Enter response: ");
                    message = scanner.nextLine();
                    //If the response is not x the response is send if not send a notification that the server finish the connection and disconnect the socket
                    if (!message.equalsIgnoreCase("X")) {
                        bufferedWriter.write(message);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else {
                        bufferedWriter.write("Connection finished by the server.");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        disconnect();
                        System.out.println("Connection finished");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("User disconnected");
            this.disconnect();
        }

    }

    public void printClientMessage(String text) {
        System.out.println("[Client " + idSession + "]==> " + text + "\n");
    }

    public void disconnect() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error trying to close the server\n");
        }
    }
}
