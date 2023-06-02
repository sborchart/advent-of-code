import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Day9 {

    public Day9() {
        // constructor
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

    private int[] updateTail(int[] head, int[] tail) {
        HashMap<List<Integer>, List<Integer>> changeForTail = new HashMap<>();
        changeForTail.put(Arrays.asList(2, 1), Arrays.asList(1, 1));
        changeForTail.put(Arrays.asList(1, 2), Arrays.asList(1, 1));
        changeForTail.put(Arrays.asList(2, 0), Arrays.asList(1, 0));
        changeForTail.put(Arrays.asList(2, -1), Arrays.asList(1, -1));
        changeForTail.put(Arrays.asList(1, -2), Arrays.asList(1, -1));
        changeForTail.put(Arrays.asList(0, -2), Arrays.asList(0, -1));
        changeForTail.put(Arrays.asList(-1, -2), Arrays.asList(-1, -1));
        changeForTail.put(Arrays.asList(-2, -1), Arrays.asList(-1, -1));
        changeForTail.put(Arrays.asList(-2, 0), Arrays.asList(-1, 0));
        changeForTail.put(Arrays.asList(-2, 1), Arrays.asList(-1, 1));
        changeForTail.put(Arrays.asList(-1, 2), Arrays.asList(-1, 1));
        changeForTail.put(Arrays.asList(0, 2), Arrays.asList(0, 1));

        List<Integer> differenceList = new ArrayList<>();
        differenceList.add(head[0] - tail[0]);
        // System.out.println(head[0] - tail[0]);
        differenceList.add(head[1] - tail[1]);
        // System.out.println(differenceList.size());
        List<Integer> change = changeForTail.getOrDefault(differenceList, Arrays.asList(0, 0));
        int[] newTailPosition = new int[2];
        newTailPosition[0] = tail[0] + change.get(0);
        newTailPosition[1] = tail[1] + change.get(1);
        return newTailPosition;
    }

    public void partOne() {
        List<String[]> movementInstructions = new ArrayList<>();
        try {
            File file = new File("/Users/sophieborchart/advent_of_code/day9input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.trim().split(" ");
                movementInstructions.add(new String[]{parts[0], parts[1]});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int[] tail = {0, 0};
        int[] head = {0, 0};

        Set<String> tailPositions = new HashSet<>();
        tailPositions.add(tail[0] + "," + tail[1]);

        for (String[] instruction : movementInstructions) {
            String direction = instruction[0];
            int distance = Integer.parseInt(instruction[1]);
            while(distance > 0){
                head = updateHead(head, direction);
                tail = updateTail(head, tail);
                tailPositions.add(Arrays.toString(tail));
                distance -= 1;
            }
        }
        int numberOfPositionsTailVisits = tailPositions.size();
        // this is not the right answer :(
        System.out.println("The number of position the tail of the rope visits at least once is: " + numberOfPositionsTailVisits);
    }

    public static void main(String [] args) {
        Day9 day9Obj = new Day9();
        day9Obj.partOne();
    }


}