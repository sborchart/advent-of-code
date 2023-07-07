import java.util.*;

public class ShortestPath {
    // can only go up, down, left, and right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int findShortestPath(char[][] grid) {
        int numRows = grid.length;
        int numColumns = grid[0].length;

        // Find the starting position and end position
        int startRow = -1, startColumn = -1, endRow = -1, targetColumn = -1;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (grid[i][j] == 'S') {
                    startRow = i;
                    startColumn = j;
                } else if (grid[i][j] == 'E') {
                    endRow = i;
                    targetColumn = j;
                }
            }
        }

        // Breadth-first search algorithm
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[numRows][numColumns];
        int[][] steps = new int[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            Arrays.fill(steps[i], Integer.MAX_VALUE);
        }

        queue.offer(new int[]{startRow, startColumn});
        visited[startRow][startColumn] = true;
        steps[startRow][startColumn] = 0;

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int row = position[0];
            int column = position[1];

            if (row == endRow && column == targetColumn) {
                return steps[row][column];
            }

            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newColumn = column + direction[1];

                if (isValidPosition(newRow, newColumn, numRows, numColumns) && !visited[newRow][newColumn]) {
                    char currentChar = grid[row][column];
                    char nextChar = grid[newRow][newColumn];
                    int currentWeight = getWeight(currentChar);
                    int nextWeight = getWeight(nextChar);

                    if (nextWeight - currentWeight <= 1) {
                        queue.offer(new int[]{newRow, newColumn});
                        visited[newRow][newColumn] = true;
                        steps[newRow][newColumn] = steps[row][column] + 1;
                    }
                }
            }
        }
        // not able to find a path 
        return -1;
    }

    // checks whether a given position is within the valid range based off the num of rows + cols
    // false if position is outside the valid range of the 2D char array (height map)
    public static boolean isValidPosition(int row, int column, int numRows, int numColumns) {
        return row >= 0 && row < numRows && column >= 0 && column < numColumns;
    }

    // finding the start and end positions by using start 'S' = 0 and end 'E' = 27 to be unique from the a-z 1-26
    public static int getWeight(char c) {
        if (c == 'S') {
            return 0;
        } else if (c == 'E') {
            return 27;
        } else {
            return c - 'a' + 1;
        }
    }
}
