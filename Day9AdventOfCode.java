import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Day9AdventOfCode {

    public Day9AdventOfCode() {
        // constructor
    }

    // helper class to create Tuple objects
    public class Tuple<A, B> {
        public final A a;
        public final B b;

        public Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

     private int [] updateTail(int[] head, int[] tail) {
        Map<Tuple<Integer, Integer>, Tuple<Integer, Integer>> changeForTail = new HashMap<>();
        changeForTail.put(new Tuple<>(2, 1), new Tuple<>(1, 1));
        changeForTail.put(new Tuple<>(1, 2), new Tuple<>(1, 1));
        changeForTail.put(new Tuple<>(2, 0), new Tuple<>(1, 0));
        changeForTail.put(new Tuple<>(2, -1), new Tuple<>(1, -1));
        changeForTail.put(new Tuple<>(1, -2), new Tuple<>(1, -1));
        changeForTail.put(new Tuple<>(0, -2), new Tuple<>(0, -1));
        changeForTail.put(new Tuple<>(-1, -2), new Tuple<>(-1, -1));
        changeForTail.put(new Tuple<>(-2, -1), new Tuple<>(-1, -1));
        changeForTail.put(new Tuple<>(-2, 0), new Tuple<>(-1, 0));
        changeForTail.put(new Tuple<>(-2, 1), new Tuple<>(-1, 1));
        changeForTail.put(new Tuple<>(-1, 2), new Tuple<>(-1, 1));
        changeForTail.put(new Tuple<>(0, 2), new Tuple<>(0, 1));

        int[] difference = new int[2];
        for (int i = 0; i < difference.length; i++) {
            difference[i] = head[i] - tail[i];
        }

        // this is returning the tuple object
        // Tuple<Integer, Integer> key = new Tuple<>(2, 1);
        // this is working
        // changeForTail.put(key, new Tuple<>(1, 1));
        // Tuple<Integer, Integer> value1 = changeForTail.get(key);
        // System.out.println("HERE, value is tuple" + value1);
        // // but this is null, even though key = new Tuple<>(2, 1);
        // Tuple<Integer, Integer> value2 = changeForTail.get(new Tuple<>(2, 1));
        // System.out.println("HERE, value is null though..." + value2);

        // this is the part that isn't working
        // need to retrieve value by preset key

        Tuple<Integer, Integer> zeros = new Tuple<>(0, 0);
        Tuple<Integer, Integer> differenceTuple = new Tuple<>(difference[0], difference[1]);

        Tuple<Integer, Integer> changeTuple = changeForTail.getOrDefault(differenceTuple, zeros);
        int[] newTailPosition = new int[]{tail[0] + changeTuple.a, tail[1] + changeTuple.b};

        System.out.println("testing KEY last value " + differenceTuple);
        Tuple<Integer, Integer> value3 = changeForTail.get(differenceTuple);
        System.out.println("testing VALUE last value " + value3);


        return newTailPosition;

        // Tuple<Integer, Integer> changeTuple = changeForTail.getOrDefault(new Tuple<>(difference[0], difference[1]), new Tuple<>(0, 0));
        // int[] newTailPosition = new int[]{tail[0] + changeTuple.a, tail[1] + changeTuple.b};
        // return newTailPosition;
    }

    private int [] updateHead(int[] head, String direction) {
        if (direction.equals("U")) {
            head[0] +=1;
        } else if (direction.equals('D')) {
            head[0] -= 1;
        } else if (direction.equals('R')) {
            head[1] += 1;
        } else if (direction.equals('L')) {
            head[1] -= 1;
        }
        return head;
    }

    public void partOne() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("/Users/sophieborchart/advent_of_code/day9input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                lines.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Tuple<String, Integer>> movementInstructions = new ArrayList<>();
        for (String entry : lines) {
            String[] parts = entry.trim().split(" ");
            String instruction = parts[0];
            int value = Integer.parseInt(parts[1]);
            Tuple<String, Integer> tuple = new Tuple<>(instruction, value);
            movementInstructions.add(tuple);
        }

        int[] tail = {0, 0};
        int[] head = {0, 0};

        Set<Tuple<Integer, Integer>> tailPositions = new HashSet<>();
        tailPositions.add(new Tuple<>(tail[0], tail[1]));

        for (Tuple<String, Integer> instruction : movementInstructions) {
            String direction = instruction.a;
            int distance = instruction.b;
            while (distance > 0) {
                head = updateHead(head, direction);
                updateTail(head, tail);
                tail = updateTail(head, tail);
                tailPositions.add(new Tuple<>(tail[0], tail[1]));
                distance -= 1;
            }
        }
        int numberOfPositionsTailVisits = tailPositions.size();
        System.out.println("The number of position the tail of the rope visits at least once is: " + numberOfPositionsTailVisits);
    }

     public static void main(String [] args) {
        Day9AdventOfCode day9Obj = new Day9AdventOfCode();
        day9Obj.partOne();
    }
}