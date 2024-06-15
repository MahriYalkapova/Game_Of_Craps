package model;

import java.util.Observable;
import java.util.Random;

/**
 * The MyGame class represents the model for the "Game of Craps." It manages the game state,
 * including dice rolls, player wins, house wins, the bank, bets, and various game-related parameters.
 * The class extends Observable to notify observers about state changes.
 *
 * @author Mahri Yalkapova
 * @version Fall 2023
 */
public class MyGame extends Observable {

    /**
     * Random number generator for simulating dice rolls.
     */
    final private Random random = new Random();

    /**
     * The value of the first die after the last roll.
     */
    private int die1;

    /**
     * The value of the second die after the last roll.
     */
    private int die2;

    /**
     * The point value established in the game.
     */
    private int point;

    /**
     * The count of player wins in the current game session.
     */
    private int playerWins;

    /**
     * The count of house wins in the current game session.
     */
    private int houseWins;

    /**
     * The initial count of player wins at the start of the game.
     */
    private int initialPlayerWins;

    /**
     * The initial count of house wins at the start of the game.
     */
    private int initialHouseWins;

    /**
     * The current bank balance.
     */
    private int bank;

    /**
     * The current bet amount.
     */
    private int bet;

    /**
     * The previous count of player wins before the current game session.
     */
    private int previousPlayerWins = 0;

    /**
     * The previous count of house wins before the current game session.
     */
    private int previousHouseWins = 0;


    /**
     * Constructs a MyGame instance, initializing initial player and house wins to zero.
     */
    public MyGame() {
        initialPlayerWins = 0;
        initialHouseWins = 0;
    }

