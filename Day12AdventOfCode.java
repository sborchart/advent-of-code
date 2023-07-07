import java.util.*;
import java.io.*;

// height map: a is lowest, z is highest
// can only move up, down, left, right 1 step at a time
// can only move to a square that is at most 1 level higher (so equal and below are fine)
// starting position: S
// end position: E

// use dijkstra's shortest path algorithm


public class Day12AdventOfCode {

    public Day12AdventOfCode() {
        // constructor
    }

    // open file and initialize char array
    public char[][] initializeArray() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("/Users/sophieborchart/advent_of_code/day12input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] letterArray = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                if (j < line.length()) {
                    letterArray[i][j] = line.charAt(j);
                } else {
                    letterArray[i][j] = ' ';
                }
            }
        }

        // Display the 2D array
        // for (int i = 0; i < rows; i++) {
        //     for (int j = 0; j < cols; j++) {
        //         System.out.print(letterArray[i][j]);
        //     }
        // System.out.println();
        // }
        return letterArray;
    }
        
    public static void main(String [] args) {
        Day12AdventOfCode day12Obj = new Day12AdventOfCode();
        char [][] letterWeights = day12Obj.initializeArray();

        // use breadth first search to find the shortest path
        ShortestPath shortestPathFinder = new ShortestPath();
        int steps = shortestPathFinder.findShortestPath(letterWeights);

        System.out.println("Shortest path: " + steps + " steps");

    }
}
