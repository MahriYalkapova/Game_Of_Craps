package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * The LeftPanel class represents the user interface panel located on the left side
 * of the application window. It displays information related to dice rolls and win totals.
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class LeftPanel extends JPanel {

    /**
     * The JTextField displaying the value of the first die.
     */
    private static JTextField die1Field;

    /**
     * The JTextField displaying the value of the second die.
     */
    private static JTextField die2Field;

    /**
     * The JTextField displaying the total value of the dice.
     */
    private JTextField totalField;

    /**
     * The JTextField displaying the point value in the current roll.
     */
    private JTextField pointField;

    /**
     * The JTextField displaying the total number of player wins.
     */
    private JTextField playerWinField;

    /**
     * The JTextField displaying the total number of house wins.
     */
    private JTextField houseWinField;

    /**
     * The JLabel displaying the icon for the first die.
     */
    private static JLabel label1;

    /**
     * The JLabel displaying the icon for the second die.
     */
    private static JLabel label2;

    /**
     * Constructs a LeftPanel with a layout and appearance to display dice rolls and win totals.
     */
    public LeftPanel() {
        setLayout(new GridLayout(3, 0));
        setBackground(new Color(51, 0, 111));

        JPanel dicePanel = createDicePanel();
        JPanel pointPanel = createPointPanel();
        JPanel winsPanel = createWinsPanel();

        add(dicePanel);
        add(pointPanel);
        add(winsPanel);
    }

    /**
     * Creates the panel for displaying dice values and icons.
     *
     * @return The JPanel for displaying dice values and icons.
     */
    private JPanel createDicePanel() {
        JPanel dicePanel = new JPanel(new GridLayout(2, 2, 10, 0));
        dicePanel.setBackground(new Color(51, 0, 111));
        dicePanel.setOpaque(true);

        ImageIcon initialDice1 = loadDiceImage(1);
        label1 = new JLabel();
        label1.setIcon(initialDice1);
        label1.setHorizontalAlignment(JLabel.CENTER);
        dicePanel.add(label1);

        ImageIcon initialDice2 = loadDiceImage(1);
        label2 = new JLabel();
        label2.setIcon(initialDice2);
        label2.setHorizontalAlignment(JLabel.CENTER);
        dicePanel.add(label2);

        JPanel panel1 = new JPanel(new GridLayout(2, 0));
        JLabel field1 = new JLabel("Die 1:");
        field1.setForeground(new Color(232, 211, 162));
        field1.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(field1);
        die1Field = new JTextField(3);
        die1Field.setEditable(false);
        panel1.add(die1Field);
        dicePanel.add(panel1);
        panel1.setBorder(BorderFactory.createEmptyBorder(0, 40, 30, 40));
        panel1.setBackground(new Color(51, 0, 111));

        JPanel panel2 = new JPanel(new GridLayout(2, 0));
        JLabel field2 = new JLabel("Die 2:");
        field2.setForeground(new Color(232, 211, 162));
        field2.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(field2);
        die2Field = new JTextField(3);
        die2Field.setEditable(false);
        panel2.add(die2Field);
        dicePanel.add(panel2);
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 40, 30, 40));
        panel2.setBackground(new Color(51, 0, 111));

        return dicePanel;
    }

    /**
     * Creates the panel for displaying current roll information.
     *
     * @return The JPanel for displaying current roll information.
     */
    private JPanel createPointPanel() {
        JPanel pointPanel = new JPanel(new GridLayout(2, 0, 0, 40));
        pointPanel.setBackground(new Color(51, 0, 111));
        pointPanel.setOpaque(true);

        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(232, 211, 162)), "Current Roll");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleColor(new Color(232, 211, 162));
        EmptyBorder emptyBorder = (EmptyBorder) BorderFactory.createEmptyBorder(20, 50, 20, 60);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, border);
        pointPanel.setBorder(compoundBorder);

        totalField = new JTextField(5);
        totalField.setEditable(false);
        pointField = new JTextField(5);
        pointField.setEditable(false);

        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setForeground(new Color(232, 211, 162));
        pointPanel.add(totalLabel);
        pointPanel.add(totalField);

        JLabel pointLabel = new JLabel("Point:");
        pointLabel.setForeground(new Color(232, 211, 162));
        pointPanel.add(pointLabel);
        pointPanel.add(pointField);

        return pointPanel;
    }

    /**
     * Creates the panel for displaying win totals.
     *
     * @return The JPanel for displaying win totals.
     */
    private JPanel createWinsPanel() {
        JPanel winsPanel = new JPanel(new GridLayout(2, 0, 0, 40));
        winsPanel.setBackground(new Color(51, 0, 111));
        winsPanel.setOpaque(true);

        TitledBorder border1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(232, 211, 162)), "Win Totals");
        border1.setTitleJustification(TitledBorder.CENTER);
        border1.setTitleColor(new Color(232, 211, 162));
        EmptyBorder emptyBorder1 = (EmptyBorder) BorderFactory.createEmptyBorder(20, 50, 20, 60);
        CompoundBorder compoundBorder1 = BorderFactory.createCompoundBorder(emptyBorder1, border1);
        winsPanel.setBorder(compoundBorder1);

        playerWinField = new JTextField(5);
        houseWinField = new JTextField(5);

        JLabel playerWinLabel = new JLabel("Player Win Total:");
        playerWinLabel.setForeground(new Color(232, 211, 162));
        winsPanel.add(playerWinLabel);
        winsPanel.add(playerWinField);

        JLabel houseWinLabel = new JLabel("House Win Total:");
        houseWinLabel.setForeground(new Color(232, 211, 162));
        winsPanel.add(houseWinLabel);
        winsPanel.add(houseWinField);

        return winsPanel;
    }

    /**
     * Updates the displayed values of the dice in the UI.
     *
     * @param die1Value The value of the first die.
     * @param die2Value The value of the second die.
     */
    public static void updateDiceValues(int die1Value, int die2Value) {
        if (die1Value == 0 && die2Value == 0) {
            label1.setIcon(loadDiceImage(1));
            label2.setIcon(loadDiceImage(1));
        } else {
            label1.setIcon(loadDiceImage(die1Value));
            label2.setIcon(loadDiceImage(die2Value));
        }
        die1Field.setText(String.valueOf(die1Value));
        die2Field.setText(String.valueOf(die2Value));
    }

    /**
     * Updates the total field in the UI.
     *
     * @param total The total value of the dice.
     */
    public void updateTotalField(int total) {
        totalField.setText(String.valueOf(total));
    }

    /**
     * Updates the point field in the UI.
     *
     * @param point The point value in the current roll.
     */
    public void updatePointField(int point) {
        pointField.setText(String.valueOf(point));
    }

    /**
     * Updates the win fields in the UI.
     *
     * @param playerWins The total number of player wins.
     * @param houseWins  The total number of house wins.
     */
    public void updateWinFields(int playerWins, int houseWins) {
        SwingUtilities.invokeLater(() -> {
            playerWinField.setText(String.valueOf(playerWins));
            houseWinField.setText(String.valueOf(houseWins));
        });
    }

    /**
     * Loads the image of a die based on its value.
     *
     * @param value The value of the die.
     * @return The ImageIcon representing the die image.
     */
    private static ImageIcon loadDiceImage(int value) {
        String imageName = "src/icons/dice" + value + ".jpg";
        return new ImageIcon(imageName);
    }
}
