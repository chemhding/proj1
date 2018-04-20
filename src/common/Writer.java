package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    protected String destinationPath;

    protected BufferedWriter bw;

    public Writer(String destinationPath) {
        try {
            bw = new BufferedWriter(new FileWriter(destinationPath, true));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addString(String toBeWritten) {
        try {
            bw.write(toBeWritten);
            bw.newLine();
            bw.write("");
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    ////
    //// public static void main(String[] args) {
    //// Writer ww = new Writer("src/resources/ss.txt");
    //// ww.addString("aaaaaaaa");
    //// try {
    //// BufferedWriter bb = new BufferedWriter(new
    //// FileWriter("src/resources/ss.txt"));
    //// bb.write("XXX");
    //// bb.append(" ");
    //// bb.newLine();
    //// bb.close();
    //// } catch (IOException e) {
    //// // TODO Auto-generated catch block
    //// e.printStackTrace();
    //// }
    //
    // }
}
