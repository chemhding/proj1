package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * This class is responsible for writing to files
 */
public class Writer {
    protected String destinationPath;

    protected BufferedWriter bw;

    public Writer(String destinationPath) {
        try {
            bw = new BufferedWriter(new FileWriter(destinationPath, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addString(String toBeWritten) {
        try {
            bw.write(toBeWritten);
            bw.newLine();
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
