/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 *
 * @author thaonv
 */
public class HDFSWordCounter {

    //change this to string arg in main
    public static final String INPUT_FILE = "/java/handle/test.txt";
    public static final String MESSAGE = "Count the amount of words in this sentence!\n";

    /**
     * @param args
     */
    // hadoop jar /home/cpu10869-local/NetBeansProjects/hadoop-sandbox/hdoops-1.0-SNAPSHOT.jar
    public static void main(String[] args) throws IOException {
        // Create a default hadoop configuration
        Configuration config = new Configuration();
        // Parse created config to the HDFS 
        FileSystem fs = FileSystem.get(config);
        // Specifies a new file in HDFS.
        Path filenamePath = new Path(INPUT_FILE);
        try {
            // if the file already exists delete it.
            if (fs.exists(filenamePath)) {
                //remove the file
                fs.delete(filenamePath, true);
                System.out.println("============= file exist - nvthao ============");
            }
            // 
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                sb.append("java-").append(i).append("\n");
            }
            //FSOutputStream to write the inputmsg into the HDFS file 
            FSDataOutputStream fout = fs.create(filenamePath);
            fout.writeUTF(MESSAGE);
            fout.writeUTF(sb.toString());
            fout.close();
            //FSInputStream to read out of the filenamePath file
            FSDataInputStream fin = fs.open(filenamePath);
            for (int i = 1; i < 10; i++) {
                String msgIn = fin.readUTF();
                //Print to screen
                System.out.println(msgIn);
            }
            fout.close();
        } catch (IOException ioe) {
            System.err.println("IOException during operation " + ioe.toString());
            System.exit(1);
        }
    }

}
