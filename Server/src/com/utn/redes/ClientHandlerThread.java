package com.utn.redes;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandlerThread implements Runnable{

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
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = "";

            //Send a message to the user indicating that the connection has been stablished
            dataOutputStream.writeUTF("Connection established \n");

            while(message != null){
                message = bufferedReader.readLine();
                if ("X".equalsIgnoreCase(message)){
                    break;
                }else {
                    printClientMessage(message);
                    System.out.println("Enter response: ");
                    dataOutputStream.writeUTF(scanner.nextLine() + "\n");
                }
            }

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void printClientMessage(String text){
        System.out.println("[Client " + idSession + "]==> " + text+ "\n");
    }


}
