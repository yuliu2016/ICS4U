package ics4u.io;

import java.text.*;

@SuppressWarnings("ALL")
public class Out
{
  public static void println ()
  {
    System.out.println();
  }

  public static void print (double n, int fieldWidth, int decimalPlaces)
  {
    String format = "0.";
    for (int i = 0; i < decimalPlaces; i++)
      format = format + "0";
    NumberFormat form = new DecimalFormat(format);
    String s = form.format(n);
    int stop = fieldWidth - s.length();
    for (int i = 0; i < stop; i++)
      s = " " + s;
    System.out.print(s);
  }

  public static void println (double n, int fieldWidth, int decimalPlaces)
  {
    print(n, fieldWidth, decimalPlaces);
    System.out.println();
  }

  public static void printCurrency (double n, int fieldWidth, int decimalPlaces)
  {
    String format = "$0.";
    for (int i = 0; i < decimalPlaces; i++)
      format = format + "0";
    NumberFormat form = new DecimalFormat(format);
    String s = form.format(n);
    int stop = fieldWidth - s.length();
    for (int i = 0; i < stop; i++)
      s = " " + s;
    System.out.print(s);
  }

  public static void printlnCurrency (double n, int fieldWidth, int decimalPlaces)
  {
    printCurrency(n, fieldWidth, decimalPlaces);
    System.out.println();
  }

  public static void print (double n)
  {
    System.out.print(n);
  }

  public static void println (double n)
  {
    print(n);
    System.out.println();
  }

  public static void print (long n, int fieldWidth)
  {
    String s = String.valueOf(n);
    int stop = fieldWidth - s.length();
    for (int i = 0; i < stop; i++)
      s = " " + s;
    System.out.print(s);
  }

  public static void println (long n, int fieldWidth)
  {
    print(n, fieldWidth);
    System.out.println();
  }

  public static void print (long n)
  {
    System.out.print(n);
  }
	
  public static void println (long n)
  {
    print(n);
    System.out.println();
  }
	
  public static void print (String s, int fieldWidth)
  {
    int stop = fieldWidth - s.length();
    if (s.length() > fieldWidth)
      s = s.substring(0, fieldWidth);
    else
      for (int i = 0; i < stop; i++)
        s = " " + s;
    System.out.print(s);
  }
	
  public static void println (String s, int fieldWidth)
  {
    print(s, fieldWidth);
    System.out.println();
  }
	
  public static void print (String s)
  {
    System.out.print(s);
  }

  public static void println (String s)
  {
    System.out.println(s);
  }

  public static void print (char c, int fieldWidth)
  {
    String s = String.valueOf(c); 
    int stop = fieldWidth - 1;
    for (int i = 0; i < stop; i++)
      s = " " + s;
    System.out.print(s);
  }
	
  public static void println (char c, int fieldWidth)
  {
    print(c, fieldWidth);
    System.out.println();
  }
	
  public static void print (char c)
  {
    System.out.print(c);
  }

  public static void println (char c)
  {
    System.out.println(c);
  }
}
