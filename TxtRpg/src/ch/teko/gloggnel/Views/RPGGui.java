package ch.teko.gloggnel.Views;

import ch.teko.gloggnel.Models.Character;
import ch.teko.gloggnel.Models.Enemy;
import ch.teko.gloggnel.Models.Player;
import ch.teko.gloggnel.Models.SaveManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RPGGui extends JFrame {
    private Player player;
    private Enemy enemy;

    private JLabel lblPlayerName;
    private JLabel lblPlayerHealth;
    private JLabel lblPlayerLevel;
    private JLabel lblPlayerAttack;
    private JLabel lblPlayerDefense;
    private JLabel lblPlayerSkillPoints;
    private JLabel lblPlayerHitChance;
    private JLabel lblPlayerCritChance;
    private JLabel lblPlayerCritDamage;
    private JLabel lblEnemyName;
    private JLabel lblEnemyHealth;

    private JTextArea battleLog;

    private Timer battleTimer;
    private boolean playerTurn;
    private Random random;

    private JButton btnIncreaseAttack;
    private JButton btnIncreaseDefense;
    private JButton btnIncreaseHealth;
    private JButton btnIncreaseHitChance;
    private JButton btnIncreaseCritChance;
    private JButton btnIncreaseCritDamage;

    public RPGGui() {
        setTitle("SIMPLRPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        player = SaveManager.loadPlayer();
        if (player == null) {
            String playerName = null;
            while (playerName == null) {
                playerName = JOptionPane.showInputDialog(this, "Enter your character's name:");
            }
            player = new Player(playerName);
        }

        enemy = new Enemy("Goblin", 1);
        random = new Random();

        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());

        JPanel playerStatsPanel = createPlayerStatsPanel();
        add(playerStatsPanel, BorderLayout.WEST);

        // Create Tabbed Pane for Battle and Skills
        JTabbedPane tabbedPane = new JTabbedPane();

        // Battle Tab
        JPanel battlePanel = createBattlePanel();
        tabbedPane.addTab("Battle", battlePanel);

        // Skills Tab
        JPanel skillsPanel = createSkillsPanel();
        tabbedPane.addTab("Skills", skillsPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createPlayerStatsPanel() {
        JPanel playerStatsPanel = new JPanel(new GridLayout(9, 1, 5, 5)); // Adjusted to include more stats
        playerStatsPanel.setBorder(BorderFactory.createTitledBorder("Player Stats"));

        lblPlayerName = new JLabel("Name: " + player.getName());
        lblPlayerLevel = new JLabel("Level: " + player.getLevel());
        lblPlayerHealth = new JLabel("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        lblPlayerAttack = new JLabel("Attack: " + player.getAttack());
        lblPlayerDefense = new JLabel("Defense: " + player.getDefense());
        lblPlayerHitChance = new JLabel("Hit Chance: " + (player.getHitChance() * 100) + "%");
        lblPlayerCritChance = new JLabel("Crit Chance: " + (player.getCriticalHitChance() * 100) + "%");
        lblPlayerCritDamage = new JLabel("Crit Damage: " + (player.getCriticalDamage() * 100) + "%");
        lblPlayerSkillPoints = new JLabel("Skill Points: " + player.getSkillPoints());

        playerStatsPanel.add(lblPlayerName);
        playerStatsPanel.add(lblPlayerLevel);
        playerStatsPanel.add(lblPlayerHealth);
        playerStatsPanel.add(lblPlayerAttack);
        playerStatsPanel.add(lblPlayerDefense);
        playerStatsPanel.add(lblPlayerHitChance);
        playerStatsPanel.add(lblPlayerCritChance);
        playerStatsPanel.add(lblPlayerCritDamage);
        playerStatsPanel.add(lblPlayerSkillPoints);

        return playerStatsPanel;
    }

    private JPanel createBattlePanel() {
        JPanel battlePanel = new JPanel(new BorderLayout());

        // Panel for player and enemy information
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblEnemyName = new JLabel("Enemy: " + enemy.getName());
        lblEnemyHealth = new JLabel("Health: " + enemy.getHealth());

        infoPanel.add(lblEnemyName);
        infoPanel.add(lblEnemyHealth);

        battlePanel.add(infoPanel, BorderLayout.NORTH);

        // Battle log
        battleLog = new JTextArea();
        battleLog.setEditable(false);
        battleLog.setLineWrap(true);
        battleLog.setWrapStyleWord(true);
        battleLog.setBackground(Color.BLACK);
        battleLog.setForeground(Color.GREEN);
        battleLog.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(battleLog);
        battlePanel.add(scrollPane, BorderLayout.CENTER);

        // Battle buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAttack = new JButton("Attack");
        btnAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAttack.setEnabled(false);
                startBattle();
            }
        });

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveManager.savePlayer(player);
                battleLog.append("Game saved.\n");
            }
        });

        buttonPanel.add(btnAttack);
        buttonPanel.add(btnSave);
        battlePanel.add(buttonPanel, BorderLayout.SOUTH);

        return battlePanel;
    }

    private JPanel createSkillsPanel() {
        JPanel skillsPanel = new JPanel(new BorderLayout());
        skillsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Skill Buttons
        JPanel skillButtonPanel = new JPanel(new GridLayout(6, 1, 5, 5)); // Adjusted to include more buttons

        btnIncreaseAttack = new JButton("Increase Attack (" + player.getAttack() + ")");
        btnIncreaseAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spendSkillPoints("attack");
            }
        });

        btnIncreaseDefense = new JButton("Increase Defense (" + player.getDefense() + ")");
        btnIncreaseDefense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spendSkillPoints("defense");
            }
        });

        btnIncreaseHealth = new JButton("Increase Health (" + player.getMaxHealth() + ")");
        btnIncreaseHealth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spendSkillPoints("health");
            }
        });

        btnIncreaseHitChance = new JButton("Increase Hit Chance (" + (player.getHitChance() * 100) + "%)");
        btnIncreaseHitChance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spendSkillPoints("hitChance");
            }
        });

        btnIncreaseCritChance = new JButton("Increase Crit Chance (" + (player.getCriticalHitChance() * 100) + "%)");
        btnIncreaseCritChance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spendSkillPoints("critChance");
            }
        });

        btnIncreaseCritDamage = new JButton("Increase Crit Damage (" + (player.getCriticalDamage() * 100) + "%)");
        btnIncreaseCritDamage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spendSkillPoints("critDamage");
            }
        });

        skillButtonPanel.add(btnIncreaseAttack);
        skillButtonPanel.add(btnIncreaseDefense);
        skillButtonPanel.add(btnIncreaseHealth);
        skillButtonPanel.add(btnIncreaseHitChance);
        skillButtonPanel.add(btnIncreaseCritChance);
        skillButtonPanel.add(btnIncreaseCritDamage);

        skillsPanel.add(skillButtonPanel, BorderLayout.CENTER);

        return skillsPanel;
    }

    private void spendSkillPoints(String attribute) {
        if (player.getSkillPoints() > 0) {
            player.distributeSkillPoints(attribute, 1);
            updatePlayerStatsPanel();
            updateSkillButtonLabels();
        } else {
            JOptionPane.showMessageDialog(this, "No skill points available!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSkillButtonLabels() {
        btnIncreaseAttack.setText("Increase Attack (" + player.getAttack() + ")");
        btnIncreaseDefense.setText("Increase Defense (" + player.getDefense() + ")");
        btnIncreaseHealth.setText("Increase Health (" + player.getMaxHealth() + ")");
        btnIncreaseHitChance.setText("Increase Hit Chance (" + (player.getHitChance() * 100) + "%)");
        btnIncreaseCritChance.setText("Increase Crit Chance (" + (player.getCriticalHitChance() * 100) + "%)");
        btnIncreaseCritDamage.setText("Increase Crit Damage (" + (player.getCriticalDamage() * 100) + "%)");
    }

    private void updatePlayerStatsPanel() {
        lblPlayerLevel.setText("Level: " + player.getLevel());
        lblPlayerHealth.setText("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        lblPlayerAttack.setText("Attack: " + player.getAttack());
        lblPlayerDefense.setText("Defense: " + player.getDefense());
        lblPlayerHitChance.setText("Hit Chance: " + (player.getHitChance() * 100) + "%");
        lblPlayerCritChance.setText("Crit Chance: " + (player.getCriticalHitChance() * 100) + "%");
        lblPlayerCritDamage.setText("Crit Damage: " + (player.getCriticalDamage() * 100) + "%");
        lblPlayerSkillPoints.setText("Skill Points: " + player.getSkillPoints());
    }

    private void startBattle() {

        resetBattle();
        battleLog.append("The battle begins...\n");


        playerTurn = true;
        battleTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performBattleStep();
            }
        });

        battleTimer.start();
    }

    private void resetBattle() {
        player.setHealth(player.getMaxHealth());
        enemy = new Enemy("Goblin", player.getLevel());
        updateGUI();
    }

    private void performBattleStep() {
        if (player.getHealth() <= 0 || enemy.getHealth() <= 0) {
            battleTimer.stop();
            battleLog.append("The battle is over.\n");
            if (player.getHealth() > 0) {
                battleLog.append("The player has won!\n");
                player.addExperience(50, battleLog);
            } else {
                battleLog.append("The player was defeated...\n");
            }
            updatePlayerStatsPanel();
            return;
        }

        if (playerTurn) {
            performAttack(player, enemy);
        } else {
            performAttack(enemy, player);
        }

        playerTurn = !playerTurn; // Switch turns
        updateGUI();
    }

    private void performAttack(Character attacker, Character target) {
        if (random.nextDouble() <= attacker.getHitChance()) {
            boolean isCriticalHit = random.nextDouble() <= attacker.getCriticalHitChance();
            int damage = isCriticalHit ? (int)(attacker.getAttack() * attacker.getCriticalDamage()) : attacker.getAttack();

            damage = Math.max(damage - target.getDefense(), 1);
            target.setHealth(target.getHealth() - damage);

            if (isCriticalHit) {
                battleLog.append(attacker.getName() + " lands a CRITICAL HIT! Dealing " + damage + " damage.\n");
            } else {
                battleLog.append(attacker.getName() + " deals " + damage + " damage.\n");
            }
        } else {
            battleLog.append(attacker.getName() + " missed the attack!\n");
        }
    }

    private void updateGUI() {
        lblEnemyHealth.setText("Health: " + enemy.getHealth());
        updatePlayerStatsPanel();
        updateSkillButtonLabels();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RPGGui gui = new RPGGui();
                gui.setVisible(true);
            }
        });
    }
}
