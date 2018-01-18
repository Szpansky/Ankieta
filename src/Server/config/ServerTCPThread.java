package Server.config;

import Data.Answer;
import Data.Answers;
import Data.Question;
import Data.Questions;
import Server.Database.JDBC;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerTCPThread extends Thread {
    Socket mySocket;

    public ServerTCPThread(Socket socket) {
        super(); // konstruktor klasy Thread
        mySocket = socket;
    }

    public void run() {


        System.out.println("NEW (" + mySocket.getPort() + ") CONNECTED");
        System.out.println("SEND (" + mySocket.getPort() + ") WAITING");


        if (sendQuestionnaire(mySocket))
            System.out.println("SEND (" + mySocket.getPort() + ") OK");
        else
            System.out.println("SEND (" + mySocket.getPort() + ") FALSE");


        if (getAnswers(mySocket))
            System.out.println("GET (" + mySocket.getPort() + ") OK");
        else
            System.out.println("GET: " + mySocket.getPort() + ") FALSE");


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


    private boolean getAnswers(Socket mySocket) {
        boolean flag = false;
        Answers answers;
        try {
            ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());
            System.out.println("GET (" + mySocket.getPort() + ") WAITING");
            while (true) {

                answers = (Answers) in.readObject();


                for (Answer answer : answers) {
                    if (answer.id.contains("exit") || answer.answer.contains("exit")) {
                        mySocket.close();
                        flag = true;
                    }else{
                        System.out.println(answer.id + " " + answer.answer );
                    }
                }

                if (flag) break;
            }
            JDBC.sendAnswers(answers);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException d) {
            d.printStackTrace();
            return false;
        }
        return true;

    }

}
