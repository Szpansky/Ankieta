package Server.config;

import java.io.IOException;
import java.net.*;


public class Server {

    public static void Start() {
        int port = 3399;
        ServerSocket serverSocket = null;
        try {
            // tworzymy socket
            serverSocket = new ServerSocket(port);
            System.out.println("Port Serwera = " + port + "\n");
            while (true) {
                // czekamy na zgłoszenie klienta ...
                Socket socket = serverSocket.accept();
                // tworzymy wątek dla danego połączenia i uruchamiamy go
                (new ServerTCPThread(socket)).start();

            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}


