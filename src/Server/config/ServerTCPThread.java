package Server.config;

import Data.*;
import Server.Database.JDBC;

import java.io.*;
import java.net.*;

public class ServerTCPThread extends Thread {
    Socket mySocket;

    public ServerTCPThread(Socket socket) {
        super(); // konstruktor klasy Thread
        mySocket = socket;
    }


    public void run() {
        System.out.println("NEW (" + mySocket.getPort() + ") CONNECTED");

        /**
         * Nieskończona pętla do nasłuchiwania portu
         */
        while (true) {
            System.out.println("GET (" + mySocket.getPort() + ") WAITING");
            if (getObject(mySocket)) {
                System.out.println("GET (" + mySocket.getPort() + ") OK");
            } else {
                System.out.println("GET: (" + mySocket.getPort() + ") FALSE, CONNECTION LOST");
                break;
            }
        }
    }


    private boolean sendQuestionnaire(Socket mySocket) {
        try {
            Questions questions = JDBC.getQuestions();
            ObjectOutputStream out = new ObjectOutputStream(mySocket.getOutputStream());
            out.writeObject(questions);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private boolean sendStatistics(Socket mySocket, Statistics statistics) {
        return true;
    }


    private boolean getObject(Socket mySocket) {
        Answers answers = null;
        Statistics statistics = null;

        try {
            ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());
            Object object = in.readObject();

            /**
             *  nieskonczona pętla do sprawdzaniu jakiego typu jest przeslany obiekt
             */
            while (true) {

                if (object instanceof Answers) {
                    answers = (Answers) object;
                    JDBC.sendAnswers(answers);
                    break;
                }

                if (object instanceof Statistics) {
                    statistics = (Statistics) object;
                    statistics = JDBC.getStatistics();
                    sendStatistics(mySocket, statistics);
                    break;
                }

                if (object instanceof Questions) {
                    if (sendQuestionnaire(mySocket)) {
                        System.out.println("SEND (" + mySocket.getPort() + ") OK");
                    } else {
                        System.out.println("SEND (" + mySocket.getPort() + ") FALSE");
                    }
                    break;
                }

                if (object instanceof Close) {
                    mySocket.close();
                    break;
                }


            }

        } catch (IOException e) {
           // e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;

    }

}
