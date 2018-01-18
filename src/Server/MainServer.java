package Server;

import Server.Database.JDBC;
import Server.config.Server;


/**
 * Created by Marcin on 2018-01-16.
 */
public class MainServer {

    public static void main(String[] args) {

        JDBC.connectToDB();

        Server.Start();

        JDBC.closeConnection();



    }

}
