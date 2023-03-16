import java.util.*;
import java.io.*;

public class Day7AdventOfCode {

    public Day7AdventOfCode() {

    }

    public static void main(String [] args) {;
        readLinesTest();
    }


    public static void readLinesTest() {
        BufferedReader reader;
        LinkedList<String> directory1 = new LinkedList<String>();
        try {
			reader = new BufferedReader(new FileReader("/Users/sophieborchart/advent_of_code/day7input.txt"));
			String line = reader.readLine();
            while(line != null) {
                if (line.contains("ls")) {
                    while((line = reader.readLine()) != null) {
                        if(!line.contains("$") && !line.contains("dir")) {
                            // System.out.println("Lines in dir: " + line);
                            totalSize(line);
                        }
                        if(line.contains("cd") && !line.contains("cd ..")) {
                            System.out.println("new command: " + line);
                            // if (!(line = reader.readLine()).contains("ls")) {
                            //     System.out.println("line to process: " + line);
                                // StringBuilder fileSize = new StringBuilder();
                                // char [] ch = line.toCharArray();
                                // for (char c: ch) {
                                //     if(Character.isDigit(c)) {
                                //         fileSize.append(c);
                                //     }
                                // }
                            // System.out.println("FILE SIZE IS: " + fileSize);
                            // }
                        }
                    }
                }
                line = reader.readLine();
            }
        }
        catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static StringBuilder totalSize(String line) {
        StringBuilder fileSize = new StringBuilder();
        char [] ch = line.toCharArray();
        for (char c: ch) {
            if(Character.isDigit(c)) {
                fileSize.append(c);
                }
            }
        System.out.println("FILE SIZE IS: " + fileSize);
        return fileSize;
    }

}