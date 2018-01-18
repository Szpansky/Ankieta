package Client.Ui.Panels;

import Client.config.Client;

import javax.swing.*;

public class BasePanel extends JPanel {
    Client client;

    public BasePanel(Client client) {
        this.client = client;
    }
}
