//Ondrej Romancov
//1731713

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class MagicalSquare{
	
	private int side;
	private int array_side;
	private int[][] numbers;
	private int[][] correct_numbers;
	private String[] directions = {"U","D","L","R"};
	public int player_moves = 0;
	public boolean game_won = false;

	public MagicalSquare(int side){
		this.side = side;
		this.array_side = (side-1);
		generateSquare(side);
	}

	private void generateSquare(int side){
		numbers = new int[side][side];

		//set all elements in array to 0
		for (int[] row: numbers){
    		Arrays.fill(row, 0);
		}

		int x = 0;
		int y = ((side+1)/2)-1;
		numbers[x][y] = 1;
		int[] squareTopLeft = {array_side,y-1};

		for(int i = 2; i <=(side*side); i++){
			if(numbers[squareTopLeft[0]][squareTopLeft[1]] == 0){
				x=squareTopLeft[0];
				y=squareTopLeft[1];
			}
			else{
				x++;
			}
			numbers[x][y] = i;
			//work out next top left square
			if(x==0){
				squareTopLeft[0]=array_side;
			}
			else{
				squareTopLeft[0]=x-1;
			}
			if(y==0){
				squareTopLeft[1]=array_side;
			}
			else{
				squareTopLeft[1]=y-1;
			}
		}
		correct_numbers = new int[side][side];

		for(int i = 0; i<side; i++){
			for(int j = 0; j<side; j++){
				correct_numbers[i][j] = numbers[i][j];
			}
		}
	}

	public void showSquare(){
		for(int i = 0; i<side; i++){
			System.out.println();
			for(int j = 0; j<side; j++){
				System.out.print(numbers[i][j]+"  ");
			}
		}
		System.out.println();
		System.out.println();
	}

	public void shuffleSquare(){
		Random generator = new Random();
		for(int i = 0; i<(side*side); i++){		

			int random_x = generator.nextInt(side);
			int random_y = generator.nextInt(side);
			int random_d = generator.nextInt(getNeighbours(random_x,random_y).size());

			doSwap(random_x,random_y,getNeighbours(random_x,random_y).get(random_d));
		}
	}

	public void doSwap(int x, int y, String d){
		int new_number = numbers[x][y];
		switch (d) {
				case "U":
					numbers[x][y] = numbers[x-1][y];
					numbers[x-1][y] = new_number;
					break;
				case "D":
					numbers[x][y] = numbers[x+1][y];
					numbers[x+1][y] = new_number;
					break;
				case "L":
					numbers[x][y] = numbers[x][y-1];
					numbers[x][y-1] = new_number;
					break;
				case "R":
					numbers[x][y] = numbers[x][y+1];
					numbers[x][y+1] = new_number;
					break;
		}
		if(checkWon()){
			game_won = true;
			System.out.println("WON");
		}
		else{
			player_moves++;
		}
	}

	private ArrayList<String> getNeighbours(int x, int y){
		ArrayList<String> neighbours = new ArrayList<String>();
		if(x>0){
			neighbours.add("U");
		}
		if(x<array_side){
			neighbours.add("D");
		}
		if(y>0){
			neighbours.add("L");
		}
		if(y<array_side){
			neighbours.add("R");
		}
		return neighbours;
	}

	private boolean checkWon(){
		boolean won = true;
		for(int i = 0; i<side; i++){
			for(int j = 0; j<side; j++){
				if(correct_numbers[i][j] != numbers[i][j]){
					won = false;
				}
			}
		}
		return won;
	}

}