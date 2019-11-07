package com.abc.test;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * The  class for creating number to words
 * @author suraj.jain
 * @
 * 
 */

public class NumberToWords {

	private static final String[] smallValues = {
			"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

	private static final String[] tensMultipliers = {
			"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

	private static final String[] bigValues = {
			"thousand", "million", "billion"};

	/**
	 * The Method having actual business logic
	 * @param int value
	 * @return the word format of int value as String 
	 */
	public static String convertNumberToWords (int n) {
		//checking for value less than 0 and calling the respective function(with minus sign)
		if (n < 0) {
			return "minus " + convertNumberToWords(-n); 
		}
		//checking for value less than or equal to 999 and calling the respective function
		if (n <= 999) {
			return convert999(n); 
		}
		String s = null;
		int t = 0;
		while (n > 0) {
			if (n % 1000 != 0) {
				String str2 = convert999(n % 1000);
				if (t > 0) {
					str2 = str2 + " " + bigValues[t-1]; 
				}
				if (s == null) {
					s = str2; 
				}
				else {
					s = str2 + ", " + s;
				}
			}
			n /= 1000;
			t++; 
		}
		return s; 
	}


	/**
	 * The method logic for value 0-999
	 * @param int value
	 * @return the word format of int value as String 
	 */
	private static String convert999 (int number) {
		String str1 = smallValues[number / 100] + " hundred";
		String str2 = convert99(number % 100);
		if (number <= 99) {
			return str2;
		}
		else if (number % 100 == 0) {
			return str1; 
		}
		else {
			return str1 + " " + str2;
		}
	}

	/**
	 * The logic for values 0-99
	 * @param int value
	 * @return the word format of int value as String 
	 */
	private static String convert99 (int number) {
		if (number < 20) {
			return smallValues[number]; 
		}
		String s = tensMultipliers[number / 10 - 2];
		if (number % 10 == 0) {
			return s; 
		}
		return s + "-" + smallValues[number % 10]; 
	}

	/**
	 * main method for using our logic. 
	 */
	public static void main(final String[] args) throws WrongFormat {

		int n=0;
		//Creating reference for Scanner class to get the input from input Stream
		Scanner s=new Scanner(System.in);
		System.out.println("Enter a number to convert into word format");
		String str=s.next();

		try 
		{ 
			// checking valid integer using parseInt() method 
			n=Integer.parseInt(str);

		}  
		catch (Exception e)  
		{ 
			throw new WrongFormat(str +" is a not valid integer number"); 
		}

		//getting integer value from String formatted input
		n=Integer.parseInt(str);

		//Displaying the output
		//if value is 0, directly print Zero else call our logic
		if (n == 0) {
			System.out.print("Number " + n + " in words:  'Zero' ");
		}
		else{
			System.out.println("Number "  + NumberFormat.getInstance().format(n) +" in words "  + " = '" + convertNumberToWords(n) + " '");
		}
	}
}
/**
 * The Exception class for throwing message for user , if input provided is invalid
 */
class WrongFormat extends Exception 
{ 

	private static final long serialVersionUID = 1L;

	public WrongFormat(String s) 
	{ 
		// Call constructor of parent Exception 
		super(s); 
	} 
}
