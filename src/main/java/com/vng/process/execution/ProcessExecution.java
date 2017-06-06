/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.process.execution;

/**
 *
 * @author thaonv
 */
public class ProcessExecution {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        args = new String[]{"com.vng.process.info.Process07"};
        Executor executor = (Executor) Class.forName(args[0]).newInstance();
        executor.execute();
    }

}
