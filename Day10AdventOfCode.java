import java.util.*;
import java.io.*;

public class Day10AdventOfCode {

    public String puzzleInput = "/Users/sophieborchart/advent_of_code/day10input.txt";
    public ArrayList<String> instructions = new ArrayList<String>();
    public ArrayList<String> values = new ArrayList<String>();
    public int cycleCounter = 1;
    public ArrayList<Integer> xValAtCycle = new ArrayList<Integer>();
    public int xVal = 1;


    public Day10AdventOfCode() {
        // constructor
    }

    public int partOne() {
        readFile();
        executeInstructions();
        return calculateSignalStrength();
    }

    public int calculateSignalStrength() {
        int totalSignalStrengthOfSixCycles = (xValAtCycle.get(18) * 20)
        + (xValAtCycle.get(58) * 60)
        + (xValAtCycle.get(98) * 100)
        + (xValAtCycle.get(138) * 140)
        + (xValAtCycle.get(178) * 180)
        + (xValAtCycle.get(217) * 220);
        // System.out.println("20th cycle val is: " + xValAtCycle.get(18));
        // System.out.println("60th cycle val is: " + xValAtCycle.get(58));
        // System.out.println("100th cycle val is: " + xValAtCycle.get(98));
        // System.out.println("140th cycle val is: " + xValAtCycle.get(138));
        // System.out.println("180th cycle val is: " + xValAtCycle.get(178));
        // System.out.println("220th cycle val is: " + xValAtCycle.get(217));
        System.out.println("The total sum of the signal strength during the 20th, 60th, 100th, 140th, 180th, and 220th cycles is : " + totalSignalStrengthOfSixCycles);
        return totalSignalStrengthOfSixCycles;
    }

    public void executeInstructions() {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).equals("addx")) {
                cycleCounter += 2;
                // addx - 2 cycles
                try {
                    int valueToAdd = Integer.parseInt(values.get(i));
                    xVal += valueToAdd;
                    xValAtCycle.add(xVal);
                    xValAtCycle.add(xVal);
                } catch (NumberFormatException e) {
                    // no value on this line, it's a noop instruction
                }
            } else {
                cycleCounter++;
                xValAtCycle.add(xVal);
                // instruction is noop - 1 cycle
            }
        }
        // System.out.println("total number of cycles is: " + (cycleCounter - 1));
        // System.out.println("x value is: " + xVal);
        // System.out.println("cycle counter size is: " + xValAtCycle.size());
        // for (int j = 0; j < xValAtCycle.size(); j++) {
        //     System.out.println("x val at cycle " + (j+1) + " is: " + xValAtCycle.get(j));
        // }
    }

    public void readFile() {
        File fileInput = new File(puzzleInput);
        try {
            Scanner myReader = new Scanner(fileInput);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String [] elements = line.split(" ");
                if (elements.length == 2) {
                    instructions.add(elements[0]);
                    values.add(elements[1]);
                } else {
                    instructions.add(elements[0]);
                    values.add("no value");
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File can't be found!!!");
        }
        // for (int i = 0; i < instructions.size(); i++) {
        //     System.out.println("instruction  on line " + (i + 1) + " is " + instructions.get(i));
        // }
        // for (int i = 0; i < values.size(); i++) {
        //     System.out.println("value on line " + (i + 1) + " is: " + values.get(i));
        // }
    }

    public static void main (String [] args) {
        Day10AdventOfCode partOne = new Day10AdventOfCode();
        // put into one method partOne and then push up
        partOne.partOne();
    } 
}