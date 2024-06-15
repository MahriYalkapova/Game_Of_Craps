package model;

import java.util.Random;
import java.util.Scanner;

public class CrapsGame {
    private static final int MIN_BET = 1;
    private static final int MAX_BET = 500;

    private int bankAccount;
    private int bet;
    private int die1;
    private int die2;

    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public CrapsGame(int initialBankAccount) {
        bankAccount = initialBankAccount;
    }

    public CrapsGame() {
        System.out.println("Enter your bank: ");
        try {
            bankAccount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Invalid bank account. Enter a valid bank account: ");
            initializeBank();
        }
    }

    public void initializeBank() {
        try {
            bankAccount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Invalid bank account. Enter a valid bank account: ");
            initializeBank();
        }
    }

    public void setBank(int amount) {
        bankAccount = amount;
    }

    public int getBank() {
        return bankAccount;
    }

    public void setBet(int amount) {
        bet = amount;
    }

    public int getBet() {
        return bet;
    }

    public void takeBet() {
        System.out.print("Enter your bet ($" + MIN_BET + " - $" + MAX_BET + "): ");
        while (true) {
            try {
                bet = Integer.parseInt(scanner.nextLine());
                if (bet < MIN_BET || bet > MAX_BET || bet > bankAccount) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid bet. Enter a valid bet: ");
            }
        }
    }

    public void rollDice() {
        die1 = random.nextInt(6) + 1;
        die2 = random.nextInt(6) + 1;

        while (die1 + die2 > 12) {
            die1 = random.nextInt(6) + 1;
            die2 = random.nextInt(6) + 1;
        }
    }

    public void startGame() {
        while (bankAccount > 0) {
            System.out.println("Bank Account: $" + bankAccount);
            takeBet();

            int point = playFirstRoll();

            if (point == 7 || point == 11) {
                win();
            } else if (point == 2 || point == 3 || point == 12) {
                lose();
            } else {
                playNextPoint(point);
            }
        }

        System.out.println("Game Over. Your bank account is empty.");
    }

    private int playFirstRoll() {
        rollDice();
        int sum = getTotal();

        System.out.println("First Roll: " + getDie1() + " + " + getDie2() + " = " + sum);

        return sum;
    }

    private void playNextPoint(int point) {
        System.out.println("Point is set to " + point);

        while (true) {
            int roll = rollDie() + rollDie();
            System.out.println("Roll: " + roll);

            if (roll == 7) {
                lose();
                break;
            } else if (roll == point) {
                win();
                break;
            }
        }
    }

    private void win() {
        bankAccount += bet;
        System.out.println("You win! Bank Account: $" + bankAccount);
        playAgain();
    }

    private void lose() {
        bankAccount -= bet;
        System.out.println("You lose. Bank Account: $" + bankAccount);
        playAgain();
    }

    public int rollDie() {
        rollDice();
        return getTotal();
    }

    public int getDie1() {
        return die1;
    }

    public int getDie2() {
        return die2;
    }

    public int getTotal() {
        return getDie1() + getDie2();
    }

    public void playAgain() {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        if (response.equals("yes")) {
            resetGame();
        } else {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }

    private void resetGame() {
        bet = 0;
        die1 = 0;
        die2 = 0;
        startGame();
    }

    public static void main(String[] args) {
        CrapsGame crapsGame = new CrapsGame();
        crapsGame.startGame();
    }
}
