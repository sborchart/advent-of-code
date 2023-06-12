import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11AdventOfCode {

    public Day11AdventOfCode() {
        // constructor
    }

    public static class Monkey {
        public int monkeyNumber;
        public List<Integer> startingItems;
        public String operation;
        public String test;
        public String trueCondition;
        public String falseCondition;

        public Monkey() {
            // Monkey constructor 
        }

        void getMonkey() {
            System.out.println("getting the monkey");
        }
    }

    public List<String> openFile() {
        // 1-6 monkey 0
        // 7 space
        // 8-13 monkey 1
        // 14 space
        // 15-20 monkey 2
        // 21 space

        List<String> lines = new ArrayList<>();
        List<Monkey> monkeys = new ArrayList<>();
        try {
            File file = new File("/Users/sophieborchart/advent_of_code/day11input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String [] result = line.split(" ");

                // initializing monkey num
                if (result[0].equals("Monkey")) {
                    Integer monkeyNum = Character.getNumericValue(result[1].charAt(0));
                    System.out.println("monkey num: " + monkeyNum);
                }
                
                // initializing starting items
                if (result[0].equals("Starting")) {
                    List<Integer> startingItems = new ArrayList<>();
                    // Regex pattern to match starting items
                    String regexPattern = "\\b\\d+\\b";
                    Pattern pattern = Pattern.compile(regexPattern);
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        int startingNumberToAdd = Integer.parseInt(matcher.group());
                        startingItems.add(startingNumberToAdd);
                        System.out.println("Starting item: " + startingNumberToAdd);
                    }
                }

                // initializing operation
                if (result[0].equals("Operation:")) {
                    // old + - * / by old or int: old = old * 19, old = old + old
                    Matcher matcher = getNumberFromString(line);
                    if (matcher.find()) {
                        String numberStr = matcher.group();
                        int operationNum = Integer.parseInt(numberStr);
                        System.out.println("operation num is: " + operationNum);
                    } else {
                        // must be "old"
                        // operationNum is old
                        System.out.println("operation num is: old");
                    }

                    // new conditional to check the operation type + - * /
                    
                }

                // initializing test (get the number it's divisible by)
                if (result[0].equals("Test:")) {
                    Matcher matcher = getNumberFromString(line);
                    if (matcher.find()) {
                        String numberStr = matcher.group();
                        int numberDivisibleBy = Integer.parseInt(numberStr);
                        System.out.println("divisible by TEST: " + numberDivisibleBy);
                    }
                }

                // initializing boolean scenarios
                if (result[0].equals("If")) {
                    Matcher matcher = getNumberFromString(line);
                    if (matcher.find()) {
                        String numberStr = matcher.group();
                        int monkeyToThrowTo = Integer.parseInt(numberStr);
                        if (result[1].equals("true:"))  {
                            System.out.println("monkey to throw to TRUE: " + monkeyToThrowTo);
                        }
                        else if (result[1].equals("false:"))  {
                            System.out.println("monkey to throw to FALSE: " + monkeyToThrowTo);
                        }
                    }
                }

                lines.add(line);
                // now that everything is parsed, add each value to monkey object
                // monkeys.add(monkey);
                // then add each monkey to monkeyList
                // then, can do part 1 with the monkey list


                // System.out.println("adding line : " + line);







            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public Matcher getNumberFromString(String line) {
        // regex pattern to get the number out of the string to divide by
        String regexPattern = "\\d+";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(line);
        return matcher;
    }

    public static void main(String [] args) {
        Day11AdventOfCode day11Obj = new Day11AdventOfCode();
        Monkey monkey = new Monkey();
        // monkey.getMonkey();
        day11Obj.openFile();
    }
}