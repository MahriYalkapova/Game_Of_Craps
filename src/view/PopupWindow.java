package view;

import javax.swing.*;

public class PopupWindow {

    public static void showStartPopup() {
        JOptionPane.showMessageDialog(null, "Please set your bank amount before playing.", "Game of Craps",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void showAboutDialog(JFrame parent) {
        JOptionPane.showMessageDialog(parent, "Game of Craps", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showRulesDialog(JFrame parent) {
        JOptionPane.showMessageDialog(parent, "Game Rules:", "Rules",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
