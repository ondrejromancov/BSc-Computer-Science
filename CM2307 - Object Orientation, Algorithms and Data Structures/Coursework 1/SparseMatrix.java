
/* Put your student number here
 * 1731713
 * Optionally, if you have any comments regarding your submission, put them here. 
 * For instance, specify here if your program does not generate the proper output or does not do it in the correct manner.
 */

import java.util.*;

//import sun.security.util.Length;

import java.io.*;

// A class that represents a dense vector and allows you to read/write its elements
class DenseVector {
	private int[] elements;

	public DenseVector(int n) {
		elements = new int[n];
	}

	public DenseVector(String filename) {
		File file = new File(filename);
		ArrayList<Integer> values = new ArrayList<Integer>();

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextInt()) {
				values.add(sc.nextInt());
			}

			sc.close();

			elements = new int[values.size()];
			for (int i = 0; i < values.size(); ++i) {
				elements[i] = values.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Read an element of the vector
	public int getElement(int idx) {
		return elements[idx];
	}

	// Modify an element of the vector
	public void setElement(int idx, int value) {
		elements[idx] = value;
	}

	// Return the number of elements
	public int size() {
		return (elements == null) ? 0 : (elements.length);
	}

	// Print all the elements
	public void print() {
		if (elements == null) {
			return;
		}

		for (int i = 0; i < elements.length; ++i) {
			System.out.println(elements[i]);
		}
	}
}

// A class that represents a sparse matrix
public class SparseMatrix {
	// Auxiliary function that prints out the command syntax
	public static void printCommandError() {
		System.err.println("ERROR: use one of the following commands");
		System.err.println(" - Read a matrix and print information: java SparseMatrix -i <MatrixFile>");
		System.err.println(" - Read a matrix and print elements: java SparseMatrix -r <MatrixFile>");
		System.err.println(" - Transpose a matrix: java SparseMatrix -t <MatrixFile>");
		System.err.println(" - Add two matrices: java SparseMatrix -a <MatrixFile1> <MatrixFile2>");
		System.err.println(" - Matrix-vector multiplication: java SparseMatrix -v <MatrixFile> <VectorFile>");
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			printCommandError();
			System.exit(-1);
		}

