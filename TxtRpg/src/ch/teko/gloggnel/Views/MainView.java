package ch.teko.gloggnel.Views;

import ch.teko.gloggnel.Controllers.BattleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class MainView {
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
    private JTextPane ViewLogArea2;
    private JTextArea ViewLogArea;
    private JProgressBar progressCooldown;

    public MainView(){
        JFrame frame = new JFrame();
        BattleController _battleController = new BattleController();

        ViewRootContainer.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(ViewRootContainer, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TXTRPG");
        frame.setSize(1200, 1000);
        frame.setVisible(true);

        kampfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                kampfButton.setEnabled(false);
                if(_battleController.calculateEnemyHit()) {
                    int damageDone =_battleController.calculateDamage();
                    enemyIsHit.setText("Enemy Hit with " + damageDone + " Damage ");
                    appendLogText("Hit with " + damageDone + " Damage");
                }else{
                    enemyIsHit.setText("Enemy Missed ");
                    appendLogText("Enemy Missed");
                }
                new CooldownHandler().execute();
            }
        });

    }

    public void appendLogText(String text){
        ViewLogArea.append(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date()) +  ": " + text + "\n");
    }



    //Cooldown handling
    private class CooldownHandler extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            int cooldown = 500;
            int steps = 100;
            int delay = cooldown / steps;

            for (int i = 100; i >= 0 ; i--) {
                progressCooldown.setValue(i);
                Thread.sleep(delay);
            }

            return null;
        }

        @Override
        protected void done() {
            kampfButton.setEnabled(true);
        }

    }

}


