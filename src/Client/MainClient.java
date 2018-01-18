package Client;

import Client.Ui.Panels.Main;
import Client.config.Client;

import javax.swing.*;
import java.awt.*;


public class MainClient extends JFrame {
    Client client;


    public MainClient() throws HeadlessException {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLayout(new FlowLayout());

        client = new Client();
        client.Connect();
        add(new Main(client));
    }


    public static void main(String[] args) {
        new MainClient();
    }


}
