package resources.generator;

import java.io.*;
import java.util.*;

public class randomStudents {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("src/resources/generator/names.txt"));
            BufferedWriter write = new BufferedWriter(new FileWriter("src/resources/fakedata.txt"));

            ArrayList<String> firstNames = new ArrayList<String>();
            ArrayList<String> lastNames = new ArrayList<String>();
            ArrayList<String> classNames = new ArrayList<String>();
            while (scan.hasNextLine()) {
                firstNames.add(scan.next());
                lastNames.add(scan.next());
                scan.nextLine();
            }
            scan = new Scanner(new File("src/resources/generator/classnum.txt"));
            while (scan.hasNextLine()) {
                classNames.add(scan.next());
                scan.nextLine();
            }
            System.out.println(firstNames.size() + " " + lastNames.size() + " " + classNames.size());
            Random rand = new Random();
            for (int i = 0; i < 1000; i++) {
                String first = firstNames.get(rand.nextInt(30));
                String last = lastNames.get(rand.nextInt(30));
                int firstFive = rand.nextInt(10000) + 10001;
                int lastFive = rand.nextInt(10000) + 10001;
                String SID = firstFive + "" + lastFive;
                String className = classNames.get(rand.nextInt(110));
                int siteNum = rand.nextInt(3) + 1;
                int hw1 = rand.nextInt(6) + 5;
                int hw2 = rand.nextInt(6) + 5;
                int hw3 = rand.nextInt(6) + 5;
                int proj = rand.nextInt(20) + 1;
                int exam1 = rand.nextInt(30) + 71;
                int exam2 = rand.nextInt(40) + 61;
                String line = first + " " + last + " " + SID + " " + className + " " + siteNum + " " + hw1 + " " + hw2
                        + " " + hw3 + " " + proj + " " + exam1 + " " + exam2;
                write.write(line);
                write.newLine();
            }
            write.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
