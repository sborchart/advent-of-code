import java.util.*;
import java.io.*;

public class Day7AdventOfCode {

    public String puzzleInput = "/Users/sophieborchart/advent_of_code/day7input.txt";

    public Day7AdventOfCode() {
        // constructor
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
                // might want to strip whitespaces first?
                lines.add(data);
                // System.out.println(data);
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
            String line = lines.get(0);
            lines.remove(0);
            if (line.equals("$ cd /")) {
                tree.current = tree.root;
            }
            else if(line.equals("$ ls")) {
                while (lines.size() > 0 && !lines.get(0).contains("$")) {
                    //
                }
            }
            else if(line.equals("$ cd ..")) {
                tree.current = tree.current.parent;
            }
            else if(line.contains("$ cd")) {
                //
            }
        }
        // tree is now built! can do parts 1 and 2 now
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
            child.parent = this.parent;
            this.children.add(child);
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

        public int partOne() {
            int dirSizes = 0;
            if (this.isDirectory) {
                for (int i = 0; i < children.size(); i++) {
                    if (children.get(i).isDirectory && children.get(i).getSize() <= 10000) {
                        dirSizes += children.get(i).getSize() + children.get(i).partOne();
                    }
                    else {
                        dirSizes += children.get(i).partOne();
                    }
                }
            }
            return dirSizes;
        }
    }

    public static void main(String [] args) {
        Day7AdventOfCode partOne = new Day7AdventOfCode();
        partOne.makeTree();
    }
}