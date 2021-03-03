//Ondrej Romancov
//1731713

import java.util.Arrays;
import java.util.Scanner;

public class QuestionTwo{

	static Scanner input = new Scanner(System.in);
	static Scanner number_input = new Scanner(System.in);


	
	public static void main(String[] args) {

		System.out.println();
		System.out.println("Please enter an odd iteger. A magic square of that size will be created for you to start the game: ");
		int size = number_input.nextInt();
		while(size%2==0){
			System.out.println("Please input an odd number!");
			size = number_input.nextInt();
		}
		
		MagicalSquare testik = new MagicalSquare(size);

		testik.shuffleSquare();
		testik.player_moves = 0;
		testik.game_won = false;
		while(testik.game_won == false){
			playGame(testik);
		}
		System.out.println("You finished the game in "+(testik.player_moves+1)+" moves");
	}

	public static void playGame(MagicalSquare square){
		square.showSquare();
		System.out.println("Make a move: ");
		System.out.println("");

		String str = input.nextLine();
		String[] splited = str.split("\\s+");

		System.out.println("Making move: "+str);

		square.doSwap(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),splited[2]);
	}

}