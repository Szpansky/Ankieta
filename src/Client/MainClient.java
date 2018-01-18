package Client;

import Client.Ui.Fragments.Main;
import Client.Ui.Fragments.Questionnaire;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Marcin on 2018-01-16.
 */
public class MainClient extends JFrame {

    public MainClient() throws HeadlessException {

        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        add(new Main());
        setSize(800,600);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }


    public static void main(String[] args) {
        new MainClient();
    }



}
