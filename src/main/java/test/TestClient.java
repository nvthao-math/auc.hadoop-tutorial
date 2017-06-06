/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import com.java.hadoop.HadoopClient;
import java.io.IOException;
import org.apache.hadoop.fs.FileSystem;

/**
 *
 * @author thaonv
 */
public class TestClient extends HadoopClient {
    
    private static final Gson GSON = new Gson();
    
    public static void main(String[] args) throws IOException {
        int i = 0;
        while (i < 100) {
            try {
                Thread.sleep(1000l);
            } catch (Exception ignore) {                
            }
            _fs = FileSystem.get(HADOOP_CONFIG);
            i++;
            System.out.println(GSON.toJson(_fs.getStatus()));
            _fs.close();
        }
    }
    
}
