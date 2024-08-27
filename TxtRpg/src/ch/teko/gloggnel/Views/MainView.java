package ch.teko.gloggnel.Views;

import ch.teko.gloggnel.Controllers.BattleController;
import ch.teko.gloggnel.Models.PlayerModel;
import ch.teko.gloggnel.Models.StorageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
    private JPanel battleContainer;
    private JPanel battlePlayerContainer;
    private JPanel battleEnemyContainer;
    private JTextArea battlePlayerStats;
    private JTextArea battleEnemyStats;
    private PlayerModel _player;
    private StorageModel _storage;

    public MainView(PlayerModel player, StorageModel storage){
        JFrame frame = new JFrame();
        _player = player;
        _storage = storage;
        BattleController _battleController = new BattleController(_player, _storage);

        ViewRootContainer.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(ViewRootContainer, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("TXTRPG");
        frame.setSize(1200, 1000);
        frame.setVisible(true);

        kampfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //kampfButton.setEnabled(false);
                if(_battleController.calculateEnemyHit()) {
                    double damageDone =_battleController.calculateDamage();
                    enemyIsHit.setText("Enemy Hit with " + damageDone + " Damage ");
                    appendLogText("Hit with " + damageDone + " Damage");
                }else{
                    enemyIsHit.setText("Enemy Missed ");
                    appendLogText("Enemy Missed");
                }
                //new CooldownHandler().execute();
            }
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

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


