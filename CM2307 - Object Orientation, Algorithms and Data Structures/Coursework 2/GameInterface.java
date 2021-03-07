//GameInterface.java

import java.io.*;
import java.util.*;

public interface GameInterface{
    //simple interface outlining variables and methods for later implementation of specific games

    public static RandomInterface r = new LinearCongruentialGenerator();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public default void playGame() throws Exception {
    }

    public static void initialiseGame() throws Exception {
    }

    public static void mainGame() throws Exception {
    }

    public static void declareGameWinner() throws Exception {
    }

}

