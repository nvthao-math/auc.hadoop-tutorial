/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.hadoop;

import com.java.hadoop.HadoopClient;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 *
 * @author thaonv
 */
public class Test {

    public static void main(String[] args) throws IOException {
        //Get configuration of Hadoop system
        Configuration hadoopConfig = new Configuration();
        hadoopConfig.set("fs.defaultFS", "hdfs://localhost:9000/");
        hadoopConfig.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        hadoopConfig.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        System.setProperty("HADOOP_USER_NAME", "hduser");
        System.out.println("Connecting to -- " + hadoopConfig.get("fs.defaultFS"));
        String dst = "/app4001/speed-profiles/raw-data";
        //Destination file in HDFS
        FileSystem fs = FileSystem.get(hadoopConfig); // URI.create(dst), 
//        HadoopUtils.deleteFile(dst);
        OutputStream out = fs.create(new Path(dst));
    }

}