		if (args[0].equals("-i")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1]);
			System.out.println("The matrix has " + mat.getNumRows() + " rows and " + mat.getNumColumns() + " columns");
			System.out.println("It has " + mat.numNonZeros() + " non-zeros");
		} else if (args[0].equals("-r")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1] + ":");
			mat.print();
		} else if (args[0].equals("-t")) {
			if (args.length != 2) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			System.out.println("Read matrix from " + args[1]);
			SparseMatrix transpose_mat = mat.transpose();
			System.out.println();
			System.out.println("Matrix elements:");
			mat.print();
			System.out.println();
			System.out.println("Transposed matrix elements:");
			transpose_mat.print();
		} else if (args[0].equals("-a")) {
			if (args.length != 3) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat1 = new SparseMatrix();
			mat1.loadEntries(args[1]);
			System.out.println("Read matrix 1 from " + args[1]);
			System.out.println("Matrix elements:");
			mat1.print();

			System.out.println();
			SparseMatrix mat2 = new SparseMatrix();
			mat2.loadEntries(args[2]);
			System.out.println("Read matrix 2 from " + args[2]);
			System.out.println("Matrix elements:");
			mat2.print();
			SparseMatrix mat_sum1 = mat1.add(mat2);

			System.out.println();
			mat1.multiplyBy(2);
			SparseMatrix mat_sum2 = mat1.add(mat2);

			mat1.multiplyBy(5);
			SparseMatrix mat_sum3 = mat1.add(mat2);

			System.out.println("Matrix1 + Matrix2 =");
			mat_sum1.print();
			System.out.println();

			System.out.println("Matrix1 * 2 + Matrix2 =");
			mat_sum2.print();
			System.out.println();

			System.out.println("Matrix1 * 10 + Matrix2 =");
			mat_sum3.print();
		} else if (args[0].equals("-v")) {
			if (args.length != 3) {
				printCommandError();
				System.exit(-1);
			}

			SparseMatrix mat = new SparseMatrix();
			mat.loadEntries(args[1]);
			DenseVector vec = new DenseVector(args[2]);
			DenseVector mv = mat.multiply(vec);

			System.out.println("Read matrix from " + args[1] + ":");
			mat.print();
			System.out.println();

			System.out.println("Read vector from " + args[2] + ":");
			vec.print();
			System.out.println();

			System.out.println("Matrix-vector multiplication:");
			mv.print();
		}
	}

	// Loading matrix entries from a text file
	// You need to complete this implementation
	public void loadEntries(String filename) {
		File file = new File(filename);

		try {
			Scanner sc = new Scanner(file);
			numRows = sc.nextInt();
			numCols = sc.nextInt();
			entries = new ArrayList<Entry>();

			while (sc.hasNextInt()) {
				// Read the row index, column index, and value of an element
				int row = sc.nextInt();
				int col = sc.nextInt();
				int val = sc.nextInt();

				// Add your code here to add the element into data member entries
				Entry entry = new Entry((numCols*row)+col,val);

				entries.add(entry);
			}

			// Add your code here for sorting non-zero elements
			ArrayList<Entry> sorted_entries = mergeSort(entries);

			entries = sorted_entries;
			
		} catch (Exception e) {
			e.printStackTrace();
			numRows = 0;
			numCols = 0;
			entries = null;
		}
	}

	public ArrayList<Entry> mergeSort(ArrayList<Entry> array){
		int length = array.size(); //get the size of the current ArrayList
		int middle = length/2; //get the middle index of the current ArrayList
		if(length <= 1){
			//if there is only one or less values in the ArrayList, then the list can be
			//returned as it is already sorted
			return array;	
		}

		//split the ArrayList into two ArrayLists
		ArrayList<Entry> firsthalf = new ArrayList<Entry>(array.subList(0, middle));
		ArrayList<Entry> secondhalf = new ArrayList<Entry>(array.subList(middle, length));
		
		//perform mergeSorts on the two halves
		firsthalf = mergeSort(firsthalf);
		secondhalf = mergeSort(secondhalf);

		return(merge(firsthalf, secondhalf)); //merge the two halves and return the sorted ArrayList
	}

	public ArrayList<Entry> merge(ArrayList<Entry> firsthalf, ArrayList<Entry> secondhalf){

		ArrayList<Entry> sorted = new ArrayList<Entry>(); //create an empty ArrayList

		while(!firsthalf.isEmpty() && !secondhalf.isEmpty()){
			//if neither of the halves are empty, compares which of the two current entries from the two
			//lists should go first
			if(firsthalf.get(0).getPosition()<secondhalf.get(0).getPosition()){

				//add the entry from the first half to the sorted ArrayList and delete it
				sorted.add(firsthalf.get(0)); 
				firsthalf.remove(0);
			}
			else{

				//add the entry from the second half to the sorted ArrayList and delete it
				sorted.add(secondhalf.get(0));
				secondhalf.remove(0);
			}
		}
		//if one of the lists is empty, add all of the items from the other list to the sorted ArrayList
		while(!firsthalf.isEmpty()){
				sorted.add(firsthalf.get(0));
				firsthalf.remove(0);
		}
		while(!secondhalf.isEmpty()){
				sorted.add(secondhalf.get(0));
				secondhalf.remove(0);
		}	
		return(sorted); //return the sorted ArrayList
	}

	// Default constructor
	public SparseMatrix() {
		numRows = 0;
		numCols = 0;
		entries = null;
	}

	// A class representing a pair of column index and elements
	private class Entry {
		private int position; // Position within row-major full array representation
		private int value; // Element value

		// Constructor using the column index and the element value
		public Entry(int pos, int val) {
			this.position = pos;
			this.value = val;
		}

		// Copy constructor
		public Entry(Entry entry) {
			this(entry.position, entry.value);
		}

		// Read column index
		int getPosition() {
			return position;
		}

		// Set column index
		void setPosition(int pos) {
			this.position = pos;
		}

		// Read element value
		int getValue() {
			return value;
		}

		// Set element value
		void setValue(int val) {
			this.value = val;
		}
	}

	// Adding two matrices
	public SparseMatrix add(SparseMatrix M) {

		SparseMatrix addedMatrix = new SparseMatrix();
		addedMatrix.numCols = M.numCols;
		addedMatrix.numRows = M.numRows;

		ArrayList<Entry> firsthalf = new ArrayList<Entry>(this.entries);
		ArrayList<Entry> secondhalf = new ArrayList<Entry>(M.entries);
		
		ArrayList<Entry> added_array = new ArrayList<Entry>(); //create an empty ArrayList

		while(!firsthalf.isEmpty() && !secondhalf.isEmpty()){
			//if neither of the halves are empty, compares which of the two current entries from the two
			//lists should go first
			if(firsthalf.get(0).getPosition()<secondhalf.get(0).getPosition()){
				//add the entry from the first half to the sorted ArrayList and delete it
				added_array.add(firsthalf.get(0)); 
				firsthalf.remove(0);
			}
			else if(firsthalf.get(0).getPosition()==secondhalf.get(0).getPosition()){
				Entry new_entry = new Entry(firsthalf.get(0).getPosition(), firsthalf.get(0).getValue()+secondhalf.get(0).getValue());
				added_array.add(new_entry);
				firsthalf.remove(0);
				secondhalf.remove(0);
			}
			else{
				//add the entry from the second half to the sorted ArrayList and delete it
				added_array.add(secondhalf.get(0));
				secondhalf.remove(0);
			}
		}
		//if one of the lists is empty, add all of the items from the other list to the sorted ArrayList
		while(!firsthalf.isEmpty()){
				added_array.add(firsthalf.get(0));
				firsthalf.remove(0);
		}
		while(!secondhalf.isEmpty()){
				added_array.add(secondhalf.get(0));
				secondhalf.remove(0);
		}
		
		addedMatrix.entries = added_array;

		return addedMatrix;
	}

	// Transposing a matrix
	public SparseMatrix transpose() {
		
		SparseMatrix transposed =  new SparseMatrix();
		int new_cols = this.getNumRows();
		int new_rows = this.getNumColumns();
		transposed.numCols = new_cols;
		transposed.numRows = new_rows;

		int iterations = this.entries.size();

		ArrayList<Entry> transposed_entries = new ArrayList<Entry>(); 

		for(int i = 0; i < iterations;i++){
			int new_value = this.entries.get(i).value;

			int old_row = this.entries.get(i).position / this.numCols;
			int old_col = this.entries.get(i).position % this.numCols;
			int new_position = (old_col*transposed.numCols)+old_row;

			Entry transposed_entry = new Entry(new_position, new_value);
			
			transposed_entries.add(transposed_entry);
		}

		ArrayList<Entry> sorted_entries = mergeSort(transposed_entries);

		transposed.entries = sorted_entries;

		return transposed;
	}

	// Matrix-vector multiplication
	public DenseVector multiply(DenseVector v) {

		DenseVector result = new DenseVector(this.numRows);
		
		int colums = this.numCols;

		int row_counter = 0;
		int dense_entry = 0;
		int dense_index = 0;

		for(int i = 0;i<this.entries.size();i++){
			if(entries.get(i).position/colums == row_counter){
				dense_entry = dense_entry + (entries.get(i).value*v.getElement(entries.get(i).position%colums));
			}
			else{
				result.setElement(dense_index, dense_entry);
				row_counter++;
				dense_entry = 0;
				dense_index++;

				dense_entry = dense_entry + (entries.get(i).value*v.getElement(entries.get(i).position%colums));
			}
		}

		result.setElement(dense_index, dense_entry);

		return result;
	}

	// Return the number of non-zeros
	public int numNonZeros() {
		return entries.size();
	}

	// Multiply the matrix by a scalar, and update the matrix elements
	public void multiplyBy(int scalar) {

		ArrayList<Entry> multiplied_entries = new ArrayList<Entry>(); 

		for(int i = 0; i<this.entries.size(); i++){
			Entry new_entry = new Entry(this.entries.get(i).getPosition(),this.entries.get(i).getValue()*scalar);
			multiplied_entries.add(new_entry);
		}

		this.entries = multiplied_entries;
	}

	// Number of rows of the matrix
	public int getNumRows() {
		return this.numRows;
	}

	// Number of columns of the matrix
	public int getNumColumns() {
		return this.numCols;
	}

	// Output the elements of the matrix, including the zeros
	// Do not modify this method
	public void print() {
		int n_elem = numRows * numCols;
		int pos = 0;

		for (int i = 0; i < entries.size(); ++i) {
			int nonzero_pos = entries.get(i).getPosition();

			while (pos <= nonzero_pos) {
				if (pos < nonzero_pos) {
					System.out.print("0 ");
				} else {
					System.out.print(entries.get(i).getValue());
					System.out.print(" ");
				}

				if ((pos + 1) % this.numCols == 0) {
					System.out.println();
				}

				pos++;
			}
		}

		while (pos < n_elem) {
			System.out.print("0 ");
			if ((pos + 1) % this.numCols == 0) {
				System.out.println();
			}

			pos++;
		}
	}

	private int numRows; // Number of rows
	private int numCols; // Number of columns
	private ArrayList<Entry> entries; // Non-zero elements
}
