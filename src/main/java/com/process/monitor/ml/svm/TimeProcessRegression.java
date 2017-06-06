/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.process.monitor.ml.svm;

import com.bash.BashExecutor;
import com.bash.SVMBash;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author thaonv
 */
public class TimeProcessRegression {

    private static final String BLANK = " ";
    private static final String FILENAME = "/home/cpu10869-local/sandbox/process-monitor/dataset";

    public static void main(String[] args) {
        trainSVMModelRegression();
        //
        predictTime();
    }

    public static void predictTime() {
        File folder = new File(FILENAME);
        for (final File fileEntry : folder.listFiles()) {
            String fileName = fileEntry.getAbsolutePath();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = null;
                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
                    String[] fields = line.split("\t");
                    int id = Integer.parseInt(fields[2]);
                    double cpu = Double.parseDouble(fields[4]);
                    double mem = Double.parseDouble(fields[5]);
                    double actualTime = Double.parseDouble(fields[6]);
                    double predTime = realTimeSVMModelRegression(id, cpu, mem);
                    System.out.println(predTime + ", " + actualTime);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void trainSVMModelRegression() {
        (new BashExecutor<Boolean>(SVMBash.TRAINING) {
            @Override
            protected Boolean build() {
                System.out.println(this.resultSet.toString());
                return true;
            }
        }).run();
    }

    public static double realTimeSVMModelRegression(int id, double cpu, double mem) {
        String command = new StringBuilder()
                .append(SVMBash.REAL_TIME)
                .append(BLANK)
                .append(id)
                .append(BLANK)
                .append(cpu)
                .append(BLANK)
                .append(mem)
                .toString();
        double time = (new BashExecutor<Double>(command) {
            @Override
            protected Double build() {
                Double time = null;
                try {
                    String[] parts = this.resultSet.toString().split("\t", -1);
                    time = Double.parseDouble(parts[1]);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                return time;
            }
        }).run();
        return time;
    }

}
