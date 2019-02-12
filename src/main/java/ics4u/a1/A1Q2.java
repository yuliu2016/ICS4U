package ics4u.a1;

import java.util.Scanner;

/**
 * Assignment 1
 * <p>
 * Problem 2 – Braille
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A1Q2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
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
        var lines = new String[15];
        for (var i = 0; i < 15; i++){
            lines[i] = scanner.nextLine();
        }
        var builder = new StringBuilder();
        for (var i = 0; i < 5; i++) {
            var v = i * 3;
            var len = lines[v].length() / 2;
            for (var j = 0; j < len; j++) {
                var h = j * 2;
                var code = xoToInt(lines[v].charAt(h)) << 5
                        | xoToInt(lines[v].charAt(h + 1)) << 4
                        | xoToInt(lines[v + 1].charAt(h)) << 3
                        | xoToInt(lines[v + 1].charAt(h + 1)) << 2
                        | xoToInt(lines[v + 2].charAt(h)) << 1
                        | xoToInt(lines[v + 2].charAt(h + 1));
                for (var k = 0; k < patterns.length; k++){
                    if(patterns[k] == code){
                        builder.append((char) (k + 97));
                    }
                }
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    private static int xoToInt(char xo) {
        if (xo == 'x') return 1;
        if (xo == 'o') return 0;
        throw new IllegalStateException();
    }
}
