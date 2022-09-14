package udemy.multithreading.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.InflaterOutputStream;

/**
 * Purpose is to have a collection of data that can be used to provide reproduces result.  The data 
 * will be serialized so it can be reused with creating new data.
 */
public class TestDataSerialization {

    /**
     * Crete a list of Integers where we have a 100 entries with each entry having a 100 elements
     * 
     * @return   A list of list of integers 
     */
    public List<List<Integer>> createData(int rows, int cols) {
        Random random = new Random();

        List<List<Integer>> listOfListOfNumbers = new ArrayList<>();
        for ( Integer index = 0; index < rows; index++) {
            listOfListOfNumbers.add( random.ints(cols).boxed().toList());
        }

        System.out.println(
            "Created a list of list of integers where the number of rows is " + 
            listOfListOfNumbers.size() +
            " and each rows has " + 
            listOfListOfNumbers.get(0).size() + 
            "elements");

        return listOfListOfNumbers;  
    }

    public List<List<Integer>> readFile(String filenameWithoutExtension ) {
        String name = filenameWithoutExtension + ".ser";
        List<List<Integer>> matrix = null;

        try ( FileInputStream inputStream = new FileInputStream(name)) {
            try ( OutputInputStream objectInputStream = new OutputInputStream(inputStream) ) { 
                matrix = ( List<List<Integer>> ) objectInputStream.readObject();
            }
        }

        return matrix;
    }

    public void createFile(String filenameWithoutExtension, List<List<Integer>> matrix ) throws FileNotFoundException, IOException {

        String name = filenameWithoutExtension + ".ser";

        try ( FileOutputStream outputFile = new FileOutputStream(name)) {
            try ( ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputFile)) {
                objectOutputStream.writeObject(matrix);
            }  
        } catch( FileNotFoundException exception) {
            System.out.println("name = " + name + " is a directory and the message is " + exception.getMessage());
            throw exception;
        }

    }

    public static void main(String... arg) throws FileNotFoundException, IOException {
        TestDataSerialization testData = new TestDataSerialization();
        testData.createFile("test", testData.createData(100,100));
    }

} 