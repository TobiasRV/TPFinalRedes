package com.utn.redes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;

    public Client(Socket socket){
        this.socket = socket;
    }


    public void startClient(){
        Scanner scanner = new Scanner(System.in);
        String message = "";
        try {

            DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            //Print the message send by the server
            System.out.println("Client started successfully");
            message = bufferedReader.readLine();
            this.printServerMessage(message);

            // TODO cliente no envia mensajes
            while(socket.isConnected()){
                System.out.println("Ingrese el mensaje: ");
                message = scanner.nextLine();
                System.out.println(message);
                so
                dataOutputStream.writeUTF(message);
                printServerMessage(bufferedReader.readLine());
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void printServerMessage(String text){
        System.out.println("[Server]==> " + text + "\n");
    }

}
