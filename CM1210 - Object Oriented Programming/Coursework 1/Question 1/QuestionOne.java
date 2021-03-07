//Ondrej Romancov
//1731713

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class QuestionOne{

	//static ArrayList<String> studentList = new ArrayList<String>();
	static ArrayList<StudentRecord> studentList = new ArrayList<StudentRecord>();

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		boolean run = true; 
		
		//main loop of the manager
		while (run == true){
			System.out.println();
			System.out.println("STUDENTS RECORDS MANAGER");
			System.out.println();
			System.out.println("Type in LOAD to load data from a file");
			System.out.println("Type in CREATE to create an empty file");
			System.out.println("Type in SAVE to save data to a file");
			System.out.println("Type in ADD to add new student records");
			System.out.println("Type in DELETE to delete a record");
			System.out.println("Type in DISPLAY to display all student details");
			System.out.println("Type in DISPLAY_COURSE to display all records with specific course name");
			System.out.println("Type in DISPLAY_SET to display a selection of students");
			System.out.println("Type in DISPLAY_ADDRESS to display all records with certain address");
			System.out.println("Type in QUIT to quit the application");
			System.out.println();
			System.out.println("What do you want to do?");
			System.out.println();

			String choice = input.nextLine();
			switch (choice.toLowerCase()) {
				case "load":
					loadfromFile();
					break;
				case "create":
					createFile();
					break;
				case "save":
					savetoFile();
					break;
				case "add": 
					enterData();
					break;
				case "delete":
					deleteData();
					break;
				case "display":
					display();
					break;
				case "display_course":
					displayCourse();
					break;
				case "display_set":
					displaySet();
					break;
				case "quit": 
					run = false;
					break;
			} 
		}
	}

	public static void enterData(){

		StudentRecord new_record = new StudentRecord();
		String temp = "";

		//Input data
		do{
			System.out.println("Enter name:");
			temp = input.nextLine();
			new_record.setName(temp);
		}
		while(!temp.matches("^[a-zA-Z|\\s]+$")); //Only letters with spaces
		
		do{
			System.out.println("Enter Student's Number:");
			temp = input.nextLine();
			new_record.setSNumber(temp);
		}
		while(!temp.matches("^[C]\\d{6}$")); //Upper case C with 6 digits

		do{
			System.out.println("Enter Course Name:");
			temp = input.nextLine();
			new_record.setCourseName(temp);
		}
		while(!temp.matches("^[a-zA-Z|\\s]+$")); //Only letters with spaces
		
		do{
			System.out.println("Enter Course ID:");
			temp = input.nextLine();
			new_record.setCourseID(temp);
		}
		while(!temp.matches("^[A-Z]{2}\\d{4}$")); //Two upper case letters and 4 digits

		do{
			System.out.println("Enter House Number:");
			temp = input.nextLine();
			new_record.setHouse(temp);

		}
		while(!temp.matches("^\\d+[a-zA-Z]?")); //At least one digit followed with upmost one number

		do{
			System.out.println("Enter Street Name:");
			temp = input.nextLine();
			new_record.setStreet(temp);
		}
		while(!temp.matches("^[a-zA-Z|\\s]+$")); //Only letters with spaces

		do{
			System.out.println("Enter Town:");
			temp = input.nextLine();
			new_record.setTown(temp);
		}
		while(!temp.matches("^[a-zA-Z|\\s]+$")); //Only letters with spaces

		do{
			System.out.println("Enter Postcode:");
			temp = input.nextLine();
			new_record.setPostcode(temp);
		}
		while(!temp.matches("^[A-Z]{2}\\d{1}[A-Z]{2}$")); //2 upper case letters, 1 number, 2 upper case letters

		System.out.println("Record added succesfully.");

		//pass record to list
		studentList.add(new_record);	
	}

	public static void deleteData(){
		System.out.println();
		System.out.println("Type in the number of the record that you want to delete:");
		System.out.println();

		int temp = input.nextInt();

		studentList.remove(temp-1);

		System.out.println("Record "+temp+" succesfully deleted.");
	}

	public static void display(){
		for(int i = 0; i < studentList.size(); i++){
				System.out.println((i+1) + ": " + studentList.get(i));
		}
	}

	public static void createFile(){
		try{
			//reference to code used from CM1210 taugh lab session3 
			System.out.println("How would you like to name the new file?");

			FileWriter writer = new FileWriter(input.nextLine());
            PrintWriter out = new PrintWriter( writer );
            out.close();

            System.out.println("New file created succesfully.");

		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public static void loadfromFile(){
		//reference to code used from CM1210 taugh lab session3 in PhoneBookloader example
		System.out.println("Enter the name of the file: ");

		String filename = input.nextLine();

		File fileIn = new File(filename);
        
        try {
            Scanner in = new Scanner( fileIn );
            studentList.clear();
            
            while( in.hasNextLine() ) {
                // Get a line of the text file
                String line = in.nextLine();
                StudentRecord new_record = new StudentRecord();
                
                // Separate the line into name and number
                String[] parts = line.split(", ");

				new_record.setName(parts[0]);
				new_record.setSNumber(parts[1]);
				new_record.setCourseName(parts[2]);
				new_record.setCourseID(parts[3]);
				new_record.setHouse(parts[4]);
				new_record.setStreet(parts[5]);
				new_record.setTown(parts[6]);
				new_record.setPostcode(parts[7]);

				studentList.add(new_record);	
            }
            in.close();

            System.out.println(filename +" loaded succesfully.");
            
        }
        catch( Exception e ) {
            System.out.println("Problem reading file: " + filename );
            System.out.println(e);  // re-raise exception
        }
	}

	public static void savetoFile(){
		//reference session3 Text folder
		try{

			System.out.println("Enter the name of the file: (Enter a new name for a new file or a name of an existing file to overwrite data in that file)");

			FileWriter writer = new FileWriter(input.nextLine());
            PrintWriter out = new PrintWriter( writer );
            for(int i = 0; i < studentList.size(); i++){
				out.println(studentList.get(i));
			}
            out.close();

            System.out.println("Saved succesfully.");

		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public static void displayCourse(){
		ArrayList<StudentRecord> results = new ArrayList<StudentRecord>();

		System.out.println("Please input a specific substring: ");
		String query = input.nextLine();

		for(int i = 0; i<studentList.size(); i++){
			if(studentList.get(i).getCourseName().toLowerCase().contains(query.toLowerCase())){
				results.add(studentList.get(i));
			}
		}

		for(int i = 0; i <results.size(); i++){
				System.out.println(results.get(i));
		}
	}

	public static void displaySet(){
		System.out.println("Please enter your minimum: ");
		int min = input.nextInt();

		System.out.println("Please enter your maximum ");
		int max = input.nextInt();

		for(int i = (min-1); i<max; i++){
			System.out.println(studentList.get(i));
		}
	}
}
