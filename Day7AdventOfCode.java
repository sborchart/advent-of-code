import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;

public class Day7AdventOfCode {

    public String puzzleInput = "/Users/sophieborchart/advent_of_code/day7input.txt";

    public Day7AdventOfCode() {
        // constructor
    }

    public boolean isNumeric(String str) { 
        try {
            Double.parseDouble(str);  
            return true;
        } catch (NumberFormatException e) {  
            return false;  
        }
    }

    public void makeTree() {
        // read the file input
        System.out.println(puzzleInput);
        File fileInput = new File(puzzleInput);
        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(fileInput);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(data.trim());
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File can't be found!!!");
            e.printStackTrace();
        }

        // build the tree
        Tree tree = new Tree();
        while (lines.size() > 0) {
            String line = lines.remove(0);
            if (line.equals("$ cd /")) {
                tree.current = tree.root;
            }
            else if(line.equals("$ ls")) {
                while (lines.size() > 0 && !lines.get(0).contains("$")) {
                    line = lines.remove(0);
                    String[] result = line.split(" ");
                    String size = result[0];
                    String name = result[1];
                    TreeNode newNode = new TreeNode(false, name, 0);
                    if (isNumeric(size)) {
                        newNode.setSize(Integer.parseInt(size));
                    } else {
                        newNode.setDirBoolean(true);
                    }
                    tree.addChild(newNode);
                }
            }
            else if(line.equals("$ cd ..")) {
                tree.current = tree.current.parent;
            }
            else if(line.contains("$ cd")) {
                String [] result = line.split(" ");
                String name = result[2];
                List<TreeNode> children = tree.current.children;
                for (TreeNode child : children) {
                    if (child.name.equals(name)) {
                        tree.current = child;
                        break;
                    }
                }
            }
        }
        // tree is now built! can do parts 1 and 2 now
        // part 1
        int allDirsSize = tree.root.partOne();
        System.out.println("Part one answer is: " + allDirsSize);

        // part 2
        int totalSpaceOnDisk = 70000000;
        int spaceNeededForDisk = 30000000;
        int currentEmptySpace = totalSpaceOnDisk - tree.root.getSize();
        int emptySpaceNeeded = spaceNeededForDisk - currentEmptySpace;
        ArrayList<Integer> potentialDirsPartTwo = tree.root.partTwo(emptySpaceNeeded);
        potentialDirsPartTwo.sort(Comparator.naturalOrder());
        System.out.println("Part two answer is: " + potentialDirsPartTwo.get(0));
    }

    public class Tree {
        TreeNode root = new TreeNode(true, "root", 0);
        TreeNode current;

        public Tree() {
            this.root = root;
            this.current = this.root;
        }

        public void addChild(TreeNode child) {
            this.current.treeNodeAddChild(child);
        }

        public TreeNode getParent() {
            return this.current.treeNodeGetParent();
        }
    }

    public class TreeNode {

        public boolean isDirectory;
        public String name;
        public int fileSize;
        public ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        public TreeNode parent;

        public TreeNode(boolean isDirectory, String name, int fileSize) {
            this.isDirectory = isDirectory;
            this.name = name;
            this.fileSize = fileSize;
            this.children = children;
            this.parent = parent;
        }

        public void treeNodeAddChild(TreeNode child) {
            child.setParent(this);
            this.children.add(child);
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public TreeNode treeNodeGetParent() {
            return this.parent;
        }

        public void setDirBoolean(boolean value) {
            isDirectory = value;
        }

        public int getSize() {
            int totalSize = 0;
            if (this.isDirectory) {
                for (int i = 0; i < children.size(); i++) {
                    totalSize += children.get(i).getSize();
                }
                return totalSize;
            }
            else {
                return this.fileSize;
            }
        }

        public void setSize(int sizeToBeSet) {
            this.fileSize = sizeToBeSet;
        }


        // refactor so parts one and two are in one method if possible


        public int partOne() {
            int dirSizes = 0;
            if (this.isDirectory) {
                for (TreeNode child : this.children) {
                    if (child.isDirectory && child.getSize() <= 100000) {
                        dirSizes += child.getSize() + child.partOne();
                    } else {
                        dirSizes += child.partOne();
                    }
                }
            }
            return dirSizes;
        }

        public ArrayList<Integer> partTwo(int minimumSize) {
            ArrayList<Integer> potentialDirSizes = new ArrayList<>();
            if (this.isDirectory) {
                for (TreeNode child : this.children) {
                    if (child.isDirectory && child.getSize() >= minimumSize) {
                        potentialDirSizes.add(child.getSize());
                        potentialDirSizes.addAll(child.partTwo(minimumSize));
                    } else {
                        potentialDirSizes.addAll(child.partTwo(minimumSize));
                    }
                }
            }
            return potentialDirSizes;
        }
    }

    public static void main(String [] args) {
        Day7AdventOfCode partOne = new Day7AdventOfCode();
        partOne.makeTree();
    }
}