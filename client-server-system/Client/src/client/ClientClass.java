/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author Noemy Roberta 
 *         and 
 *         Milena Carolinne 923 - 'A'
 */
public class ClientClass {

    private static final String ADDRESS = "localhost";
    private static final int PORT = 12222;

    public void execute() {

        ObjectOutputStream output;
        ObjectInputStream input;
        float finalAverage;
        Socket connection;

        try {

            connection = new Socket(ADDRESS, PORT);
            System.out.println("[Connected to the server...: " + ADDRESS + ", on port...: " + PORT + "]");

            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();

            input = new ObjectInputStream(connection.getInputStream());

            System.out.println("[Digite a quantidade de alunos....:] ");
            Scanner inpt = new Scanner(System.in);
            int quant = inpt.nextInt();
            System.out.println("\n\n");
            output.writeObject(quant);

            try {

                int i = 0;
                while (i < quant) {

                    i++;

                    System.out.println("Nota 01: ");
                    float noteOne = inpt.nextFloat();
                    System.out.println("Nota 02: ");
                    float noteTwo = inpt.nextFloat();
                    System.out.println("Nota 03: ");
                    float noteThree = inpt.nextFloat();
                    System.out.println("Nota 04: ");
                    float noteFour = inpt.nextFloat();

                    output.writeObject(noteOne);
                    output.writeObject(noteTwo);
                    output.writeObject(noteThree);
                    output.writeObject(noteFour);

                    finalAverage = (float) input.readObject();
                    System.out.println("\nMédia do aluno "+i+" ..: "+ finalAverage);

                    System.out.println("\n\n");

                }

            } catch (ClassNotFoundException ex) {

                Logger.getLogger(ClientClass.class.getName()).log(Level.SEVERE, null, ex);
            }

            output.close();
            input.close();
            connection.close();

        } catch (IOException ex) {

            printStackTrace(ex);

        }

    }

}
