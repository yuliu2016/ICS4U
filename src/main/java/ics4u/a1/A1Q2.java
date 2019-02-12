package ics4u.a1;

import java.util.Scanner;

/**
 * Assignment 1
 *
 * Problem 2 – Braille
 *
 * - Convert groups of 3 lines of braille patterns to single-line text
 *
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A1Q2 {
    public static void main(String[] args) {
        // create scanner for user input
        var scanner = new Scanner(System.in);
        // create an array of binary numbers representing braille patterns
        var patterns = new int[]{
                0b100000, // A
                0b101000, // B
                0b110000, // C
                0b110100, // D
                0b100100, // E
                0b111000, // F
                0b111100, // G
                0b101100, // H
                0b011000, // I
                0b011100, // J
                0b100010, // K
                0b101010, // L
                0b110010, // M
                0b110110, // N
                0b100110, // O
                0b101010, // P
                0b111110, // Q
                0b101110, // R
                0b011010, // S
                0b011110, // T
                0b100011, // U
                0b101011, // V
                0b011101, // W
                0b110011, // X
                0b110111, // Y
                0b100111  // Z
        };
        // get 15 lines of input before any calculations
        var lines = new String[15];
        for (var i = 0; i < 15; i++){
            lines[i] = scanner.nextLine();
        }
        // create a builder of the result
        var builder = new StringBuilder();
        // go through each line
        for (var i = 0; i < 5; i++) {
            // calculate the top-left vertical position
            var v = i * 3;
            // find number of characters
            var len = lines[v].length() / 2;
            // go through each character in the line
            for (var j = 0; j < len; j++) {
                // calculate the top-left horizontal position
                var h = j * 2;
                // convert characters to 1s and 0s and shift them by their bit number
                // then adding the result up using the bitwise OR operation
                var code = xoToInt(lines[v].charAt(h)) << 5
                        | xoToInt(lines[v].charAt(h + 1)) << 4
                        | xoToInt(lines[v + 1].charAt(h)) << 3
                        | xoToInt(lines[v + 1].charAt(h + 1)) << 2
                        | xoToInt(lines[v + 2].charAt(h)) << 1
                        | xoToInt(lines[v + 2].charAt(h + 1));
                // searches the result code in the patterns array
                for (var k = 0; k < patterns.length; k++){
                    if(patterns[k] == code){
                        // convert index to character (`a` starts at 97 in ASCII)
                        // and add to the builder
                        builder.append((char) (k + 97));
                    }
                }
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    /**
     * Converts the characters `x` and `o` to an integer representation
     */
    private static int xoToInt(char xo) {
        if (xo == 'x') return 1;
        if (xo == 'o') return 0;
        throw new IllegalStateException();
    }
}
