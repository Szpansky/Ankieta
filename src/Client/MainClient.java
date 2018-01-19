package Client;

import Client.Ui.Panels.Main;
import Client.config.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ConnectException;


public class MainClient extends JFrame {
    Client client;
    Main main;


    public MainClient() throws HeadlessException {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new FlowLayout());

        client = new Client();

        try {
            client.Connect();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Brak połączenia z serwerem", "Dialog", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        main = new Main(client);

        JScrollPane pane = new JScrollPane(main);
        setContentPane(pane);
        pack();

        setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);


    }


    public static void main(String[] args) {
        MainClient mainClient = new MainClient();
    }


}
