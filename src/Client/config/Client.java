package Client.config;

/**
 * Created by Marcin on 2018-01-16.
 */

import Data.Answer;
import Data.Answers;
import Data.Question;
import Data.Questions;

import java.io.*;
import java.net.Socket;

public class Client {

    public Questions questions;
    Socket mySocket;

    public Questions getQuestions() {
        return this.questions;
    }


    public void Connect() {
        int port = 3399;
        try {
            mySocket = new Socket("127.0.0.1", port);
            getQuestionnaire();

            // mySocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getQuestionnaire() throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());
            questions = (Questions) in.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendAnswers(Answers answers) {
        try {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(mySocket.getOutputStream());

            mySocket.setTcpNoDelay(true);

            answers.add(new Answer("exit", "exit")); //informacja dla serwera o koncu

            out.writeObject(answers);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}







