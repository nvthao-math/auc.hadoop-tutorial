/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thaonv
 */
public class RegexMatches {

    public static void main(String args[]) {
        // String to be scanned to find the pattern.
        String line = "[12, 23, 41, 22]";
        String pattern = "(\\d+\\*?)";  // (?<id>\\d+)

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        while (m.find()) {
            System.out.println(m);
            System.out.println("Found value: " + m.group());
        }
    }

}
