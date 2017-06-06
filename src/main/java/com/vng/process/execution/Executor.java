/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.process.execution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thaonv
 */
public abstract class Executor {

    public abstract void execute();

    public static List<String> readData(String path) {
        List<String> result = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
                result.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void writeData(String data, String path) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(path, true);
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static final String RESULT = "/home/cpu10869-local/sandbox/process-monitor/data/result.txt";

    public static void main(String[] args) {
        timeToString(new Date());
    }

    public static String timeToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String str = sdf.format(date);
        return str;
    }

}
