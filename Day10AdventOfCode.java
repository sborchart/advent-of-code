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
        System.out.println("Part 1 -- The total sum of the signal strength during the 20th, 60th, 100th, 140th, 180th, and 220th cycles is : " + totalSignalStrengthOfSixCycles);
        return totalSignalStrengthOfSixCycles;
    }

    public void executeInstructions() {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).equals("addx")) {
                cycleCounter += 2;
                try {
                    int valueToAdd = Integer.parseInt(values.get(i));
                    xVal += valueToAdd;
                    xValAtCycle.add(xVal);
                    xValAtCycle.add(xVal);
                } catch (NumberFormatException e) {
                }
            } else {
                cycleCounter++;
                xValAtCycle.add(xVal);
            }
        }
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
    }

    public void partTwo() {
        List<Integer> litPixels = new ArrayList<>();
        int xRegister = 1;
        int cycleCount = 0;

        System.out.println("Part 2 -- CRT is producing the following image: ");

        File fileInput = new File(puzzleInput);
        try {
            Scanner myReader = new Scanner(fileInput);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                cycleCount = executeCycles(cycleCount, xRegister, litPixels);
                if (line.startsWith("addx")) {
                    cycleCount = executeCycles(cycleCount, xRegister, litPixels);
                    xRegister += Integer.parseInt(line.split(" ")[1]);
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File can't be found!!!");
        }
    }

    public int executeCycles(int cycleCount, int xRegister, List<Integer> litPixels) {
        cycleCount += 1;
        if (xRegister <= cycleCount && cycleCount <= xRegister + 2) {
            litPixels.add(cycleCount - 1);
        }
        if (cycleCount == 40) {
            for (int i = 0; i < 40; i++) {
                if (litPixels.contains(i)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println(" ");
            litPixels.clear();
            cycleCount = 0;
        }
        return cycleCount;
    }

    public static void main (String [] args) {
        Day10AdventOfCode day10 = new Day10AdventOfCode();
        day10.partOne();
        day10.partTwo();

    } 
}