package ics4u.a1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Assignment 1
 *
 * Problem 1 – Word Frame
 * creating a frame around the stars with words
 *
 * ICS4U class 2019
 * @author Yu Liu
 */

public class A1Q1 {
    public static void main(String[] args) {
        // create scanner for user input
        var scanner = new Scanner(System.in);
        // do 5 repeats of I/O
        for (var i = 0; i < 5; i++) {
            // get word
            var word = scanner.nextLine();
            // get array size
            var len = word.length() + 2;
            // create empty array
            var charArray = new char[len][len];
            // fill array
            for (var row : charArray) Arrays.fill(row, '*');
            // index through word and add to four sides of the array using length
            for (var j = 1; j < len - 1; j++){
                var ch = word.charAt(j - 1);
                charArray[0][j] = ch;
                charArray[len - 1][len - j - 1] = ch;
                charArray[j][0] = ch;
                charArray[len - j - 1][len - 1] = ch;
            }
            // print out array with string builder
            var builder = new StringBuilder();
            for (var row: charArray){
                for (var ch: row){
                    builder.append(ch);
                    builder.append(' ');
                }
                builder.append('\n');
            }
            System.out.println(builder.toString());
        }
    }
}
