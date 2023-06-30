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
        // System.out.println("Number of visible trees is: " + numberOfVisibleTrees);
        return numberOfVisibleTrees;
    }   


    public int day8_part2() {
        List<String> lines = openFile();
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] trees = new char[rows][cols];
        int[][] scenicScores = new int[rows][cols];
        
        // create a 2D char array from the lines
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                trees[i][j] = line.charAt(j);
            }
        }
        
        // loop through all of the trees in the array
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                int[] treeColumns = new int[rows];
                int[] treeRows = new int[cols];
                for (int k = 0; k < rows; k++) {
                    treeColumns[k] = trees[k][j] - trees[i][j];
                    // System.out.println(treeColumns[k]);
                }
                for (int k = 0; k < cols; k++) {
                    treeRows[k] = trees[i][k] - trees[i][j];
                    // System.out.println(treeRows[k]);
                }
                // System.out.println(scenicScores[i][j]);
                // good until here, problem is in part2 method
                scenicScores[i][j] = part2(treeColumns, treeRows, i, j, scenicScores);
            }
        }
        
        int maxScore = Arrays.stream(scenicScores)
                            .flatMapToInt(Arrays::stream)
                            .max()
                            .getAsInt();
        // for (int i = 0; i < rows; i++) {
        //     for (int j = 0; j < cols; j++) {
        //         System.out.println(scenicScores[i][j]);
        //     }
        // }
        System.out.println("PART 2 -- Highest scenic score possible for any tree: " + maxScore);
        return maxScore;
    }

    private int part2(int[] treeColumns, int[] treeRows, int i, int j, int[][] scenicScores) {
        // start at column j - 1 up to the beginning of the array in iterations of -1 to go from right to left, etc

        int[][] directions = {
            Arrays.copyOfRange(treeRows, 0, j),
            Arrays.copyOfRange(treeRows, j + 1, treeRows.length),
            Arrays.copyOfRange(treeColumns, 0, i),
            Arrays.copyOfRange(treeColumns, i + 1, treeColumns.length)
        };

        // int[] direction1 = Arrays.copyOfRange(treeRows, j - 1, -1);
        // int[] direction2 = Arrays.copyOfRange(treeRows, j + 1, treeRows.length);
        // int[] direction3 = Arrays.copyOfRange(treeColumns, i - 1, -1);
        // int[] direction4 = Arrays.copyOfRange(treeColumns, i + 1, treeColumns.length);
        // int[][] directions = {direction1, direction2, direction3, direction4};

        for (int x = 0; x < directions.length; x++) {
            for (int y = 0; y < directions[x].length; y++) {
                // System.out.println("DIRECTIONS");
                System.out.print(directions[x][y]);
            }
        }
    
        int score = 1;
        for (int[] direction : directions) {
            score *= computeScenicScore(direction);
        }
        return score;
    }

    private int computeScenicScore(int[] route) {
        int len = route.length;
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            if (route[i] >= 0) {
                maxLen = i + 1;
            }
        }
        return (maxLen > 0) ? maxLen : len;
    }

    // public static int computeScenicScore(int[] route) {
    //     // determine which trees are taller (>=0) by determining the difference to the current tree in the loop
    //     List<Boolean> tallestTreesArray = new ArrayList<>();
    //     for (int i = 0; i < route.length; i++) {
    //         if (route[i] >= 0) {
    //             tallestTreesArray.add(true);
    //         } else {
    //             tallestTreesArray.add(false);
    //         }
    //     }

    //     if (tallestTreesArray.contains(true)) {
    //         // get the first index that is taller or equally as tall,
    //         // index(True) returns the index of the first element in list with value True
    //         return tallestTreesArray.indexOf(true) + 1;
    //     } else {
    //         // no routes >= 0, so the scenic score is the maximum possible length of the route
    //         return tallestTreesArray.size();
    //     }
    // }


    public static void main(String [] args) {
        Day8AdventOfCode day8Obj = new Day8AdventOfCode();
        day8Obj.bothParts();
        // day8Obj.partTwoSolo();
        day8Obj.day8_part2();
    }
}