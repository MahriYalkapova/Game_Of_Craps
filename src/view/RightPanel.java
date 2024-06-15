package view;

import model.MyGame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The RightPanel class represents the user interface panel located on the right
 * side of the application window. It provides functionality for managing the player's bank
 * and placing bets during the game. The class includes components for setting the bank amount,
 * displaying and updating the bet amount, and buttons for placing different bet values.
 * Implements the Observer interface to stay updated on changes in the game model.
 *
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class RightPanel extends JPanel implements Observer {

    /**
     * The JTextField displaying and allowing editing of the player's bank amount.
     */
    private JTextField bankField;

    /**
     * The JTextField displaying the current bet amount.
     */
    private JTextField betField;

    /**
     * The JButton for setting the bank amount.
     */
    private JButton setBankButton;

    /**
     * Buttons for placing different bet values.
     */
    private JButton oneButton, fiveButton, tenButton, fiftyButton, hundredButton, fiveHundredButton;

    /**
     * Boolean flag indicating whether the bank field is enabled for editing.
     */
    private boolean bankFieldEnabled = true;

    /**
     * The MyGame instance representing the game model.
     */
    private MyGame myGame;

    /**
     * Constructs a RightPanel with a reference to the game model.
     *
     * @param myGame The MyGame instance representing the game model.
     */
    public RightPanel(MyGame myGame) {
        this.myGame = myGame;
        myGame.addObserver(this);
        setLayout(new GridLayout(2, 0));
        setBackground(new Color(51, 0, 111));
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(20, 55, 20, 55));

        JPanel bankPanel = createTitledBorderPanel("Bank", 20, 0, 100, 0);
        JPanel betPanel = createTitledBorderPanel("Bet", 0, 0, 0, 0);

        add(bankPanel);
        add(betPanel);

        createBankComponents(bankPanel);
        createBetComponents(betPanel);
    }

    /**
     * Adds components related to managing the player's bank.
     *
     * @param bankPanel The JPanel dedicated to bank-related components.
     */
    private void createBankComponents(JPanel bankPanel) {
        JPanel bankTextField = new JPanel(new FlowLayout());
        bankTextField.setBackground(new Color(51, 0, 111));
        bankTextField.setOpaque(true);
        bankField = new JTextField(7);
        JLabel dollarSign = new JLabel("$");
        dollarSign.setForeground(new Color(232, 211, 162));
        bankTextField.add(dollarSign);
        bankTextField.add(bankField);
        bankPanel.add(bankTextField);

        JPanel setBankPanel = new JPanel();
        setBankPanel.setBackground(new Color(51, 0, 111));
        setBankPanel.setOpaque(true);
        setBankButton = new JButton("Set Bank");
        setBankButton.addActionListener(e -> setBank());
        setBankButton.setEnabled(true);
        setBankButton.setPreferredSize(new Dimension(90, 25));
        setBankPanel.add(setBankButton);
        bankPanel.add(setBankPanel);
    }

    /**
     * Gets the "Set Bank" button.
     *
     * @return The JButton instance representing the "Set Bank" button.
     */
    public JButton getSetBankButton() {
        return setBankButton;
    }

    /**
     * Adds components related to managing the player's bets.
     *
     * @param betPanel The JPanel dedicated to bet-related components.
     */
    private void createBetComponents(JPanel betPanel) {
        JPanel betFieldPanel = new JPanel(new FlowLayout());
        betField = new JTextField("0",5);
        betField.setEditable(false);
        JLabel dollarSign1 = new JLabel("$");
        dollarSign1.setForeground(new Color(232, 211, 162));
        betFieldPanel.add(dollarSign1);
        betFieldPanel.add(betField);
        betFieldPanel.setBackground(new Color(51, 0, 111));
        betFieldPanel.setOpaque(true);
        betPanel.add(betFieldPanel);

        oneButton = createBetButton("1");
        fiveButton = createBetButton("5");
        tenButton = createBetButton("10");
        fiftyButton = createBetButton("50");
        hundredButton = createBetButton("100");
        fiveHundredButton = createBetButton("500");

        addBetButton(betPanel, oneButton);
        addBetButton(betPanel, fiveButton);
        addBetButton(betPanel, tenButton);
        addBetButton(betPanel, fiftyButton);
        addBetButton(betPanel, hundredButton);
        addBetButton(betPanel, fiveHundredButton);
    }

    /**
     * Sets the bank amount based on the value entered in the bank field.
     * Displays an error message if the input is not a valid integer.
     */
    public void setBank() {
        if (!bankFieldEnabled) {
            return;
        }

        try {
            int bankValue = Integer.parseInt(bankField.getText());
            if (bankValue < 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid bank value.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                myGame.setBank(bankValue);
                bankField.setEditable(false);
                setBankButton.setEnabled(false);

                enableBetButtons(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid bank value.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sets the editable property of the bank field.
     *
     * @param editable true if the bank field should be editable, false otherwise.
     */
    public void setBankFieldEditable(boolean editable) {
        bankField.setEditable(editable);
    }

    /**
     * Resets the bet field to its default value.
     */
    public void resetBetField() {
        betField.setText("0");
    }

    /**
     * Resets the bank field to its default value.
     */
    public void resetBankField() {
        bankField.setText("0");
    }

    /**
     * Enables or disables the bet buttons.
     *
     * @param enabled true to enable the bet buttons, false to disable.
     */
    public void enableBetButtons(boolean enabled) {
        oneButton.setEnabled(enabled);
        fiveButton.setEnabled(enabled);
        tenButton.setEnabled(enabled);
        fiftyButton.setEnabled(enabled);
        hundredButton.setEnabled(enabled);
        fiveHundredButton.setEnabled(enabled);
    }

    /**
     * Creates a JButton for a specific bet value.
     *
     * @param label The label to be displayed on the button.
     * @return The created JButton instance.
     */
    private JButton createBetButton(String label) {
        JButton button = new JButton();
        button.setText("+$" + label);
        button.addActionListener(e -> betButton(label));
        button.setEnabled(false);
        button.setPreferredSize(new Dimension(70, 20));
        return button;
    }

    /**
     * Adds a bet button to the bet panel.
     *
     * @param betPanel The JPanel containing bet-related components.
     * @param button   The JButton to be added.
     */
    private void addBetButton(JPanel betPanel, JButton button) {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(51, 0, 111));
        buttonPanel.setOpaque(true);
        buttonPanel.add(button);
        betPanel.add(buttonPanel);
    }

    /**
     * Handles the action when a bet button is clicked.
     *
     * @param label The label of the clicked bet button.
     */
    public void betButton(String label) {
        try {
            int betValue = Integer.parseInt(label);
            int currentBet = Integer.parseInt(betField.getText());
            int newBet = currentBet + betValue;
            betField.setText(String.valueOf(newBet));

            myGame.setBet(newBet);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid bet value.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates the observer (this panel) with the latest bank value from the game model.
     *
     * @param o   The Observable object (MyGame instance).
     * @param arg The argument passed by the Observable (not used in this implementation).
     */
    @Override
    public void update(Observable o, Object arg) {
        bankField.setText(String.valueOf(myGame.getBank()));
    }

    /**
     * Creates a titled border panel with specified title and border margins.
     *
     * @param title  The title of the titled border.
     * @param top    The top margin of the border.
     * @param left   The left margin of the border.
     * @param bottom The bottom margin of the border.
     * @param right  The right margin of the border.
     * @return The JPanel with the titled border.
     */
    private JPanel createTitledBorderPanel(String title, int top, int left, int bottom, int right) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(new Color(51, 0, 111));
        panel.setOpaque(true);

        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(232, 211, 162)), title);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleColor(new Color(232, 211, 162));
        EmptyBorder emptyBorder = (EmptyBorder) BorderFactory.createEmptyBorder(top, left, bottom, right);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, border);
        panel.setBorder(compoundBorder);

        return panel;
    }
}
