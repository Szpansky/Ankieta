package Client.config;

import Data.*;

import java.io.*;
import java.net.Socket;

public class Client {

    Questions questions;
    Statistics statistics;
    Socket mySocket;

    public void Connect() throws IOException {
        int port = 3399;
        mySocket = new Socket("127.0.0.1", port);
    }


    public Questions getQuestionnaire() {
        sendObject(new Questions());

        while (true) {
            Object object = getObject();
            if (object instanceof Questions) {
                questions = (Questions) object;
                break;
            }
        }
        return questions;
    }


    public Statistics getStatistics() {
        sendObject(new Statistics());

        while (true) {
            Object object = getObject();
            if (object instanceof Statistics) {
                statistics = (Statistics) object;
                break;
            }
        }
        return statistics;
    }


    public void sendObject(Object object) {
        try {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(mySocket.getOutputStream());

            mySocket.setTcpNoDelay(true);

            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Object getObject() {
        Object object = null;

        try {
            ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());
            object = in.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }

}







