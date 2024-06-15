package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MySwingFrame extends JFrame {

    JButton rollButton;
    private JTextField die1Field;
    private JTextField die2Field;
    private JTextField totalField;
    private JTextField bankField;
    private JButton setBankButton;
    private JTextField pointField;
    private JTextField playerWinField;
    private JTextField houseWinField;
    private JTextField betField;
    private JButton oneButton;
    private JButton fiveButton;
    private JButton tenButton;
    private JButton fiftyButton;
    private JButton hundredButton;
    private JButton fiveHundredButton;
    private JButton playAgainButton;

    public MySwingFrame() {
        setTitle("Game of Craps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(e -> rollDice());
        rollButton.setEnabled(true);
        topPanel.add(rollButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        topPanel.setBackground(new Color(51,0,111));

        JPanel leftPanel = new JPanel(new GridLayout(3,0));
        leftPanel.setBackground(new Color(51,0,111));;
        leftPanel.setOpaque(true);

        JPanel dicePanel = new JPanel(new GridLayout(2,2,10,0));
        dicePanel.setBackground(new Color(51,0,111));;
        dicePanel.setOpaque(true);

        ImageIcon dice1 = new ImageIcon("src/icons/dice1.jpg");
        JLabel label1 = new JLabel();
        label1.setIcon(dice1);
        label1.setHorizontalAlignment(JLabel.CENTER);
        dicePanel.add(label1);
        ImageIcon dice2 = new ImageIcon("src/icons/dice1.jpg");
        JLabel label2 = new JLabel();
        label2.setIcon(dice2);
        label2.setHorizontalAlignment(JLabel.CENTER);
        dicePanel.add(label2);

        JPanel panel1 = new JPanel(new GridLayout(2,0));
        JLabel field1 = new JLabel("Die 1:");
        field1.setForeground(new Color(232,211,162));
        field1.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(field1);
        die1Field = new JTextField(3);
        panel1.add(die1Field);
        dicePanel.add(panel1);
        panel1.setBorder(BorderFactory.createEmptyBorder(0,40,30,40));
        panel1.setBackground(new Color(51,0,111));

        JPanel panel2 = new JPanel(new GridLayout(2,0));
        JLabel field2 = new JLabel("Die 2:");
        field2.setForeground(new Color(232,211,162));
        field2.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(field2);
        die2Field = new JTextField(3);
        panel2.add(die2Field);
        dicePanel.add(panel2);
        panel2.setBorder(BorderFactory.createEmptyBorder(0,40,30,40));
        panel2.setBackground(new Color(51,0,111));

        leftPanel.add(dicePanel);

        JPanel pointPanel = new JPanel(new GridLayout(2,0,0,40));
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(232,211,162)), "Current Roll");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleColor(new Color(232,211,162));
        EmptyBorder emptyBorder = (EmptyBorder) BorderFactory.createEmptyBorder(20, 50, 20, 60);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, border);
        pointPanel.setBorder(compoundBorder);
        pointPanel.setBackground(new Color(51,0,111));
        pointPanel.setOpaque(true);
        totalField = new JTextField(5);
        pointField = new JTextField(5);
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setForeground(new Color(232,211,162));
        pointPanel.add(totalLabel);
        pointPanel.add(totalField);
        JLabel pointLabel = new JLabel("Point:");
        pointLabel.setForeground(new Color(232,211,162));
        pointPanel.add(pointLabel);
        pointPanel.add(pointField);
        leftPanel.add(pointPanel);

        JPanel winsPanel = new JPanel(new GridLayout(2,0,0, 40));
        TitledBorder border1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(232,211,162)), "Win Totals");
        border1.setTitleJustification(TitledBorder.CENTER);
        border1.setTitleColor(new Color(232,211,162));
        EmptyBorder emptyBorder1 = (EmptyBorder) BorderFactory.createEmptyBorder(20, 50, 20, 60);
        CompoundBorder compoundBorder1 = BorderFactory.createCompoundBorder(emptyBorder1, border1);
        winsPanel.setBorder(compoundBorder1);
        winsPanel.setBackground(new Color(51,0,111));
        winsPanel.setOpaque(true);
        playerWinField = new JTextField(5);
        houseWinField = new JTextField(5);
        JLabel playerWinLabel = new JLabel("Player Win Total:");
        playerWinLabel.setForeground(new Color(232,211,162));
        winsPanel.add(playerWinLabel);
        winsPanel.add(playerWinField);
        JLabel houseWinLabel = new JLabel("House Win Total:");
        houseWinLabel.setForeground(new Color(232,211,162));
        winsPanel.add(houseWinLabel);
        winsPanel.add(houseWinField);
        leftPanel.add(winsPanel);

        JPanel rightPanel = new JPanel(new GridLayout(2,0));
        rightPanel.setBackground(new Color(51,0,111));
        rightPanel.setOpaque(true);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20,55,20,55));

        JPanel bankPanel = new JPanel(new GridLayout(2,0));
        TitledBorder border2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(232,211,162)), "Bank");
        border2.setTitleJustification(TitledBorder.CENTER);
        border2.setTitleColor(new Color(232,211,162));
        EmptyBorder emptyBorder2 = (EmptyBorder) BorderFactory.createEmptyBorder(20, 0, 110, 0);
        CompoundBorder compoundBorder2 = BorderFactory.createCompoundBorder(emptyBorder2, border2);
        bankPanel.setBorder(compoundBorder2);
        bankPanel.setBackground(new Color(51,0,111));
        bankPanel.setOpaque(true);

        JPanel bankTextField = new JPanel(new FlowLayout());
        bankTextField.setBackground(new Color(51,0,111));
        bankTextField.setOpaque(true);
        bankField = new JTextField(7);
        JLabel dollarSign = new JLabel("$");
        bankTextField.add(dollarSign);
        dollarSign.setForeground(new Color(232,211,162));
        bankTextField.add(bankField);
        bankPanel.add(bankTextField);

        JPanel setBankPanel = new JPanel();
        setBankPanel.setBackground(new Color(51,0,111));
        setBankPanel.setOpaque(true);
        setBankButton = new JButton("Set Bank");
        setBankButton.addActionListener(e -> setBank());
        setBankButton.setEnabled(true);
        setBankButton.setPreferredSize(new Dimension(90,25));
        setBankPanel.add(setBankButton);
        bankPanel.add(setBankPanel);

        rightPanel.add(bankPanel);

        JPanel betPanel = new JPanel(new GridLayout(7, 0, 0, 3));
        TitledBorder border3 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(232,211,162)), "Bet");
        border3.setTitleJustification(TitledBorder.CENTER);
        border3.setTitleColor(new Color(232,211,162));
        betPanel.setBorder(border3);
        betPanel.setBackground(new Color(51,0,111));
        betPanel.setOpaque(true);

        JPanel betFieldPanel = new JPanel(new FlowLayout());
        betField = new JTextField(5);
        JLabel dollarSign1 = new JLabel("$");
        betFieldPanel.add(dollarSign1);
        dollarSign1.setForeground(new Color(232, 211, 162));
        betFieldPanel.add(betField);
        betFieldPanel.setBackground(new Color(51,0,111));
        betFieldPanel.setOpaque(true);
        betPanel.add(betFieldPanel);

        oneButton = new JButton();
        fiveButton = new JButton();
        tenButton = new JButton();
        fiftyButton = new JButton();
        hundredButton = new JButton();
        fiveHundredButton = new JButton();

        addBetButton(betPanel, "1", oneButton);
        addBetButton(betPanel, "5", fiveButton);
        addBetButton(betPanel, "10", tenButton);
        addBetButton(betPanel, "50", fiftyButton);
        addBetButton(betPanel, "100", hundredButton);
        addBetButton(betPanel, "500", fiveHundredButton);

        rightPanel.add(betPanel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> rollDice());
        playAgainButton.setEnabled(true);
        bottomPanel.add(playAgainButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        bottomPanel.setBackground(new Color(51,0,111));

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        getContentPane().add(mainPanel);

        setVisible(true);

    }

    public void rollDice() {
    }

    public void setBank(){

    }

    private void addBetButton(JPanel betPanel, String label, JButton button) {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(51,0,111));
        buttonPanel.setOpaque(true);
        button.setText("+$" + label);
        button.addActionListener(e -> betButton());
        button.setEnabled(true);
        button.setPreferredSize(new Dimension(70, 20));
        buttonPanel.add(button);
        betPanel.add(buttonPanel);
    }

    public void betButton(){

    }

}