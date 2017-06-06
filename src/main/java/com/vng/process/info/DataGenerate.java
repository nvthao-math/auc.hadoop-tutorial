/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.process.info;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author thaonv
 */
public class DataGenerate {

    private static final String PATH_DATA = "/home/cpu10869-local/sandbox/process-monitor/data/input.txt";

    public static void main(String[] args) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            int count = 0;
            fw = new FileWriter(PATH_DATA);
            bw = new BufferedWriter(fw);
            while (count < 1000000) {
                bw.write(Integer.toString(count));
                bw.write("\n");
                count++;
            }
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

}
