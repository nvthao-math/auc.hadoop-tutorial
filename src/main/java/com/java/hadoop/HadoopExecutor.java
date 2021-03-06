/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.hadoop;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author thaonv
 */
public class HadoopExecutor {

    private static final String LOG_BASE = "/home/cpu10869-local/workspace/data/hadoop/";
    private static final String HADOOP_BASE = "/app/speed-profiles/raw-data/";

    public static void main(String[] args) {
        try {
            long t1 = System.currentTimeMillis();
            Configuration configuration = HadoopClient.HADOOP_CONFIG;
            Job job = Job.getInstance(configuration);
            job.setJobName("import-23");
            Path input = new Path("/input");
            FileInputFormat.addInputPath(job, input);
            Path output = new Path("/tmp");
            FileOutputFormat.setOutputPath(job, output);
            job.setJarByClass(HadoopExecutor.class);
//            job.submit();
            //
            // Submit the job, then poll for progress until the job is complete
            Calendar fromDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            fromDate.setTime(TimeUtils.toTime("2017-03-23-00"));
            endDate.setTime(TimeUtils.toTime("2017-03-23-23"));
            String time = null;
            while (!fromDate.after(endDate)) {
                time = TimeUtils.toString(fromDate.getTime(), TimeUtils.yyyy_MM_dd_HH);
                System.out.println("Time: " + time);
//                parseSpeedLog(time);
                // get path
                String source = LOG_BASE + TimeUtils.asPath(time);
                String dest = HADOOP_BASE + TimeUtils.asPath(time);
//                System.out.println(source);
//                System.out.println(dest);
                File folder = new File(source);
                File[] listOfFiles = folder.listFiles();
                System.out.println("list of file: " + listOfFiles);
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        System.out.println(file);
                        HadoopClient.copyFromLocal(file.getAbsolutePath(), dest);
                    }
                }
                // increase time
                fromDate.add(Calendar.HOUR_OF_DAY, 1);
            }
            long duration = System.currentTimeMillis() - t1;
            System.out.println("Time consuming: " + duration + " (ms)");
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (Exception ex) {
            StringWriter error = new StringWriter();
            ex.printStackTrace(new PrintWriter(error));
            System.out.println("HadoopExecutor, " + error.toString());
        }
    }

}
