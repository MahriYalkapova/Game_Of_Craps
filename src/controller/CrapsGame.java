package controller;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CrapsGame extends JFrame
{
    private JMenu 	fileJMenu,
            statsJMenu,
            prefJMenu,
            aboutJMenu;

    private JLabel 	playerJLabel,
            sumJLabel,
            pointJLabel,
            die1JLabel,
            die2JLabel,
            dispPointJLabel = new JLabel("Point"),
            dispPlayerJLabel = new JLabel("Player:"),
            dispSumJLabel = new JLabel("Total Roll"),
            dispDie1JLabel= new JLabel("Die 1 Value"),
            dispDie2JLabel = new JLabel("Die 2 Value"),
            statusJLabel;

    private JMenuItem avgRollsItem = new JMenuItem("Avg Rolls/Game"),
            summaryItem = new JMenuItem("Summary");

    private JButton comeOutButton,
            rollButton,
            newGameButton;

    private JRadioButtonMenuItem[] 	colorItems,
            lookItems;

    private ButtonGroup colorButtonGroup,
            lookButtonGroup;


    private final Color[] colorValue = { 	Color.LIGHT_GRAY,
            Color.WHITE,
            Color.BLACK,
            Color.BLUE,
            Color.GREEN,
            Color.ORANGE};

    private UIManager.LookAndFeelInfo[] looks;
    private String[] lookNames;

    private int myPoint = 0; // point if no win or loss on first roll

    //stats
    private int wins = 0;
    private int totalGames = 0;
    private int rolls = 0;
    private int totalRolls = 0;

    // constants that represent common rolls of the dice
    private final static int SNAKE_EYES = 2;
    private final static int TREY = 3;
    private final static int SEVEN = 7;
    private final static int YO_LEVEN = 11;
    private final static int BOX_CARS = 12;

    private int die1Value;
    private int die2Value;

    /****************************************************
     * Method     : comeOut
     *
     * Purpose    : 	Activated when the player presses the Comeout button
     * 				the sum of the rolled dice is passed in and logic decides
     * 				if the player is a winner, loser, or the game continues into
     * 				point roll depending on the game rules. If continue the value
     * 				myPoint is set.
     *
     * Parameters : sumOfDice- The value generated from the rollDice method.
     *
     * Returns    : N/A
     ****************************************************/
    public void comeOut(int sumOfDice)
    {
        // determine game status and point based on first roll
        switch ( sumOfDice )
        {
            case SEVEN: // win with 7 on first roll
                winner();
                break;
            case YO_LEVEN: // win with 11 on first roll
                winner();
                break;
            case SNAKE_EYES: // lose with 2 on first roll
                loser();
                break;
            case TREY: // lose with 3 on first roll
                loser();
                break;
            case BOX_CARS: // lose with 12 on first roll
                loser();
                break;
            default: // did not win or lose, so remember point
                myPoint = sumOfDice; // remember the point
                continueRoll();
                break; // optional at end of switch
        } // end switch
    }

    /****************************************************
     * Method     : rollAgain
     *
     * Purpose    : 	Activated by the Roll Again button.  The sum of the rollDice
     * 				method is used to determine if value myPoint is matched which will
     * 				result in a winning game or if the sum equals 7 the players loses.
     * 				If neither a 7 or myPoint are met the game will continue meaning the player
     * 				will need to roll again.
     *
     * Parameters : sumOfDice- The value generated from the rollDice method.
     *
     * Returns    : N/A
     ****************************************************/
    public void rollAgain(int sumOfDice)
    {
        // determine game status
        if( sumOfDice == myPoint ) // win by making point
        {
            winner();
        }
        else if( sumOfDice == SEVEN ) // lose by rolling 7 before point
        {
            loser();
        }
        else
        {
            continueRoll(); //shouldnt it pass to next shooter here?
        }
    }

    /****************************************************
     * Method     : rollDice
     *
     * Purpose    : 	Roll dice, calculate sum and display results to the Die 1 Value
     * 				and Die 2 Value and Total Roll value on the screen.
     *
     * Parameters : N/A
     *
     * Returns    : sum -  the sum of die1 and die2.
     ****************************************************/
    public int rollDice()
    {
        // die
        Die die1 = new Die();
        Die die2 = new Die();

        rolls++;

        // roll each die
        die1.roll();
        die2.roll();

        die1Value = die1.getValue();
        die2Value = die2.getValue();

        // sum of die values
        int sum = die1.getValue() + die2.getValue();

        return sum; // return sum of dice
    } // end method rollDice

    /****************************************************
     * Method     : winner
     *
     * Purpose    : 	If the dice roll results in a win, this method will
     * 				increment the win stat, call the gameEnd method for more stat modification,
     * 				reset the buttons to a new game state and inform the player that they won.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public void winner()
    {
        wins++;
        gameEnd();
        statusJLabel.setText("Winner!");
        rollButton.setEnabled(false);
        comeOutButton.setEnabled(false);
        newGameButton.setEnabled(true);
    }

    /****************************************************
     * Method     : continueRoll
     *
     * Purpose    : 	If the dice roll results in a continue, this method will
     * 				display the myPoint value to the player, inform the user to continue rolling the dice,
     * 				set the buttons to a state where only re-rolling is possible.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public void continueRoll()
    {
        pointJLabel.setText(Integer.toString(myPoint));
        statusJLabel.setText("Keep Rolling!");
        comeOutButton.setEnabled(false);
        rollButton.setEnabled(true);
    }

    /****************************************************
     * Method     : loser
     *
     * Purpose    : 	If the dice roll results in a loss, this method will
     * 				call the gameEnd method for more stat modification,
     * 				reset the buttons to a new game state and inform the player that they lost.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public void loser()
    {
        gameEnd();
        statusJLabel.setText("You Lost, Try again!");
        rollButton.setEnabled(false);
        comeOutButton.setEnabled(false);
        newGameButton.setEnabled(true);
    }

    /****************************************************
     * Method     : gameEnd
     *
     * Purpose    : 	This method will modify the stats by enabling the avg rolls/game and summary stats.
     * 				It will then increment the totalGames stat and increment the totalRolls stat by the amount
     * 				of rolls made this game, it will reset the rolls for the next game and reset the myPoint value.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public void gameEnd()
    {
        avgRollsItem.setEnabled(true);
        summaryItem.setEnabled(true);
        totalGames++;
        totalRolls += rolls;
        rolls = 0;
        myPoint = 0;
    }

    /****************************************************
     * Method     : CrapsGameFrame
     *
     * Purpose    : Creates and modifies the game frame depending on the game state.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public CrapsGame()
    {
        super("Craps Game");
        getContentPane().setForeground(new Color(0, 0, 0));

        fileJMenu = fileMenu();
        statsJMenu = statsMenu();
        prefJMenu = prefMenu();
        aboutJMenu = aboutMenu();
        getContentPane().setLayout(null);

        playerJLabel = new JLabel("Select \"New User\" from the File menu");
        playerJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        playerJLabel.setBounds(378, 6, 253, 23);
        getContentPane().add(playerJLabel);
        statusJLabel = new JLabel("Good Luck!");
        statusJLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        statusJLabel.setBounds(316, 40, 273, 32);
        statusJLabel.setVisible(true);

        newGameButton = new JButton("New Game");
        newGameButton.setBounds(10, 283, 126, 24);
        comeOutButton = new JButton("Come out roll");
        comeOutButton.setBounds(146, 284, 126, 23);
        rollButton = new JButton("Roll Dice");
        rollButton.setBounds(276, 284, 97, 23);

        newGameButton.setEnabled(false);
        comeOutButton.setEnabled(false);
        rollButton.setEnabled(false);

        Icon diceImg = new ImageIcon(getClass().getResource("Dice.jpg"));
        JLabel diceLabel = new JLabel(diceImg);
        diceLabel.setBounds(0, 0, 306, 273);
        getContentPane().add(diceLabel);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(SystemColor.activeCaption);
        panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Your Point", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(444, 204, 79, 73);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);

        pointJLabel = new JLabel(" ");
        pointJLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        pointJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pointJLabel.setBounds(6, 16, 63, 46);
        panel_3.add(pointJLabel);
        pointJLabel.setVisible(true);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(SystemColor.activeCaption);
        panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Total Roll", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(326, 206, 79, 73);
        getContentPane().add(panel_2);
        panel_2.setLayout(null);
        sumJLabel = new JLabel(" ");
        sumJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sumJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sumJLabel.setBounds(10, 11, 59, 51);
        panel_2.add(sumJLabel);

        sumJLabel.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaptionBorder);
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Die 1 Value", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(326, 96, 79, 73);
        getContentPane().add(panel);
        panel.setLayout(null);
        die1JLabel = new JLabel(" ");
        die1JLabel.setHorizontalAlignment(SwingConstants.CENTER);
        die1JLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        die1JLabel.setBounds(10, 16, 59, 46);
        panel.add(die1JLabel);
        die1JLabel.setBackground(SystemColor.activeCaptionBorder);
        die1JLabel.setVisible(true);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.activeCaptionBorder);
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Die 2 Value", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(444, 96, 79, 73);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        die2JLabel = new JLabel(" ");
        die2JLabel.setHorizontalAlignment(SwingConstants.CENTER);
        die2JLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        die2JLabel.setBounds(10, 11, 59, 51);
        panel_1.add(die2JLabel);
        die2JLabel.setVisible(true);
        getContentPane().add(statusJLabel);
        getContentPane().add(newGameButton);
        getContentPane().add(comeOutButton);
        getContentPane().add(rollButton);
        getContentPane().add(dispPointJLabel);
        getContentPane().add(dispPlayerJLabel);
        getContentPane().add(dispSumJLabel);
        getContentPane().add(dispDie1JLabel);
        getContentPane().add(dispDie2JLabel);

        JLabel lblShooter = new JLabel("Shooter:");
        lblShooter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblShooter.setBounds(316, 6, 79, 23);
        getContentPane().add(lblShooter);

        newGameButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        comeOutButton.setEnabled(true);
                        newGameButton.setEnabled(false);
                        event.getActionCommand();

                    }
                }
        );

        comeOutButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        int sum = rollDice();
                        sumJLabel.setText(Integer.toString(sum));
                        die1JLabel.setText(Integer.toString(die1Value));
                        die2JLabel.setText(Integer.toString(die2Value));
                        comeOut(sum);
                        event.getActionCommand();
                    }
                }
        );

        rollButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        int sum = rollDice();
                        sumJLabel.setText(Integer.toString(sum));
                        die1JLabel.setText(Integer.toString(die1Value));
                        die2JLabel.setText(Integer.toString(die2Value));
                        rollAgain(sum);
                        event.getActionCommand();

                    }
                }
        );

        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(fileJMenu);
        bar.add(statsJMenu);
        bar.add(prefJMenu);
        bar.add(aboutJMenu);


    }

    /****************************************************
     * Method     : fileMenu
     *
     * Purpose    : 	Allows the player to add a new user to the game
     * 				and exit the game.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public JMenu fileMenu()
    {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        JMenuItem newUserItem = new JMenuItem("New User");
        newUserItem.setMnemonic('n');

        JMenuItem exitItem = new JMenuItem("Exit");
        newUserItem.setMnemonic('Q');

        fileMenu.add(newUserItem);
        fileMenu.add(exitItem);

        newUserItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        newGameButton.setEnabled(true);
                        rollButton.setEnabled(false);
                        comeOutButton.setEnabled(false);

                        die1JLabel.setText(" ");
                        die2JLabel.setText(" ");
                        sumJLabel.setText(" ");
                        pointJLabel.setText(" ");

                        wins = 0;
                        totalGames = 0;
                        totalRolls = 0;
                        die1Value = 0;
                        die2Value = 0;
                        rolls = 0;
                        avgRollsItem.setEnabled(false);
                        summaryItem.setEnabled(false);
                        statusJLabel.setText("Good Luck!");

                        playerJLabel.setText(JOptionPane.showInputDialog("Enter player name:"));
                    }
                }
        );

        exitItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        System.exit(0);
                    }
                }
        );

        return fileMenu;
    }

    /****************************************************
     * Method     : statsMenu
     *
     * Purpose    : 	Displays the game stats to the user, the avg rolls/game and summary menus are
     * 				disabled until at least one game is played so that the program will never attempt
     * 				to divide by zero.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public JMenu statsMenu()
    {
        JMenu statsMenu = new JMenu("Game Stats");
        statsMenu.setMnemonic('G');

        JMenuItem totalGamesItem = new JMenuItem("Total Games");
        totalGamesItem.setMnemonic('g');

        JMenuItem totalWinsItem = new JMenuItem("Total Wins");
        totalWinsItem.setMnemonic('w');

        avgRollsItem.setMnemonic('r');
        summaryItem.setMnemonic('s');

        totalGamesItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        JOptionPane.showMessageDialog(null,	"Total Games Played: " + totalGames, "Statistics", JOptionPane.PLAIN_MESSAGE);
                    }
                }
        );

        totalWinsItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        JOptionPane.showMessageDialog(null,	"Total Games Won: " + wins, "Statistics", JOptionPane.PLAIN_MESSAGE);
                    }
                }
        );

        avgRollsItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        double avgRolls = totalRolls/totalGames;
                        JOptionPane.showMessageDialog(null,	"Average Rolls Per Game: " + avgRolls, "Statistics", JOptionPane.PLAIN_MESSAGE);
                    }
                }
        );

        summaryItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        double avgRolls = totalRolls/totalGames;
                        JOptionPane.showMessageDialog(null,		"Total Games Played: " 	+ totalGames
                                        + 	"\nTotal Games Won: " 	+ wins
                                        + 	"\nAverage Rolls Per Game: " + avgRolls
                                , "Summary", JOptionPane.PLAIN_MESSAGE);
                    }
                }
        );

        statsMenu.add(totalGamesItem);
        statsMenu.add(totalWinsItem);
        statsMenu.add(avgRollsItem);
        if(totalGames == 0)
        {
            avgRollsItem.setEnabled(false);
            summaryItem.setEnabled(false);
        }
        statsMenu.add(summaryItem);

        return statsMenu;
    }


    /****************************************************
     * Method     : prefMenu
     *
     * Purpose    : 	Allows the user to choose different background colors or
     * 				change the look and feel of the program window.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public JMenu prefMenu()
    {
        JMenu prefMenu = new JMenu("Preferences");
        prefMenu.setMnemonic('P');

        //Background menu
        JMenuItem bgMenu = new JMenu("Background Color");
        bgMenu.setMnemonic('c');

        String[] colors = {	"Grey",
                "White",
                "Black",
                "Blue",
                "Green",
                "Orange"};
        colorItems = new JRadioButtonMenuItem[colors.length];
        colorButtonGroup = new ButtonGroup();
        ColorHandler handleColor = new ColorHandler();

        for(int c = 0; c < colors.length; c++)
        {
            colorItems[c] = new JRadioButtonMenuItem(colors[c]);
            bgMenu.add(colorItems[c]);
            colorButtonGroup.add(colorItems[c]);
            colorItems[c].addActionListener(handleColor);
        }

        colorItems[1].setSelected(true);

        prefMenu.add(bgMenu);
        prefMenu.addSeparator();

        //look and feel menu
        JMenuItem lookMenu = new JMenu("Look & Feel");
        lookMenu.setMnemonic('f');

        looks = UIManager.getInstalledLookAndFeels();

        lookNames = new String [looks.length];
        for(int i = 0; i < looks.length; i++)
        {
            lookNames[i] = looks[i].getName();

        }

        lookItems = new JRadioButtonMenuItem[looks.length];
        lookButtonGroup = new ButtonGroup();
        LookHandler handleLook = new LookHandler();

        for(int c = 0; c < looks.length; c++)
        {
            lookItems[c] = new JRadioButtonMenuItem(lookNames[c]);
            lookMenu.add(lookItems[c]);
            lookButtonGroup.add(lookItems[c]);
            lookItems[c].addItemListener(handleLook);
        }

        lookItems[0].setSelected(true);
        prefMenu.add(lookMenu);



        return prefMenu;
    }



    //Action Listener inner class for background colors
    private class ColorHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            for(int count = 0; count< colorItems.length; count++)
            {
                if(colorItems[count].isSelected())
                {
                    getContentPane().setBackground(colorValue[count]);
                    break;
                }
            }
            repaint();
        }
    }

    //Action Listener inner class for the look and feel
    private class LookHandler implements ItemListener
    {
        public void itemStateChanged(ItemEvent event)
        {
            for(int count = 0; count< lookItems.length; count++)
            {
                if(lookItems[count].isSelected())
                {
                    changeTheLookAndFeel(count);
                    //				break;
                    //		event.getActionCommand();
                }
            }
        }
    }

    /****************************************************
     * Method     : changeTheLookAndFeel
     *
     * Purpose    : called by the LookHandler Action Listener.  Will
     * 				change the window to correspond with the look and feel option
     * 				selected by the player.
     *
     * Parameters : the index of the look and feel option selected
     *
     * Returns    : N/A
     ****************************************************/
    private void changeTheLookAndFeel(int value)
    {
        try
        {
            UIManager.setLookAndFeel(looks[value].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /****************************************************
     * Method     : aboutMenu
     *
     * Purpose    : allows the player to view the game instructions and some information
     * 				about the project.
     *
     * Parameters : N/A
     *
     * Returns    : N/A
     ****************************************************/
    public JMenu aboutMenu()
    {
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic('A');

        JMenuItem instrItem = new JMenuItem("Instructions");
        instrItem.setMnemonic('i');

        JMenuItem projItem = new JMenuItem("Project");
        projItem.setMnemonic('p');

        aboutMenu.add(instrItem);
        aboutMenu.add(projItem);


        instrItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        String instructions =
                                "The shooter throws the dice. This initial throw is known as the come out throw.\n\n"
                                        +"If he throws a total of 7 or 11 he immediately wins. This is known as a natural.\n\n"
                                        +"If he throws a total of 2, 3 or 12 he immediately loses. This is known as craps.\n\n"
                                        +"Any other total thrown is known as the player's point and he continues to throw the dice until he either throws his point again or he throws a 7.\n\n"
                                        +"If he throws his point first, he wins. This is known as making the point.  If he throws a 7 first, he loses. This is known as a seven out.";

                        JOptionPane.showMessageDialog(null,	instructions);
                    }
                }
        );

        projItem.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        String projInfo =
                                "Programmer: Anthony Browness\n"
                                        +"CS 1410-004\n"
                                        +"Instructor: Robert Baird\n"
                                        +"Salt Lake Community College\n";

                        JOptionPane.showMessageDialog(null,	projInfo);
                    }
                }
        );




        return aboutMenu;
    }
}
