import java.util.*;
import java.util.regex.*;


// java attempt --- ran into too many stupid problems with syntax stuff that took too long to figure out so just did it in
// python much faster... will try to do the java solution another time


//                     [L]     [H] [W]
//                 [J] [Z] [J] [Q] [Q]
// [S]             [M] [C] [T] [F] [B]
// [P]     [H]     [B] [D] [G] [B] [P]
// [W]     [L] [D] [D] [J] [W] [T] [C]
// [N] [T] [R] [T] [T] [T] [M] [M] [G]
// [J] [S] [Q] [S] [Z] [W] [P] [G] [D]
// [Z] [G] [V] [V] [Q] [M] [L] [N] [R]
//  1   2   3   4   5   6   7   8   9 

public class Day5AdventOfCode {

    private static ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>() {
        {
            add(stack1);
        }
    };

    private static ArrayList<ArrayList<String>> stackOfStacks = new ArrayList<ArrayList<String>>() {
        {
            add(stack1);
            add(stack2);
            add(stack3);
            add(stack4);
            add(stack5);
            add(stack6);
            add(stack7);
            add(stack8);
            add(stack9);
        }
    };

    private static ArrayList<String> stack1 = new ArrayList<String>() {
        {
            add("Z");
            add("J");
            add("N");
            add("W");
            add("P");
            add("S");
        }
    };

    private static ArrayList<String> stack2 = new ArrayList<String>() {
        {
            add("G");
            add("S");
            add("T");
        }
    };

    private static ArrayList<String> stack3 = new ArrayList<String>() {
        {
            add("V");
            add("Q");
            add("R");
            add("L");
            add("H");
        }
    };

    private static ArrayList<String> stack4 = new ArrayList<String>() {
        {
            add("V");
            add("S");
            add("T");
            add("D");
        }
    };

    private static ArrayList<String> stack5 = new ArrayList<String>() {
        {
            add("Q");
            add("Z");
            add("T");
            add("D");
            add("B");
            add("M");
            add("J");
        }
    };

    private static ArrayList<String> stack6 = new ArrayList<String>() {
        {
            add("M");
            add("W");
            add("T");
            add("J");
            add("D");
            add("C");
            add("Z");
            add("L");
        }
    };

    private static ArrayList<String> stack7 = new ArrayList<String>() {
        {
            add("L");
            add("P");
            add("M");
            add("W");
            add("G");
            add("T");
            add("J");
        }
    };

    private static ArrayList<String> stack8 = new ArrayList<String>() {
        {
            add("N");
            add("G");
            add("M");
            add("T");
            add("B");
            add("F");
            add("Q");
            add("H");
        }
    };

    private static ArrayList<String> stack9 = new ArrayList<String>() {
        {
            add("R");
            add("D");
            add("G");
            add("C");
            add("P");
            add("B");
            add("Q");
            add("W");
        }
    };

    private static String patternForMove = "move (.+) from (.+) to (.+)";

    public Day5AdventOfCode() {
    }

    public static void main(String [] args) {
        System.out.println(test);
        // System.out.println(stackOfStacks);
        // stackOfStacks.remove(0);
        // System.out.println(stackOfStacks);
        // day5part1();
    }

    public static void day5part1() {
        System.out.println("Pattern is: " + patternForMove);
        Pattern p = Pattern.compile(patternForMove);
        String line = "move 1 from 3 to 5";
        Matcher m = p.matcher(line);
        if (m.find()) {
            int numObjectsToMove = Integer.parseInt(m.group(1));
            int stackLosingNum = Integer.parseInt(m.group(2));
            int stackGainingNum = Integer.parseInt(m.group(3));
            for (int i = 0; i < numObjectsToMove; i++) {
                System.out.println("hi");
                List<String> stackLosing = stackOfStacks.get(stackLosingNum-1);
                String removedLetter = stackLosing.get(stackLosing.size()-1);
                System.out.println(removedLetter);
                int lastObjIndex = stackLosing.size()-1;
                System.out.println(lastObjIndex);
                // stackLosing.remove(0);
                System.out.println("New stack is: " + stackLosing);
                System.out.println("bye");
            }
        }
    }

}