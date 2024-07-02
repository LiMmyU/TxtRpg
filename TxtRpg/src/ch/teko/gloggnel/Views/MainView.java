package ch.teko.gloggnel.Views;

import ch.teko.gloggnel.Controllers.BattleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    private JButton kampfButton;
    private JLabel enemyIsHit;

    public MainView(){
        JFrame frame = new JFrame();
        BattleController _battleController = new BattleController();

        ViewRootContainer.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));

        frame.add(ViewRootContainer, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TXTRPG");
        frame.setSize(800, 600);
        frame.setVisible(true);

        kampfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_battleController.calculateEnemyHit()) {
                    int damageDone =_battleController.calculateDamage();
                    enemyIsHit.setText("Enemy Hit with " + damageDone + " Damage");
                    ViewLogArea.append(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date()) +  ": Enemy Hit with " + damageDone + " Damage\n");
                }else{
                    enemyIsHit.setText("Enemy Missed");
                    ViewLogArea.append(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date()) +  ": Enemy Missed\n");
                }

            }
        });

    }

}
