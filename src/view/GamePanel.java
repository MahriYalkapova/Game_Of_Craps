package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JFrame {

    private JButton rollButton;
    private JButton playAgainButton;
    private JTextField die1TextField;
    private JTextField die2TextField;
    private JTextField totalTextField;
    private JTextField pointTextField;
    private JTextField bankTextField;
    private JLabel messageLabel;
    private int winTotal = 0;

    public void showStartPopup() {
        JOptionPane.showMessageDialog(null, "Please set your bank amount before playing.", "Game of Craps",
                JOptionPane.PLAIN_MESSAGE);
    }

    public GamePanel() {
        setTitle("Game of Craps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
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

        // Create a panel with BorderLayout
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create roll button and add to the NORTH position
        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(e -> rollDice());
        rollButton.setEnabled(true);
        mainPanel.add(rollButton);

        // Create a subpanel for the text fields
        JPanel subPanel = new JPanel(new GridLayout(5,2));

        // Create text fields for Die 1, Die 2, Total, and Point
        die1TextField = new JTextField(5);
        die2TextField = new JTextField(5);
        totalTextField = new JTextField(5);
        pointTextField = new JTextField(5);
        pointTextField.setEditable(false); // Make the Point field non-editable

        // Create text field for the bank
        bankTextField = new JTextField(10);

        // Add components to the subpanel
        subPanel.add(new JLabel("Die 1:"));
        subPanel.add(die1TextField);
        subPanel.add(new JLabel("Die 2:"));
        subPanel.add(die2TextField);
        subPanel.add(new JLabel("Total:"));
        subPanel.add(totalTextField);
//        subPanel.add(new JLabel("Point:"));
//        subPanel.add(pointTextField);
//        subPanel.add(new JLabel("Bank:"));
//        subPanel.add(bankTextField);

        // Add the subpanel to the main panel at CENTER position
        mainPanel.add(subPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        getContentPane().add(mainPanel);

        setVisible(true);
    }

    private void startGame() {
        rollButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        messageLabel.setText("Game Started!");
    }

    private void resetSession() {
        winTotal = 0;
        updateWinTotalLabel();
        startGame();
    }

    private void exitGame() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Game",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, "Game of Craps", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showRulesDialog() {
        JOptionPane.showMessageDialog(this, "Game Rules:", "Rules",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void rollDice() {
    }

    private void updateWinTotalLabel() {
    }

}
