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
        public int numTimesInspectedItem;

        // Monkey constructor 
        public Monkey(int monkeyNumber, List<Integer> startingItems, char operation, int operationNumber,
            int numberDivisibleBy, int monkeyToThrowToTrue, int monkeyToThrowToFalse, int numTimesInspectedItem) {
                this.monkeyNumber = monkeyNumber;
                this.startingItems = startingItems;
                this.operation = operation;
                this.operationNumber = operationNumber;
                this.numberDivisibleBy = numberDivisibleBy;
                this.monkeyToThrowToTrue = monkeyToThrowToTrue;
                this.monkeyToThrowToFalse = monkeyToThrowToFalse;
                this.numTimesInspectedItem = numTimesInspectedItem;
        }
    }

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
            List<Integer> startingItemsForMonkey = new ArrayList<>();
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
                
                // initializing starting items -- WORRY LEVEL
                if (result[0].equals("Starting")) {
                    String regexPattern = "\\b\\d+\\b";
                    Pattern pattern = Pattern.compile(regexPattern);
                    Matcher matcher = pattern.matcher(line);
                    startingItemsForMonkey.clear();
                    while (matcher.find()) {
                        int startingNumberToAdd = Integer.parseInt(matcher.group());
                        startingItemsForMonkey.add(startingNumberToAdd);
                    }
                }

                // initializing operation -- HOW WORRY LEVEL CHANGES AS MONEKY INSPECTS EACH ITEM
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

                // initializing boolean scenarios -- HOW MONKEY USES WORRY LEVEL TO DECIDE WHERE TO THROW
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
                // After each monkey inspects an item but before it tests your worry level,
                // your relief that the monkey's inspection didn't damage the item causes your worry level
                // to be divided by three and rounded down to the nearest integer.

                if ((lineCount % 7) == 0) {
                    System.out.println("adding monkey with these values:");
                    System.out.println("Monkey " + monkeyNum + ":");
                    System.out.println("Starting items: " + startingItemsForMonkey);
                    System.out.println("Operation: new = old " + operationChar + " " + operationNum);
                    System.out.println("Test: divisible by " + numberDivisibleBy);
                    System.out.println("If true: throw to monkey " + monkeyToThrowToTrue);
                    System.out.println("If false: throw to monkey " + monkeyToThrowToFalse);
                    System.out.println();

                    List<Integer> newListOfStartingItemsForMonkey = new ArrayList<>(startingItemsForMonkey);
                    Monkey monkey = new Monkey(monkeyNum, newListOfStartingItemsForMonkey, operationChar, operationNum, numberDivisibleBy,
                        monkeyToThrowToTrue, monkeyToThrowToFalse, 0);
                    monkeys.add(monkey);
                }
                lineCount++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return monkeys;
    }

    public List<Monkey> getMonkeyList() {
        List<Monkey> monkeys = openFileInitializeMonkeys();
        return monkeys;
    }

    public int runOneRound(List<Monkey> monkeyList) {
        // iterate through each monkey in the monkey list
        for (int i = 0; i < monkeyList.size(); i++ ) {
            // iterate through each item the monkey has
            Monkey currentMonkey = monkeyList.get(i);
            System.out.println("MONKEY " + i + ":");
            int worryLevel = currentMonkey.operationNumber;
            int numberOfItemsMonkeyStartedWith = currentMonkey.startingItems.size();
            System.out.println("Monkey " + i + " is starting with " + numberOfItemsMonkeyStartedWith + " items!");
            for (int j = 0; j < currentMonkey.startingItems.size(); j++) {
                int currentStartingItem = currentMonkey.startingItems.get(j);
                System.out.println("Monkey inspects an item with a worry level of " + currentStartingItem);
                currentMonkey.numTimesInspectedItem++;

                int newWorryLevel = 0;
                if (currentMonkey.operation == '*') {
                    newWorryLevel = currentStartingItem * worryLevel;
                    // System.out.println("Operation is *");
                } else {
                    // System.out.println("Operation is +");
                    newWorryLevel = currentStartingItem + worryLevel;
                }
                if (worryLevel == 0) {
                    newWorryLevel = currentStartingItem * currentStartingItem;
                    // worry level is multiplied by itself
                    System.out.println("Worry level is multiplied by itself to " + newWorryLevel);
                }
                else {
                    // multiply starting item number by worry level
                    System.out.println("Worry level is multiplied by " + worryLevel + " to " + newWorryLevel);
                }
                // divide by 3 after monkey is bored
                newWorryLevel = newWorryLevel / 3;
                System.out.println("Monkey gets bored with item. Worry level is divided by 3 to " + newWorryLevel);

                // test worry level / divisibility to determine which monkey to throw to
                int divisibilityTest = currentMonkey.numberDivisibleBy;

                int monkeyToThrowTo = 0;
                if (newWorryLevel % divisibilityTest == 0) {
                    System.out.println("Current worry level is divisible by " + divisibilityTest);
                    monkeyToThrowTo = currentMonkey.monkeyToThrowToTrue;
                } else {
                    System.out.println("Current worry level is not divisible by " + divisibilityTest);
                    monkeyToThrowTo = currentMonkey.monkeyToThrowToFalse;
                }

                monkeyList.get(monkeyToThrowTo).startingItems.add(newWorryLevel);
                System.out.println("Item with worry level " + newWorryLevel + " is thrown to monkey " + monkeyToThrowTo);
            }
            System.out.println();
            for (int x = 0; x < numberOfItemsMonkeyStartedWith; x++) {
                currentMonkey.startingItems.remove(0);
            }
        }
        List<Integer> timesInspectedList = new ArrayList<>();
        for (int i = 0; i < monkeyList.size(); i++) {
            for (int j = 0; j < monkeyList.get(i).startingItems.size(); j++) {
                System.out.println("Monkey " + i + ": " + monkeyList.get(i).startingItems.get(j));
            }
            System.out.println("Monkey " + i + " inspected items " + monkeyList.get(i).numTimesInspectedItem + " times");
            timesInspectedList.add(monkeyList.get(i).numTimesInspectedItem );
        }
        for (int z = 0; z < timesInspectedList.size(); z++) {
            System.out.println("NUMBERS INSPECTED: " + timesInspectedList.get(z));
        }
        List<Integer> twoLargestNums = findTwoLargestNumbers(timesInspectedList);
        System.out.println("First largest number is: " + twoLargestNums.get(0));
        System.out.println("Second largest number is: " + twoLargestNums.get(1));
        // something is a bit wonky, it's working for the example but not for mine, might have an off by one error.
        return (twoLargestNums.get(0) * (twoLargestNums.get(1)-1));
    }

    public static List<Integer> findTwoLargestNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two numbers");
        }
        // Sort the list in descending order
        Collections.sort(numbers, Collections.reverseOrder());
        // Return a new list containing the two largest numbers
        return numbers.subList(0, 2);
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
        List<Monkey> monkeys = day11Obj.getMonkeyList();
        int answer = 0;
        for (int i = 1; i < 21; i++) {
            System.out.println("ROUND " + i);
            answer = day11Obj.runOneRound(monkeys);
        }
        System.out.println("LEVEL OF MONKEY BUSINESS AFTER 20 ROUNDS OF SHENANIGANS: " + answer);
    }
}