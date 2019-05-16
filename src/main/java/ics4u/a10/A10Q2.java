package ics4u.a10;

import java.io.*;

/**
 * Assignment 10
 *
 * Question 2
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A10Q2 {

    public static void main(String[] args) {
        File file = new File("name_address.txt");
        System.out.println("File path: " + file.getAbsolutePath());

        String[] headers = new String[] {"Name", "Address", "City", "PST Code", "Phone No."};

        for (int i = 0; i < headers.length; i++) {
            _Out.print(headers[i], 15);
        }

        System.out.println();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            // skip over the header line
            br.readLine();
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++){
                    _Out.print(data[i], 15);
                }
                System.out.println();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class _Out {
        public static void print(String str, int fieldWidth) {
            StringBuffer s = new StringBuffer(str);
            int stop = fieldWidth - s.length();
            for (int i = 0; i < stop; i++) s.append(" ");
            System.out.print(s);
        }
    }
}
