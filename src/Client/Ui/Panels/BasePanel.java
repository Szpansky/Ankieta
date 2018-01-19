package Client.Ui.Panels;

import Client.config.Client;

import javax.swing.*;
import java.awt.*;


public class BasePanel extends JPanel {
    Client client;

    public BasePanel(Client client) {
        super(true);
        this.client = client;
        setBackground(new Color(255,255,255));
        setLayout(new GridBagLayout());
    }

    public BasePanel() {
        setBackground(new Color(255,255,255));
        setLayout(new GridBagLayout());
    }

    protected void addToLayout(Component component, int weightx, int gridwidth, int gridx, int gridy){
        GridBagConstraints c;
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = weightx;
        c.gridwidth = gridwidth;
        c.gridx = gridx;
        c.gridy = gridy;
        add(component, c);
    }

    protected void addSpaceToLayout(int weightx, int gridwidth, int gridx, int gridy){
        addToLayout(new JTextArea(""),weightx,gridwidth,gridx,gridy);
    }
}
