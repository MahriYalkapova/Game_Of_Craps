package controller;

import view.MyFrame;

/**
 * The main class for the Craps game application.
 * It initializes the main frame and shows the start popup.
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class CrapsMain {

    /**
     * The main entry point for the Craps game application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        myFrame.showStartPopup();
    }
}
