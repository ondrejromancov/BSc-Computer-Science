//GameFactory.java

import java.io.*;
import java.util.*;

public class GameFactory {

    public static GameInterface startGame(String game) {
        // Determine which input has the user selected 
        // and return the appropriate Card or Dice game

        if (game.equals("c")) {
            System.out.println("cards");
            CardsGame cards = new CardsGame();
            return cards;
        } else if (game.equals("d")) {
            System.out.println("dice");
            DiceGame dice = new DiceGame();
            return dice;
        }
        else {
            //print error message if input not recognized
            System.out.println("Input not understood");
            return null;
        }
    }

}