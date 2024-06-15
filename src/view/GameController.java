package view;

import model.CrapsGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {

    private CrapsGame crapsGame;
    private MyFrame myFrame;

    public GameController(MyFrame myFrame) {
        this.myFrame = myFrame;
        crapsGame = new CrapsGame();

        JButton rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crapsGame.rollDie();
                updateLabels();
            }
        });

        myFrame.addRollDiceButton(rollDiceButton);
        myFrame.setVisible(true);

        crapsGame.startGame();
    }

    public void updateLabels() {
        myFrame.updateLabels(crapsGame.getDie1(), crapsGame.getDie2());
    }

    public static void main(String[] args) {
        new GameController(new MyFrame());
    }
}
