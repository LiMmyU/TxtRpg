package ch.teko.gloggnel.Views;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private JTextArea ViewLogArea;
    private JLabel ViewLogTitle;
    private JPanel ViewLogContainer;
    private JPanel ViewMainContainer;
    private JTextPane ViewStatsArea;
    private JLabel ViewStatsTitle;
    private JPanel ViewStatsContainer;
    private JPanel ViewRootContainer;
    private JTabbedPane tabbedPane1;

    public MainView(){
        JFrame frame = new JFrame();

        ViewRootContainer.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));

        frame.add(ViewRootContainer, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TXTRPG");
        frame.setSize(800, 600);
        frame.setVisible(true);

    }
}
