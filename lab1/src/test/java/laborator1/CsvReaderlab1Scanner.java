package laborator1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReaderlab1Scanner {
    public static void main(String[] args) throws FileNotFoundException
    {
        //Get scanner instance
        Scanner scanner = new Scanner(new File("student.csv"));

        //Set the delimiter used in file
        scanner.useDelimiter(",");

        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext())
        {
            System.out.print(scanner.next() + "|");
        }

        //Do not forget to close the scanner
        scanner.close();
    }
}
