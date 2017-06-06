/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.process.info;

import com.vng.process.execution.Executor;
import static com.vng.process.execution.Executor.readData;
import static com.vng.process.execution.Executor.timeToString;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thaonv
 */
public class Process03 extends Executor {

    private static final String PATH_DATA = "/home/cpu10869-local/sandbox/process-monitor/data/input.txt";
    private static final String RESULT = "/home/cpu10869-local/sandbox/process-monitor/data/result.txt";
    private static final String LOG_DATA = "/home/cpu10869-local/sandbox/process-monitor/process_time.log";

    @Override
    public void execute() {
        try {
            long t1 = System.currentTimeMillis();
            List<String> data = readData(PATH_DATA);
            for (String val : data) {
                if (Integer.parseInt(val) < 400000) {
                    long x = getFactorial(Integer.parseInt(val));
                    System.out.println(Integer.parseInt(val));
                    Executor.writeData(Long.toString(x), RESULT);
                }
            }
            long duration = System.currentTimeMillis() - t1;
            String time = timeToString(new Date());
            Executor.writeData(time + "\t" + "PROCESS-01" + "\t" + "time: " + Long.toString(duration) + " (ms)", LOG_DATA);
            System.out.println("Time: " + duration + " (ms)");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private long getFactorial(Integer x) {
        long result = 1l;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }

}
