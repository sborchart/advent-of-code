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
        public char operation;
        public int operationNumber;
        public int numberDivisibleBy;
        public int monkeyToThrowToTrue;
        public int monkeyToThrowToFalse;

        // Monkey constructor 
        public Monkey(int monkeyNumber, List<Integer> startingItems, char operation, int operationNumber,
            int numberDivisibleBy, int monkeyToThrowToTrue, int monkeyToThrowToFalse) {
                this.monkeyNumber = monkeyNumber;
                this.startingItems = startingItems;
                this.operation = operation;
                this.operationNumber = operationNumber;
                this.numberDivisibleBy = numberDivisibleBy;
                this.monkeyToThrowToTrue = monkeyToThrowToTrue;
                this.monkeyToThrowToFalse = monkeyToThrowToFalse;
        }

        void getMonkey() {
            System.out.println("getting the monkey");
        }
    }

    // public List<String> openFile() {
    public List<Monkey> openFileInitializeMonkeys() {
        List<String> lines = new ArrayList<>();
        List<Monkey> monkeys = new ArrayList<>();
        try {
            // hacked the file by adding an empty last line in at the end :0 oops.
            // will fix it later so that it's not necessary
            File file = new File("/Users/sophieborchart/advent_of_code/day11input.txt");
            Scanner scanner = new Scanner(file);
            int lineCount = 1;

            Integer monkeyNum = 0;
            // List<Integer> startingItemsForMonkey = new ArrayList<>();
            int operationNum = 0;
            boolean shouldMultiply = false;
            char operationChar = '+';
            int numberDivisibleBy = 0;
            int monkeyToThrowToTrue = 0;
            int monkeyToThrowToFalse = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String [] result = line.split(" ");

                // initializing monkey num
                if (result[0].equals("Monkey")) {
                    monkeyNum = Character.getNumericValue(result[1].charAt(0));
                }
                

                // everything is working except for starting items.. can't figure out how to clear it without deleting it?
                // it doesn't make sense why the whole list is emptying..
                // monkey objects are created correctly, now can do the actual work with them
                
                List<Integer> startingItemsForMonkey = new ArrayList<>();
                // initializing starting items
                if (result[0].equals("Starting")) {
                    String regexPattern = "\\b\\d+\\b";
                    Pattern pattern = Pattern.compile(regexPattern);
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        int startingNumberToAdd = Integer.parseInt(matcher.group());
                        startingItemsForMonkey.add(startingNumberToAdd);
                        System.out.println("STARTING NUM " + startingNumberToAdd);
                    }
                }

                // initializing operation
                if (result[0].equals("Operation:")) {
                    Matcher matcher = getNumberFromString(line);
                    if (matcher.find()) {
                        String numberStr = matcher.group();
                        operationNum = Integer.parseInt(numberStr);
                    } else {
                        // must be "old" -- save number as 0
                        operationNum = 0;
                    }

                    // check if the operation type is * (true) or + (false)
                    shouldMultiply = getOperationType(line);
                    if (shouldMultiply) {
                        operationChar = '*';
                    } else {
                        operationChar = '+';
                    }
                }

                // initializing test (get the number it's divisible by)
                if (result[0].equals("Test:")) {
                    Matcher matcher = getNumberFromString(line);
                    if (matcher.find()) {
                        String numberStr = matcher.group();
                        numberDivisibleBy = Integer.parseInt(numberStr);
                    }
                }

                // initializing boolean scenarios
                if (result[0].equals("If")) {
                    Matcher matcher = getNumberFromString(line);
                    if (matcher.find()) {
                        String numberStr = matcher.group();
                        int monkeyToThrowTo = Integer.parseInt(numberStr);
                        if (result[1].equals("true:"))  {
                            monkeyToThrowToTrue = monkeyToThrowTo;
                        }
                        else if (result[1].equals("false:"))  {
                            monkeyToThrowToFalse = monkeyToThrowTo;
                        }
                    }
                }

                // for (int i = 0; i < startingItems.size(); i++) {
                //         System.out.println("ITEMS: " + startingItems.get(i));
                //     }

                if ((lineCount % 7) == 0) {
                    // System.out.println("adding monkey with these values:");
                    System.out.println("Monkey " + monkeyNum + ":");
                    System.out.println("Starting items: " + startingItemsForMonkey);
                    System.out.println("Operation: new = old " + operationChar + " " + operationNum);
                    System.out.println("Test: divisible by " + numberDivisibleBy);
                    System.out.println("If true: throw to monkey " + monkeyToThrowToTrue);
                    System.out.println("If false: throw to monkey " + monkeyToThrowToFalse);
                    System.out.println();

                    Monkey monkey = new Monkey(monkeyNum, startingItemsForMonkey, operationChar, operationNum, numberDivisibleBy,
                        monkeyToThrowToTrue, monkeyToThrowToFalse);
                    monkeys.add(monkey);
                }

                // // emptying the list for the next monkey's starting items to be added
                // startingItemsForMonkey.clear();
                lineCount++;
                // System.out.println("adding line : " + line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // return lines;
        return monkeys;
    }

    public List<Monkey> getMonkeyList() {
        List<Monkey> monkeys = openFileInitializeMonkeys();
        System.out.println("THERE ARE THIS MANY MONKEYS: " + monkeys.size());
        // for(int i = 0; i < monkeys.size(); i++) {
        //     System.out.println(monkeys.get(i));
        // }
        return monkeys;
    }

    public Matcher getNumberFromString(String line) {
        // regex pattern to get the number out of the string to divide by
        String regexPattern = "\\d+";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(line);
        return matcher;
    }

    public static boolean getOperationType(String input) {
        Pattern pattern = Pattern.compile("\\*"); // Pattern to match *, false if it's +
        Matcher matcher = pattern.matcher(input);
        
        return matcher.find();
    }

    public static void main(String [] args) {
        Day11AdventOfCode day11Obj = new Day11AdventOfCode();
        // Monkey monkey = new Monkey();
        // monkey.getMonkey();
        // day11Obj.openFile();
        // day11Obj.openFileInitializeMonkeys();
        day11Obj.getMonkeyList();
    }
}