    /**
     * Rolls the dice, evaluates the result, and updates the game state accordingly.
     * Notifies observers about changes.
     */
    public void rollDice() {
        die1 = random.nextInt(6) + 1;
        die2 = random.nextInt(6) + 1;

        int total = die1 + die2;

        if (point == 0) {
            if (total == 7 || total == 11) {
                playerWins++;
                updateBank(bet);
            } else if (total == 2 || total == 3 || total == 12) {
                houseWins++;
                updateBank(-bet);
            } else {
                point = total;
            }
        } else {
            if (total == point) {
                playerWins++;
                updateBank(bet);
                point = 0;
            } else if (total == 7) {
                houseWins++;
                updateBank(-bet);
                point = 0;
            }
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Gets the initial count of player wins at the start of the game.
     *
     * @return The initial count of player wins.
     */
    public int getInitialPlayerWins() {
        return initialPlayerWins;
    }

    /**
     * Gets the initial count of house wins at the start of the game.
     *
     * @return The initial count of house wins.
     */
    public int getInitialHouseWins() {
        return initialHouseWins;
    }

    /**
     * Sets the initial count of player wins.
     *
     * @param initialPlayerWins The new initial count of player wins.
     */
    public void setInitialPlayerWins(int initialPlayerWins) {
        this.initialPlayerWins = initialPlayerWins;
    }

    /**
     * Sets the initial count of house wins.
     *
     * @param initialHouseWins The new initial count of house wins.
     */
    public void setInitialHouseWins(int initialHouseWins) {
        this.initialHouseWins = initialHouseWins;
    }

    /**
     * Gets the value of the first die after the last roll.
     *
     * @return The value of the first die.
     */
    public int getDie1() {
        return die1;
    }

    /**
     * Gets the value of the second die after the last roll.
     *
     * @return The value of the second die.
     */
    public int getDie2() {
        return die2;
    }

    /**
     * Gets the total value of the dice after the last roll.
     *
     * @return The total value of the dice.
     */
    public int getTotal() {
        return die1 + die2;
    }

    /**
     * Gets the point value established in the game.
     *
     * @return The point value.
     */
    public int getPoint() {
        return point;
    }

    /**
     * Gets the count of player wins in the current game session.
     *
     * @return The count of player wins.
     */
    public int getPlayerWins() {
        return playerWins;
    }

    /**
     * Gets the count of house wins in the current game session.
     *
     * @return The count of house wins.
     */
    public int getHouseWins() {
        return houseWins;
    }

    /**
     * Checks if the player has won in the current game session.
     *
     * @return True if the player has won, false otherwise.
     */
    public boolean isPlayerWin() {
        return playerWins > 0 && playerWins != previousPlayerWins;
    }

    /**
     * Checks if the player has lost in the current game session.
     *
     * @return True if the player has lost, false otherwise.
     */
    public boolean isPlayerLoss() {
        return houseWins > 0 && houseWins != previousHouseWins;
    }

    /**
     * Gets the current bank balance.
     *
     * @return The current bank balance.
     */
    public int getBank() {
        return bank;
    }

    /**
     * Sets the bank balance to a new value, updating observers if the new value is valid.
     *
     * @param newBank The new bank balance.
     */
    public void setBank(int newBank) {
        if (newBank > 0) {
            bank = newBank;
            setChanged();
            notifyObservers("BankChanged");
        } else {
            System.out.println("Invalid bank value");
        }
    }

    /**
     * Gets the current bet amount.
     *
     * @return The current bet amount.
     */
    public int getBet() {
        return bet;
    }

    /**
     * Sets the bet amount to a new value, updating observers.
     *
     * @param newBet The new bet amount.
     */
    public void setBet(int newBet) {
        this.bet = newBet;
        setChanged();
        notifyObservers();
    }

    /**
     * Handles a win event, updating initial counts and previous counts.
     */
    public void handleWin() {
        if (bet > 0) {
            if (isPlayerWin() && initialPlayerWins == playerWins) {
                initialPlayerWins++;
            }
        }

        previousPlayerWins = playerWins;
        previousHouseWins = houseWins;
    }

    /**
     * Updates the bank balance by a specified amount.
     *
     * @param amount The amount by which to update the bank balance.
     */
    private void updateBank(int amount) {
        bank += amount;
        setChanged();
        notifyObservers("BankChanged");
    }

    /**
     * Resets the game state, including the point and bet. Notifies observers about the reset.
     */
    public void resetGame() {
        point = 0;
        bet = 0;
        setChanged();
        notifyObservers("ResetGame");
    }

    /**
     * Sets the total value to a new value, updating observers.
     *
     * @param newTotal The new total value.
     */
    public void setTotal(int newTotal) {
        setChanged();
        notifyObservers("TotalChanged");
    }

    /**
     * Sets the point value to a new value, updating observers.
     *
     * @param newPoint The new point value.
     */
    public void setPoint(int newPoint) {
        point = newPoint;
        setChanged();
        notifyObservers("PointChanged");
    }

    /**
     * Sets the count of house wins to a new value, updating observers.
     *
     * @param newHouseWins The new count of house wins.
     */
    public void setHouseWins(int newHouseWins) {
        houseWins = newHouseWins;
        setChanged();
        notifyObservers("HouseWinsChanged");
    }

    /**
     * Sets the count of player wins to a new value, updating observers.
     *
     * @param newPlayerWins The new count of player wins.
     */
    public void setPlayerWins(int newPlayerWins) {
        playerWins = newPlayerWins;
        setChanged();
        notifyObservers("PlayerWinsChanged");
    }

    /**
     * Resets the values of the dice. Notifies observers about the dice reset.
     */
    public void resetDice() {
        die1 = 0;
        die2 = 0;
        setChanged();
        notifyObservers("DiceChanged");
    }

    /**
     * Resets the total value. Notifies observers about the total reset.
     */
    public void resetTotal() {
        setTotal(0);
    }

    /**
     * Resets the point value. Notifies observers about the point reset.
     */
    public void resetPoint() {
        setPoint(0);
    }

    /**
     * Gets the previous count of player wins before the current game session.
     *
     * @return The previous count of player wins.
     */
    public int getPreviousPlayerWins() {
        return previousPlayerWins;
    }

    /**
     * Gets the previous count of house wins before the current game session.
     *
     * @return The previous count of house wins.
     */
    public int getPreviousHouseWins() {
        return previousHouseWins;
    }

}
