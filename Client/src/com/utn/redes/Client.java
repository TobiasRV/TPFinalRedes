package com.utn.redes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;

    public Client(Socket socket){
        this.socket = socket;
    }


    public void startClient(){
        Scanner scanner = new Scanner(System.in);
        String message;
        try {
            //Initializing the input and output
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            System.out.println("Client started successfully");
            //Printing the message from the server indicating that the connection was established
            message = bufferedReader.readLine();
            this.printServerMessage(message);
            //Send a message to the server indicating that the connection was established
            bufferedWriter.write("Connection established.");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            //True if the socket connected and is not closed
            while((socket.isConnected()) && (!socket.isClosed())){
                System.out.println("Enter the message: ");
                message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                //If the message is not x print the message from the server, and if it's x break the while
                if(!message.equalsIgnoreCase("X")){
                    message = bufferedReader.readLine();
                    if(!message.equalsIgnoreCase("Connection finished by the server.")){
                        printServerMessage(message);
                    }else{
                        this.disconnect();
                        break;
                    }
                }else{
                    this.disconnect();
                    break;
                }
            }
            this.disconnect();

        } catch (IOException e) {
            System.out.println("Disconnected from the server");
        }


    }

    public void printServerMessage(String text){
        System.out.println("[Server]==> " + text + "\n");
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error trying to disconnect\n");
        }
    }
}
