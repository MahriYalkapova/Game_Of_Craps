package view;

import model.MyGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 * The MyFrame class represents the main application frame for the Game of Craps. It includes
 * components such as the menu panel, top panel, left panel, right panel, and bottom panel. The
 * class implements the MenuListener and Observer interfaces to handle menu actions and observe
 * changes in the game model.
 *
 * The frame provides functionalities such as starting the game, continuing the game, resetting the
 * session, and exiting the game. It also displays dialogs with information about the game, rules,
 * and shortcuts.
 *
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class MyFrame extends JFrame implements MenuPanel.MenuListener, Observer {
    /**
     * The menu panel containing the game menu options.
     */
    private MenuPanel menuPanel;

    /**
     * The instance of the game model.
     */
    private MyGame myGame;

    /**
     * The top panel containing the "Roll Dice" button.
     */
    private TopPanel topPanel;

    /**
     * The right panel containing components for managing bank and bets.
     */
    private RightPanel rightPanel;

    /**
     * The bottom panel containing the "Play Again" button.
     */
    private BottomPanel bottomPanel;

    /**
     * The left panel displaying dice values, totals, and game stats.
     */
    private LeftPanel leftPanel;

    /**
     * The previous bank value to track changes.
     */
    private int previousBankValue = 0;

    /**
     * Flag indicating whether the game is currently active.
     */
    private boolean gameActive = false;


    /**
     * Displays a popup informing the player to set their bet and bank amount before playing.
     */
    public void showStartPopup() {
        JOptionPane.showMessageDialog(null, "Please set your bet and bank amount before playing.", "Game of Craps",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Constructs a MyFrame with the necessary components and settings.
     */
    public MyFrame() {
        setTitle("Game of Craps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);

        menuPanel = new MenuPanel(this);
        setJMenuBar(menuPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(51, 0, 111));

        myGame = new MyGame();
        leftPanel = new LeftPanel();
        topPanel = new TopPanel(myGame, leftPanel, this);
        rightPanel = new RightPanel(myGame);
        bottomPanel = new BottomPanel(myGame, this);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    /**
     * Updates the observer (this frame) based on changes in the game model.
     *
     * @param o   The Observable object (MyGame instance).
     * @param arg The argument passed by the Observable (not used in this implementation).
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String && arg.equals("BankChanged")) {
            handleBankChange();
        }
    }

    /**
     * Handles the bank change event, updating the UI components accordingly.
     */
    private void handleBankChange() {
        int currentBankValue = myGame.getBank();

        if (currentBankValue != previousBankValue) {
            if (currentBankValue <= 0) {
                topPanel.getRollButton().setEnabled(false);
                bottomPanel.getPlayAgainButton().setEnabled(true);
            } else {
                topPanel.getRollButton().setEnabled(true);
                bottomPanel.getPlayAgainButton().setEnabled(false);
            }

            previousBankValue = currentBankValue;
        }
    }

    /**
     * Sets the game's active state and enables or disables relevant UI components.
     *
     * @param active true if the game is active, false otherwise.
     */
    private void setGameActive(boolean active) {
        gameActive = active;
        topPanel.getRollButton().setEnabled(active);
        rightPanel.getSetBankButton().setEnabled(!active);
        bottomPanel.getPlayAgainButton().setEnabled(!active);
        rightPanel.enableBetButtons(!active);
    }

    /**
     * Starts the game, checking if the bet and bank amounts are valid.
     */
    public void startGame() {
        int bankValue = myGame.getBank();
        int betValue = myGame.getBet();

        if (betValue == 0 || bankValue == 0) {
            showStartPopup();
            setGameActive(false);
            rightPanel.getSetBankButton().setEnabled(true);
            return;
        }

        rightPanel.getSetBankButton().setEnabled(false);
        setGameActive(true);
    }

    /**
     * Continues the game based on the current game state, displaying messages and updating the UI.
     */
    public void continueGame() {
        if ((myGame.getPlayerWins() == myGame.getInitialPlayerWins())
                && (myGame.getHouseWins() == myGame.getInitialHouseWins())) {
            bottomPanel.getPlayAgainButton().setEnabled(false);
            rightPanel.enableBetButtons(true);
            rightPanel.getSetBankButton().setEnabled(false);
        } else {
            setGameActive(false);
            rightPanel.getSetBankButton().setEnabled(false);
            rightPanel.enableBetButtons(false);

            String message;
            if (myGame.isPlayerWin()) {
                message = "Congratulations! You won!";
                playSound("src/sounds/losing.wav");
                myGame.handleWin();
                myGame.resetDice();
                myGame.resetTotal();
                myGame.resetPoint();
                JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else if (myGame.isPlayerLoss()){
                message = "Sorry! You lost!";
                playSound("src/sounds/win.wav");
                myGame.resetDice();
                myGame.resetTotal();
                myGame.resetPoint();
                JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

            myGame.setBet(0);
            rightPanel.resetBetField();
            bottomPanel.getPlayAgainButton().setEnabled(true);

            if (myGame.isPlayerWin() && myGame.getInitialPlayerWins() == myGame.getPlayerWins()) {
                myGame.setInitialPlayerWins(myGame.getPlayerWins() + 1);
            }

            myGame.setInitialHouseWins(myGame.getHouseWins());
        }
    }

    /**
     * Resets the entire game session, including the bank, bet, and UI components.
     */
    public void resetSession() {
        myGame.setBank(0);
        rightPanel.resetBankField();
        myGame.setBet(0);
        rightPanel.resetBetField();
        topPanel.getRollButton().setEnabled(false);
        bottomPanel.getPlayAgainButton().setEnabled(false);
        rightPanel.getSetBankButton().setEnabled(true);
        rightPanel.enableBetButtons(false);

        leftPanel.updateDiceValues(0, 0);
        leftPanel.updateTotalField(0);
        leftPanel.updatePointField(0);

        rightPanel.setBankFieldEditable(true);

        leftPanel.updateWinFields(0, 0);

        myGame.setPoint(0);
        myGame.setTotal(0);
        myGame.setHouseWins(0);
        myGame.setPlayerWins(0);
    }

    /**
     * Exits the game, prompting the user for confirmation.
     */
    public void exitGame() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Game",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Displays a dialog with information about the game.
     * Includes the developer's name, game version, Java version, and date created.
     */
    public void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
                "Game of Craps\n\n" +
                        "Developer: Mahri Yalkapova\n" +
                        "Version: Fall 2023\n" +
                        "Java Version: Java 21\n" +
                        "Date Created: 12/13/2023\n",
                "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a dialog with information about the game rules.
     */
    public void showRulesDialog() {
        JOptionPane.showMessageDialog(this,
                "Game Rules:\n\n" +
                        "A player rolls two dice where each die has six faces in the usual way (values 1 through 6).\n" +
                        "After the dice have come to rest, the sum of the two upward faces is calculated.\n" +
                        "The first roll/throw:\n" +
                        "  - If the sum is 7 or 11, the player wins.\n" +
                        "  - If the sum is 2, 3, or 12, the player loses (house wins).\n" +
                        "  - If the sum is 4, 5, 6, 8, 9, or 10, that sum becomes the player's 'point'.\n" +
                        "Continue rolling given the player's point:\n" +
                        "  - The player must roll the 'point' total before rolling a 7 to win.\n" +
                        "  - If they roll a 7 before rolling the point value, the player loses (house wins).\n",
                "Rules", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a dialog with information about the game shortcuts.
     */
    public void showShortcutsDialog() {
        JOptionPane.showMessageDialog(this,
                "Game Shortcuts:\n\n" +
                        "   - Start: Ctrl + S\n" +
                        "   - Reset: Ctrl + R\n" +
                        "   - Exit: Ctrl + X\n" +
                        "   - About: Ctrl + A\n" +
                        "   - Rules: Ctrl + R\n" +
                        "   - Shortcuts: Ctrl + C\n" +
                        "   - Roll Dice: Ctrl + D\n" +
                        "   - Play Again: Ctrl + P",
                "Shortcuts", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Gets the reference to the RightPanel instance.
     *
     * @return The RightPanel instance.
     */
    public RightPanel getRightPanel() {
        return rightPanel;
    }

    /**
     * Plays a sound given the file path.
     *
     * @param filePath The path to the sound file.
     */
    public void playSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
