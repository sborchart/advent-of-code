import java.util.*;
import java.io.*;

public class Day8AdventOfCode {

    public Day8AdventOfCode() {
        // constructor
    }

    public List<String> openFile() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("/Users/sophieborchart/advent_of_code/day8input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                lines.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void bothParts() {
        List<String> lines = openFile();
        int rows = lines.size();
        int columns = lines.get(0).length();
        int [][] trees = new int[rows][columns];

        // initialize the tree array
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                trees[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        int numberOfVisibleTrees = (2 * (lines.size() - 2) + (2 * lines.get(0).length()));

        int [][] scenicScores = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                scenicScores[i][j] = 0;
            }
        }

        // for loop to iterate through all of the trees in the array except edge trees since their scenic score will always be 0
        for (int i = 1; i < trees.length - 1; i++) {
            for (int j = 1; j < trees[0].length - 1; j++) {
                // int [] treeColumns = new int[trees.length];
                // for (int k = 0; k < trees.length; k++) {
                //     treeColumns[k] = trees[k][j] - trees[i][j];
                // }
                // int [] treeRows = new int[trees[0].length];
                // for (int k = 0; k < trees[0].length; k++) {
                //     treeRows[k] = trees[i][k] - trees[i][j];
                // }
                numberOfVisibleTrees = partOne(trees, i, j, numberOfVisibleTrees);
                // scenicScores[i][j] = partTwo(treeColumns, treeRows, i, j, scenicScores);
            }
        }
        // int maximumValue = 0;
        // for (int i = 0; i < scenicScores.length; i++) {
        //     for (int j = 0; j < scenicScores[0].length; j++) {
        //         if (scenicScores[i][j] > maximumValue) {
        //             maximumValue = scenicScores[i][j];
        //         }
        //     }
        // }
        System.out.println("PART 1 -- Number of visible trees is: " + numberOfVisibleTrees);
        // System.out.println("PART 2 -- Highest scenic score possible for any tree: " + maximumValue);
    }

    public int partOne(int [][] trees, int i, int j, int numberOfVisibleTrees) {
        int treeRows = trees.length;
        int treeColumns = trees[0].length;


        int [] up = new int[j];
        int [] down = new int[treeColumns - j - 1];
        int [] left = new int[i];
        int [] right = new int[treeRows - i - 1];

        for (int k = 0; k < j; k++) {
            up[k] = trees[i][k] - trees[i][j];
        }
        for (int k = j + 1; k < treeColumns; k++) {
            down[k - j - 1] = trees[i][k] - trees[i][j];
        }
        for (int k = 0; k < i; k++) {
            left[k] = trees[k][j] - trees[i][j];
        }
        for (int k = i + 1; k < treeRows; k++) {
            right[k - i - 1] = trees[k][j] - trees[i][j];
        }

        int [][] treesToConsider = {up, down, left, right};

        boolean treeIsVisible = false;
        for (int [] direction : treesToConsider) {
            treeIsVisible |= Arrays.stream(direction).allMatch(val -> val < 0);
        }

        if (treeIsVisible) {
            numberOfVisibleTrees++;
        }
        System.out.println("Number of visible trees is: " + numberOfVisibleTrees);
        return numberOfVisibleTrees;
    }

    // public int partTwo(int[] treeColumns, int[] treeRows, int i, int j, int[][] scenicScores) {
    //     int[][] directions = new int[4][];
    //     directions[0] = Arrays.copyOfRange(treeRows, j-1, -1); // equivalent to tree_rows[j - 1:: - 1]
    //     directions[1] = Arrays.copyOfRange(treeRows, j+1, treeRows.length); // equivalent to tree_rows[j + 1:]
    //     directions[2] = Arrays.copyOfRange(treeColumns, i-1, -1); // equivalent to tree_columns[i - 1:: - 1]
    //     directions[3] = Arrays.copyOfRange(treeColumns, i+1, treeColumns.length); // equivalent to tree_columns[i + 1:]

    //     int product = 1;
    //     for (int[] direction : directions) {
    //         product *= computeScenicScore(direction);
    //     }
    //     scenicScores[i][j] = product;
    //     return scenicScores[i][j];
    // }

    public int computeScenicScore(int [] route) {
        List<Boolean> tallestTrees = new ArrayList<>();
        for (int height : route) {
            tallestTrees.add(height >= 0);
        }
        if (tallestTrees.contains(true)) {
            return (tallestTrees.indexOf(true) + 1);
        }
        else {
            return tallestTrees.size();
        }
    }

    public static void main(String [] args) {
        Day8AdventOfCode day8Obj = new Day8AdventOfCode();
        day8Obj.bothParts();
    }
}