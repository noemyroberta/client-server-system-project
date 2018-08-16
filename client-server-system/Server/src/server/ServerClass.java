/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Noemy Roberta 
 *         and 
 *         Milena Carolinne 923 - 'A'
 */
public class ServerClass {

    private static final int SERVERPORT = 12222;

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;

    private float finalAverage;

    public void execute() {

        try {

            ServerSocket server = new ServerSocket(SERVERPORT, 2);
            System.out.println("[Listening on port............: " + SERVERPORT + "]");

            while (true) {

                connection = server.accept();

                System.out.println("[Established connection........: "
                        + connection.getInetAddress().getHostAddress() + "]");

                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());

                try {
                    int i = 0;
                    int quant = (int) input.readObject();

                    while (i < quant) {

                        i++;

                        float noteOne = (float) input.readObject();
                        float noteTwo = (float) input.readObject();
                        float noteThree = (float) input.readObject();
                        float noteFour = (float) input.readObject();

                        finalAverage = (noteOne + noteTwo + noteThree + noteFour) / 4;
                        output.writeObject(finalAverage);

                    }
                    System.out.println("[Message sent successfully!.....]");

                } catch (ClassNotFoundException | NumberFormatException ex) {

                    Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                }

                output.close();
                input.close();
                connection.close();

            }

        } catch (IOException ex) {

            Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
