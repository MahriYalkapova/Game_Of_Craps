package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The MenuPanel class represents the menu bar in the application window. It provides
 * options related to starting and resetting the game, as well as accessing help and information.
 * The class includes an interface for menu event listeners.
 *
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class MenuPanel extends JMenuBar {

    /**
     * The interface for menu event listeners.
     */
    public interface MenuListener {
        /**
         * Called when the "Start" menu item is selected.
         */
        void startGame();

        /**
         * Called when the "Reset" menu item is selected.
         */
        void resetSession();

        /**
         * Called when the "Exit" menu item is selected.
         */
        void exitGame();

        /**
         * Called when the "About" menu item is selected.
         */
        void showAboutDialog();

        /**
         * Called when the "Rules" menu item is selected.
         */
        void showRulesDialog();

        /**
         * Called when the "Shortcuts" menu item is selected.
         */
        void showShortcutsDialog();
    }

    /**
     * Constructs a MenuPanel with menu items related to the game and help.
     *
     * @param listener The MenuListener to handle menu events.
     */
    public MenuPanel(MenuListener listener) {
        JMenu gameMenu = new JMenu("Game");

        JMenuItem startItem = new JMenuItem("Start");
        startItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        startItem.addActionListener(e -> listener.startGame());
        gameMenu.add(startItem);

        JMenuItem resetItem = new JMenuItem("Reset");
        resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        resetItem.addActionListener(e -> listener.resetSession());
        gameMenu.add(resetItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        exitItem.addActionListener(e -> listener.exitGame());
        gameMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        aboutItem.addActionListener(e -> listener.showAboutDialog());
        helpMenu.add(aboutItem);

        JMenuItem rulesItem = new JMenuItem("Rules");
        rulesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        rulesItem.addActionListener(e -> listener.showRulesDialog());
        helpMenu.add(rulesItem);

        JMenuItem shortcutsItem = new JMenuItem("Shortcuts");
        shortcutsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        shortcutsItem.addActionListener(e -> listener.showShortcutsDialog());
        helpMenu.add(shortcutsItem);

        add(gameMenu);
        add(helpMenu);
    }
}
