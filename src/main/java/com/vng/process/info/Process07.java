/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.process.info;

import com.java.hadoop.TimeUtils;
import com.vng.process.execution.Executor;
import static com.vng.process.execution.Executor.timeToString;
import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thaonv
 */
public class Process07 extends Executor {

    private static final String LOG_BASE = "/home/cpu10869-local/workspace/data/speed-profiles/";
    private static final String LOG_DATA = "/home/cpu10869-local/sandbox/process-monitor/process_time.log";

    @Override
    public void execute() {
        try {
            long t1 = System.currentTimeMillis();
            dataProcessing();
            long duration = System.currentTimeMillis() - t1;
            String time = timeToString(new Date());
            Executor.writeData(time + "\t" + "PROCESS-0x" + "\t" + "time: " + Long.toString(duration) + " (ms)", LOG_DATA);
            System.out.println("Time: " + duration + " (ms)");
        } catch (Exception ex) {
            System.out.println("-" + ex);
        }
    }

    public static void dataProcessing() throws ParseException {
        // Submit the job, then poll for progress until the job is complete
        Calendar fromDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        fromDate.setTime(TimeUtils.toTime("2017-03-23-20"));
        endDate.setTime(TimeUtils.toTime("2017-03-23-23"));
        String time = null;
        while (!fromDate.after(endDate)) {
            time = TimeUtils.toString(fromDate.getTime(), TimeUtils.yyyy_MM_dd_HH);
            // get path
            String source = LOG_BASE + TimeUtils.asPath(time);
            File folder = new File(source);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    List<String> logs = Executor.readData(file.getAbsolutePath());
                    for (String line : logs) {
                        SpeedModel sp = parseLog(line);
//                        System.out.println(sp.getArcId());
                    }
                }
            }
            // increase time
            fromDate.add(Calendar.HOUR_OF_DAY, 1);
        }
    }

    public static SpeedModel parseLog(String record) {
        SpeedModel model = new SpeedModel();
        try {
            String[] parts = record.split(",");
            double speed = Double.parseDouble(parts[SpeedSchema.SPEED]);
            model.setId(parts[SpeedSchema.ID]);
            model.setArcId(parts[SpeedSchema.ARC_ID]);
            model.setSubClass(parts[SpeedSchema.SUB_CLASS]);
            model.setLength(parts[SpeedSchema.LENGTH]);
            model.setSpeedMax(parts[SpeedSchema.SPEED_MAX]);
            model.setSpeed(speed);
            String time = new StringBuilder().append(parts[SpeedSchema.YEAR]).append("-")
                    .append(Integer.parseInt(parts[SpeedSchema.MONTH])).append("-")
                    .append(Integer.parseInt(parts[SpeedSchema.DAY])).append("-")
                    .append(Integer.parseInt(parts[SpeedSchema.HOUR]))
                    .toString();
            model.setTime(time);
            model.setDayOfWeek(parts[SpeedSchema.DAY_OF_WEEK]);
            model.setDayNameOfWeek(parts[SpeedSchema.DAY_NAME_OF_WEEK]);
        } catch (Exception ex) {
            System.out.println("-" + ex);
        }
        return model;
    }

}
