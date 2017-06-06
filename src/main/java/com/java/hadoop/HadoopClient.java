/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 *
 * @author thaonv
 */
public class HadoopClient {

    public static Configuration HADOOP_CONFIG;
    public static FileSystem _fs;

    static {
        _initializeConfig();
    }

    public static void _initializeConfig() {
        try {
            if (HADOOP_CONFIG == null) {
                HADOOP_CONFIG = new Configuration();
                HADOOP_CONFIG.set("fs.defaultFS", "hdfs://localhost:9000/");
                HADOOP_CONFIG.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
                HADOOP_CONFIG.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
                System.setProperty("HADOOP_USER_NAME", "hduser");
                _fs = FileSystem.get(HADOOP_CONFIG);
                // yarn configuration
                // HADOOP_CONFIG.set("mapreduce.framework.name", "yarn");
                // HADOOP_CONFIG.set("yarn.resourcemanager.address", "master:8032");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void copyFromLocal(String source, String dest) {
        try {
            FileSystem fs = FileSystem.get(HADOOP_CONFIG);
            Path srcPath = new Path(source);
            Path dstPath = new Path(dest);
            // Check if the file already exists
            if (!(fs.exists(dstPath))) {
                mkdir(dest);
                System.out.println("Create dir in hadoop: " + dest);
            }
            // Get the filename out of the file path
            String filename = source.substring(source.lastIndexOf('/') + 1, source.length());
            try {
                fs.copyFromLocalFile(srcPath, dstPath);
                System.out.println("File " + filename + " copied to " + dest);
            } catch (Exception e) {
                System.err.println("Exception caught! :" + e);
            } finally {
                fs.close();
            }
        } catch (Exception ex) {
            System.out.println("HadoopUtils, " + ex);
        }
    }

    public static void deleteFile(String file) {
        try {
            FileSystem fs = FileSystem.get(HADOOP_CONFIG);
            Path path = new Path(file);
            if (!fs.exists(path)) {
                System.out.println("File " + file + " does not exists");
                return;
            }
            fs.delete(new Path(file), true);
            fs.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void mkdir(String dir) {
        try {
            FileSystem fs = FileSystem.get(HADOOP_CONFIG);
            Path path = new Path(dir);
            if (fs.exists(path)) {
                System.out.println("Dir " + dir + " already exists!");
                return;
            }
            fs.mkdirs(path);
            fs.close();
        } catch (Exception ex) {
            System.out.println("HadoopUtils, " + ex);
        }
    }

}
