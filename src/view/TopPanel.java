package view;

import model.MyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The TopPanel class represents the user interface panel located at the top
 * of the application window. It provides functionality for rolling the dice during the game.
 * The class includes a "Roll Dice" button and handles the corresponding actions.
 *
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class TopPanel extends JPanel {
    /**
     * The button responsible for triggering the dice roll action.
     */
    private JButton rollButton;

    /**
     * The MyGame instance representing the game model.
     */
    private MyGame myGame;

    /**
     * The LeftPanel instance representing the left UI panel.
     */
    private LeftPanel leftPanel;

    /**
     * The MyFrame instance representing the main application frame.
     */
    private MyFrame myFrame;


    /**
     * Constructs a TopPanel with references to the game model, left panel, and the main frame.
     *
     * @param myGame    The MyGame instance representing the game model.
     * @param leftPanel The LeftPanel instance representing the left UI panel.
     * @param myFrame   The MyFrame instance representing the main application frame.
     */
    public TopPanel(MyGame myGame, LeftPanel leftPanel, MyFrame myFrame) {
        this.myGame = myGame;
        this.leftPanel = leftPanel;
        this.myFrame = myFrame;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(51, 0, 111));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        rollButton = createButton("Roll Dice", KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK, e -> rollDice());
        add(rollButton);
        rollButton.setEnabled(false);
    }

    /**
     * Rolls the dice, updates UI elements, and continues the game.
     */
    private void rollDice() {
        myFrame.playSound("src/sounds/dice.wav");

        myGame.rollDice();
        updateDiceValues();
        updatePointField();
        updateWinFields();
        continueGame();
    }

    /**
     * Updates the displayed dice values based on the game model.
     */
    private void updateDiceValues() {
        int die1Value = myGame.getDie1();
        int die2Value = myGame.getDie2();
        leftPanel.updateDiceValues(die1Value, die2Value);

        int total = myGame.getTotal();
        leftPanel.updateTotalField(total);
    }

    /**
     * Updates the displayed point field based on the game model.
     */
    private void updatePointField() {
        int point = myGame.getPoint();
        leftPanel.updatePointField(point);
    }

    /**
     * Updates the displayed win fields based on the game model.
     */
    private void updateWinFields() {
        int playerWins = myGame.getPlayerWins();
        int houseWins = myGame.getHouseWins();
        leftPanel.updateWinFields(playerWins, houseWins);
    }

    /**
     * Continues the game by invoking the corresponding method in the main frame.
     */
    private void continueGame() {
        myFrame.continueGame();
    }

    /**
     * Creates a button with specified text, mnemonic, modifier, and action listener.
     *
     * @param text            The text displayed on the button.
     * @param mnemonic        The mnemonic key.
     * @param modifier        The key modifier.
     * @param actionListener  The action listener for the button.
     * @return The created JButton instance.
     */
    private JButton createButton(String text, int mnemonic, int modifier, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setMnemonic(mnemonic);
        button.addActionListener(actionListener);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        };
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, modifier));

        button.getActionMap().put(action.getValue(Action.ACCELERATOR_KEY), action);
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                (KeyStroke) action.getValue(Action.ACCELERATOR_KEY), action.getValue(Action.ACCELERATOR_KEY)
        );

        button.setEnabled(true);
        return button;
    }

    /**
     * Gets the "Roll Dice" button.
     *
     * @return The JButton instance representing the "Roll Dice" button.
     */
    public JButton getRollButton() {
        return rollButton;
    }
}
