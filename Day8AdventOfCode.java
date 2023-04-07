import java.util.*;
import java.io.*;

public class Day8AdventOfCode {

    public String puzzleInput = "/Users/sophieborchart/advent_of_code/day8input.txt";

    public Day8AdventOfCode() {
        // constructor
    }

    public void makeTree() {
        // read the file input
        System.out.println(puzzleInput);
        File fileInput = new File(puzzleInput);
        ArrayList<Integer> lines = new ArrayList<Integer>();
        int numColumns = 0;
        int numRows = 0;
        try {
            Scanner myReader = new Scanner(fileInput);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.trim();  // remove any leading or trailing zeroes
                int num = Integer.parseInt(data);
                lines.add(num);
                numRows = String.valueOf(data).length();
                numColumns++;
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File can't be found!!!");
            e.printStackTrace();
        }
        
        // make a 2D array based off the file input 
        int [][] treeArray = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                treeArray[i][j] = lines.get(j);
            }
            // testing the tree is made correctly
            // System.out.println(lines.get(i));
        }
    }

    public void partOne() {

    }

    public void partTwo() {

    }

    public static void main(String [] args) {
        Day8AdventOfCode partOne = new Day8AdventOfCode();
        partOne.makeTree();
    }
}