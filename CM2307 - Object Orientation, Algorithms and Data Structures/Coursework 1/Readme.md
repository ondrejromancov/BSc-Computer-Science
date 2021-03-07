# Coursework 1
Implementing the SparseMatrix data structure with various functionalities and strict requirements on their time complexity.

## Functionalities
This coursework has the following implemented functionalities:

* Loading a matrix from a file with *loadEntries(String filename)* using a sorting algorithm
* Transposing a matrix using *transpose()* 
* Adding two matrices together with *add(SparseMatrix M)*
* Matrix-Scalar multiplication through *multiplyBy(int scalar)* function
* Matrix-Vector mutliplication by *multiply(DenseVector v)*

## Running

1. Compile everything with `javac *.java`
2. Run an appropriate command from the following:
    1. `java SparseMatrix -i <MatrixFile>` to load a matrix file and print its information
    1. `java SparseMatrix -r <MatrixFile>` to load a matrix file and print its elements
    1. `java SparseMatrix -t <MatrixFile>` to transpose a matrix
    1. `java SparseMatrix -a <MatrixFile1> <MatrixFile2>` to add two matrices
    1. `java SparseMatrix -v <MatrixFile> <VectorFile>` to perform Matrix-Vector multiplitcation

