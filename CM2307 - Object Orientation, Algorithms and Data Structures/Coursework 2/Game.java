//Game.java

import java.io.*;
import java.util.*;

public class Game {
  // The BufferedReader used throughout
  public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws Exception {
    // Ask whether to play a card game or a die game

    System.out.print("Card (c) or Die (d) game? ");

    String ans=br.readLine();
    
    //ask the factory to return an appropriate game depending on user's input
    GameInterface game = GameFactory.startGame(ans);

    game.playGame();

  }
}

