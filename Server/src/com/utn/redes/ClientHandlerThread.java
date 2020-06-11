package com.utn.redes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = "";
            while(message != null){
                if ("X".equalsIgnoreCase(message)){
                    break;
                }
                message = bufferedReader.readLine();
                String clientMessage = formatClientMessage(message);
                System.out.println(clientMessage);
                dataOutputStream.writeUTF(clientMessage);
            }

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String formatClientMessage(String text){
        return "[Client " + idSession + "]==> " + text+ "\n";
    }
}
