//DiceGame.java

import java.io.*;
import java.util.*;

public class DiceGame implements GameInterface{
    //implementation of GameInterfaces

    //game variables
    public static HashSet<Integer> numbersRolled=new HashSet<Integer>();

    public void playGame() throws Exception {
        // Play die game:

        // Initialise the game
        initialiseGame();

        // Play the main game phase
        mainGame();

        // Now see if (s)he has won!
        declareGameWinner();
    }

    public static void initialiseGame() throws Exception {
        // The initialisation phase:

        // Actually there isn't anything to do here
    }

    public static void mainGame() throws Exception {
        // The main game:

        // Let the user roll the die twice
        for (int i=0; i<2; i++) {
            System.out.println("Hit <RETURN> to roll the die");
            br.readLine();
            int dieRoll=(int)(r.next() * 6) + 1;

            System.out.println("You rolled " + dieRoll);
            numbersRolled.add(new Integer(dieRoll));
        }

        // Display the numbers rolled
        System.out.println("Numbers rolled: " + numbersRolled);
    }

    public static void declareGameWinner() throws Exception {
        // Declare the winner:

        // User wins if at least one of the die rolls is a 1
        if (numbersRolled.contains(new Integer(1))) {
            System.out.println("You won!");
        }
        else System.out.println("You lost!");
    }

}