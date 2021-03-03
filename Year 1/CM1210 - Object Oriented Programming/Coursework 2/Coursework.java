//Ondrej Romancov
//1731713

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Coursework{

	public static int moves = 0;
	public static int comparisons = 0;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Which algorithm would you like to run?");
		System.out.println("(Type 'bubble' for BubbleSort)");
		System.out.println("(Type 'merge' for MergeSort)");
		String algorithm = input.nextLine();
		System.out.println("How many words would you like to input?");
		int word_amount = input.nextInt();
		double averageTime = 0L;
		
		for(int i = 0; i<10; i++){

			//Start timer
			long start = System.nanoTime();

			if(algorithm.equals("bubble")){ 
				System.out.println("Bubble Sort with "+word_amount+" words");
				bubbleSort(parseData("data.txt", word_amount));	
			}
			else if (algorithm.equals("merge")){
				moves = 0;
				//comparisons = 0;
				System.out.println("Merge Sort with "+word_amount+" words");
				mergeSort(parseData("data.txt", word_amount));
				System.out.println("Moves: "+moves);
				//System.out.println("Comparisons: "+comparisons);
			}
			else{
				System.out.println("You did not type in a valid algorithm");
				break;
			}
			//Stop timer
			long end = System.nanoTime();

			//Print out time and add time to average
			double timeTaken = (end - start)/1000000.0;
			System.out.println("Run "+(i+1)+ " took " + timeTaken+ " milliseconds");
			System.out.println("");
			averageTime += timeTaken;
		}

		// Print out average time
		averageTime /= 10;
		System.out.println("Average time taken (in milliseconds) = " +averageTime);
		System.out.println();

	}

	public static ArrayList<String> parseData(String filename, int number_of_words){

		//create an empty ArrayList
		ArrayList<String> words = new ArrayList<String>();

		try {

			//read the specifies file with the name filename
			BufferedReader input = new BufferedReader(new FileReader(filename));

			//iterate until there is enough words added to the ArrayList
			while(words.size()<number_of_words) {

					//read the next line
	                String line = input.readLine();
	                
	                //split the line by one or more whitespaces
	                String[] parts = line.split("[\\s+]");

	                //iterate over words in the current line
	                for(int i = 0; i<parts.length;i++){
	                	if(parts[i].matches("^[a-zA-Z]{4,}")){

	                		//if the word consists only out of 4 or more letters,
	                		//add it to the ArrayList
	                		words.add(parts[i]);
	                	}
	                	if(words.size()==number_of_words){
	                		//if there is already enough words in the ArrayList,
	                		//break the loop
	                		break;
	                	}
	                }
	            }
	        //close the file
	        input.close();
		}
	    catch( Exception e ) {
	    	//Raise exception ig there is a problem with the file
            System.out.println("Problem reading file: " + filename );
            System.out.println(e);  
        }

        //return populated ArrayList
        return(words);
	}

	public static ArrayList<String> bubbleSort(ArrayList<String> words){

		boolean sorted = false; //storing whether the ArrayList is sorted
		int comparisons = 0; //storing number of comparions
		int swaps = 0; //storing number of swaps

		//iterate until the ArrayList is sorted
		while(!sorted){
			sorted = true; 

			//iterate over all of the words in the ArrayList
			for(int i = 0; i<(words.size()-1); i++){
				comparisons ++; //increase the number of comparisons by one

				//compares whether the current word shouldnt be alphabetically behind the following word
				if(words.get(i).compareToIgnoreCase(words.get(i+1))>0){
					swaps ++; //increase the number of comparisons by one

					//swap the two words
					String temp = words.get(i); 
					words.set(i,words.get(i+1));
					words.set(i+1, temp);
					sorted = false; //If a swap was made, set sorted to false again
				}
			}
		}

		//System.out.println("Comparisons: "+comparisons); //print out comparisons
		System.out.println("Swaps: "+swaps); //print out swaps
		System.out.println("");

		return(words); //return sorted ArrayList
	}

	public static ArrayList<String> mergeSort(ArrayList<String> words){

		int length = words.size(); //get the size of the current ArrayList
		int middle = length/2; //get the middle index of the current ArrayList
		if(length <= 1){
			//if there is only one or less values in the ArrayList, then the list can be
			//returned as it is already sorted
			return words;	
		}

		//spli the ArrayList into two ArrayLists
		ArrayList<String> firsthalf = new ArrayList<String>(words.subList(0, middle));
		ArrayList<String> secondhalf = new ArrayList<String>(words.subList(middle, length));
		
		//perform mergeSorts on the two halves
		firsthalf = mergeSort(firsthalf);
		secondhalf = mergeSort(secondhalf);

		return(merge(firsthalf, secondhalf)); //merge the two halves and return the sorted ArrayList

	}

	public static ArrayList<String> merge(ArrayList<String> firsthalf, ArrayList<String> secondhalf){

		ArrayList<String> sorted = new ArrayList<String>(); //create an empty ArrayList

		while(!firsthalf.isEmpty() && !secondhalf.isEmpty()){
			//if neither of the halves are empty, compares which of the two current words from the two
			//lists should go first alphabetically
			if(firsthalf.get(0).compareToIgnoreCase(secondhalf.get(0))<0){
				comparisons++; //increase the number of comparisons
				moves++; //increase the number of moves

				//add the string from the first half to the sorted ArrayList and delete it
				sorted.add(firsthalf.get(0)); 
				firsthalf.remove(0);
			}
			else{
				comparisons++; //increase the number of comparisons
				moves++; //increase the number of moves

				//add the string from the second half to the sorted ArrayList and delete it
				sorted.add(secondhalf.get(0));
				secondhalf.remove(0);
			}
		}
		//if one of the list is empty, add all of the items from the other list to the sorted ArrayList
		while(!firsthalf.isEmpty()){
				moves++;
				sorted.add(firsthalf.get(0));
				firsthalf.remove(0);
		}
		while(!secondhalf.isEmpty()){
				moves++;
				sorted.add(secondhalf.get(0));
				secondhalf.remove(0);
		}	
		return(sorted); //return the sorted ArrayList
	}
}