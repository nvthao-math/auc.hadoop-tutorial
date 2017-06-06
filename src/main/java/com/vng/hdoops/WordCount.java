/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.hdoops;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 *
 * @author thaonv
 */
// command to run on hadoop
// hadoop jar /home/cpu10869-local/NetBeansProjects/hadoop-sandbox/hdoops-1.0-SNAPSHOT.jar /user/hduser/input /user/hduser/output2
// command run java ||  java -jar target/hdoops-1.0-SNAPSHOT.jar
public class WordCount {

    public static void main(String[] args) throws Exception {
//        args = new String[]{"/home/cpu10869-local/workspace/data/speed-profiles/speed.log", "/home/cpu10869-local/workspace/data/speed-profiles/hadoop_mapred.txt"};
        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
        System.out.println("input: " + files[0]);
        System.out.println("output: " + files[1]);
        Path input = new Path(files[0]);
        Path output = new Path(files[1]);
        Job j = new Job(c, "wordcount");
        j.setJarByClass(WordCount.class);
        j.setMapperClass(MapForWordCount.class);
        j.setReducerClass(ReduceForWordCount.class);
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);
        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class MapForWordCount extends Mapper<LongWritable, Text, Text, IntWritable> {

        /**
         *
         * @param key
         * @param value
         * @param con
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split(",");
            for (String word : words) {
                System.out.println(word);
                Text outputKey = new Text(word.toUpperCase().trim());
                IntWritable outputValue = new IntWritable(1);
                con.write(outputKey, outputValue);
            }
        }
    }

    public static class ReduceForWordCount extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text word, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            con.write(word, new IntWritable(sum));
        }
    }

}
