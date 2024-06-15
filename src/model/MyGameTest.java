package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyGameTest {

    private MyGame game;

    @BeforeEach
    public void setUp() {
        game = new MyGame();
    }

    @Test
    void testSetUp() {
        assertEquals(0, game.getBank());
        assertEquals(0, game.getBet());
        assertEquals(0, game.getPlayerWins());
        assertEquals(0, game.getHouseWins());
    }

    @Test
    void testBetting() {
        int bet = 20;
        assertEquals(0, game.getBet());

        game.setBet(bet);
        assertEquals(bet, game.getBet());
    }

    @Test
    public void testRollDice() {
        game.rollDice();

        // Verify dice values
        assertTrue(game.getDie1() >= 1 && game.getDie1() <= 6);
        assertTrue(game.getDie2() >= 1 && game.getDie2() <= 6);

        // Verify total
        int expectedTotal = game.getDie1() + game.getDie2();
        assertEquals(expectedTotal, game.getTotal());
    }

    @Test
    public void testWinsAndLosses() {
        // Roll until win or loss
        while(!game.isPlayerWin() && !game.isPlayerLoss()) {
            game.rollDice();
        }

        if(game.isPlayerWin()) {
            // Verify player win count increased
            assertTrue(game.getPlayerWins() > 0);
        } else {
            // Verify house win count increased
            assertTrue(game.getHouseWins() > 0);
        }
    }

    @Test
    public void testPoint() {
        int initialPoint = game.getPoint();

        game.rollDice();

        if(game.getTotal() == 7 || game.getTotal() == 11) {
            assertEquals(initialPoint, game.getPoint());
        }
        else {
            assertNotEquals(initialPoint, game.getPoint());
        }
    }

    @Test
    public void testBank() {
        int bank = 100;
        int bet = 10;

        game.setBank(bank);
        game.setBet(bet);

        int initialBank = bank;

        // Roll until win or loss
        while(!game.isPlayerWin() && !game.isPlayerLoss()) {
            game.rollDice();
        }

        if(game.isPlayerWin()) {
            assertEquals(initialBank + bet, game.getBank());
        }
        else {
            assertEquals(initialBank - bet, game.getBank());
        }
    }

    @Test
    public void testReset() {
        // Roll dice
        game.rollDice();

        // Reset game
        game.resetGame();

        // Verify reset fields
        assertEquals(0, game.getPoint());
        assertEquals(0, game.getBet());
    }

    @Test
    void testSetInitialPlayerWins() {
        int initialPlayerWins = 5;
        game.setInitialPlayerWins(initialPlayerWins);
        assertEquals(initialPlayerWins, game.getInitialPlayerWins());
    }

    @Test
    void testSetInitialHouseWins() {
        int initialHouseWins = 3;
        game.setInitialHouseWins(initialHouseWins);
        assertEquals(initialHouseWins, game.getInitialHouseWins());
    }

    @Test
    void testSetPoint() {
        int pointValue = 8;
        game.setPoint(pointValue);
        assertEquals(pointValue, game.getPoint());
    }

    @Test
    void testSetPlayerWins() {
        int playerWins = 10;
        game.setPlayerWins(playerWins);
        assertEquals(playerWins, game.getPlayerWins());
    }

    @Test
    void testSetHouseWins() {
        int houseWins = 7;
        game.setHouseWins(houseWins);
        assertEquals(houseWins, game.getHouseWins());
    }

    @Test
    void testSetBank() {
        int bankValue = 500;
        game.setBank(bankValue);
        assertEquals(bankValue, game.getBank());
    }

    @Test
    void testSetBet() {
        int betValue = 20;
        game.setBet(betValue);
        assertEquals(betValue, game.getBet());
    }
}