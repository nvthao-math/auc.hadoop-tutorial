/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.process.info;

import com.bash.BashExecutor;
import com.bash.ProcessInfo;
import com.vng.process.execution.Executor;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thaonv
 */
public class ProcessMonitor {

    private static final String TAG = ProcessMonitor.class.getSimpleName();
    private static String TAB_DELIMITER = "\t";
    private static final String DATA_COLLECT = "/home/cpu10869-local/sandbox/process-monitor/collect_data.log";
    private static final String PID = "/home/cpu10869-local/sandbox/process-monitor/pid";
    private static final String PREFIX = "ps -p ";
    private static final String SUFFIX = " -o %cpu,%mem,time,cmd | tail -1 | awk '{print $1,\"\\t\",$2,\"\\t\",$3,\"\\t\",$4}'";

    public static void main(String[] args) {
        try {
            while (true) {
                Map<String, String> pidMap = getPid(PID);
                for (String id : pidMap.keySet()) {
                    String pCommand = new StringBuilder()
                            .append(PREFIX)
                            .append(id)
                            .append(SUFFIX)
                            .toString();
                    //
                    String pInfo = (new BashExecutor<String>(pCommand) {
                        @Override
                        protected String build() {
                            String result = null;
                            try {
                                String[] info = this.resultSet.toString().split(TAB_DELIMITER, -1);
                                String time = Executor.timeToString(new Date());
                                String cpu = info[0].trim();
                                String mem = info[1].trim();
                                result = new StringBuilder()
                                        .append(id)
                                        .append(TAB_DELIMITER)
                                        .append(pidMap.get(id))
                                        .append(TAB_DELIMITER)
                                        .append(time)
                                        .append(TAB_DELIMITER)
                                        .append(cpu)
                                        .append(TAB_DELIMITER)
                                        .append(mem)
                                        .toString();
                                // save data
                                System.out.println(result);
                                Executor.writeData(result, DATA_COLLECT);
                            } catch (Exception ex) {
                                System.out.println(TAG + TAB_DELIMITER + ex);
                            }
                            return result;
                        }
                    }).run();
                }
                Thread.sleep(1l);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static Map<String, String> getPid(String path) {
        Map<String, String> result = new HashMap<>();
        List<String> pidInfo = Executor.readData(path);
        for (String info : pidInfo) {
            String[] parts = info.split(TAB_DELIMITER);
            String id = parts[0];
            String name = parts[1];
            result.put(id, name);
        }
        return result;
    }

}
