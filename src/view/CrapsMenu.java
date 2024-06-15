package view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CrapsMenu extends JFrame {

    public CrapsMenu() {
        setTitle("Game of Craps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create Game menu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(gameMenu);

        // Create Start menu item
        JMenuItem startItem = new JMenuItem("Start", KeyEvent.VK_S);
        startItem.addActionListener(e -> startGame());
        gameMenu.add(startItem);

        // Create Reset Session menu item
        JMenuItem resetItem = new JMenuItem("Reset", KeyEvent.VK_R);
        resetItem.addActionListener(e -> resetSession());
        gameMenu.add(resetItem);

        // Create Exit menu item
        JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitItem.addActionListener(e -> exitGame());
        gameMenu.add(exitItem);

        // Create Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);

        // Create About menu item
        JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);

        // Create Rules menu item
        JMenuItem rulesItem = new JMenuItem("Rules", KeyEvent.VK_R);
        rulesItem.addActionListener(e -> showRulesDialog());
        helpMenu.add(rulesItem);

        setVisible(true);
    }

    private void startGame() {
        // Perform necessary initialization for starting the game
        // rollButton.setEnabled(true);
        // playAgainButton.setEnabled(false);
        // messageLabel.setText("Game Started!");
    }

    private void resetSession() {
        // Clear all data, including win totals, and restart the game
        // winTotal = 0;
        // updateWinTotalLabel();
        startGame();
    }

    private void exitGame() {
        // Ask the user if they are sure they want to exit
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Game",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void showAboutDialog() {
        // Display information about the application
        JOptionPane.showMessageDialog(this, "Game of Craps", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showRulesDialog() {
        // Display rules of the game
        JOptionPane.showMessageDialog(this, "Game Rules:", "Rules",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
