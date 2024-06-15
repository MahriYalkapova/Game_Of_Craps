package view;

import model.MyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The BottomPanel class represents the user interface panel located at the bottom
 * of the application window. It provides functionality for playing the game again.
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class BottomPanel extends JPanel {

    /**
     * The background color of the panel.
     */
    private static final Color PANEL_BACKGROUND_COLOR = new Color(51, 0, 111);

    /**
     * The button that allows the user to play the game again.
     */
    private JButton playAgainButton;

    /**
     * The MyGame instance representing the game model.
     */
    final private MyGame myGame;

    /**
     * The MyFrame instance representing the main application frame.
     */
    final private MyFrame myFrame;

    /**
     * Constructs a BottomPanel with references to the game model and the main frame.
     *
     * @param myGame   The MyGame instance representing the game model.
     * @param myFrame  The MyFrame instance representing the main application frame.
     */
    public BottomPanel(MyGame myGame, MyFrame myFrame) {
        this.myGame = myGame;
        this.myFrame = myFrame;

        initializePanel();
        initializePlayAgainButton();
    }

    /**
     * Initializes the layout and appearance of the panel.
     */
    private void initializePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(PANEL_BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }

    /**
     * Initializes the "Play Again" button and adds it to the panel.
     */
    private void initializePlayAgainButton() {
        playAgainButton = createButton("Play Again", KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK, e -> playAgain());
        add(playAgainButton);
        playAgainButton.setEnabled(false);
    }

    /**
     * Resets the game and updates the UI when the "Play Again" button is clicked.
     */
    private void playAgain() {
        myGame.resetGame();
        myFrame.startGame();
        myFrame.getRightPanel().enableBetButtons(true);
        playAgainButton.setEnabled(false);
        myFrame.getRightPanel().getSetBankButton().setEnabled(false);
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

        Action action = createPlayAgainAction();
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, modifier));

        button.getActionMap().put(action.getValue(Action.ACCELERATOR_KEY), action);
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                (KeyStroke) action.getValue(Action.ACCELERATOR_KEY), action.getValue(Action.ACCELERATOR_KEY)
        );

        button.setEnabled(true);
        return button;
    }

    /**
     * Creates an action for the "Play Again" button.
     *
     * @return The created AbstractAction instance.
     */
    private Action createPlayAgainAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAgain();
            }
        };
    }

    /**
     * Gets the "Play Again" button.
     *
     * @return The JButton instance representing the "Play Again" button.
     */
    public JButton getPlayAgainButton() {
        return playAgainButton;
    }
}
