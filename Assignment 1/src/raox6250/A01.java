package raox6250; // Replace with your login name 

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Nausher Rao - 190906250
 *
 */
public class A01 {

	// Constants
	public static final String VOWELS = "aeiouAEIOU";
	public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int ALPHA_LENGTH = ALPHA.length();

	/**
	 * Looks for a integer in an array of integers.
	 *
	 * @param values array of int
	 * @param target value to search for
	 * @return true if target in values, false otherwise
	 */
	public static boolean contains(int[] values, int target) {
		int i = 0;
		boolean in = false;
		while(i < values.length && !in) {
			in = values[i] == target;
			i += 1;
		
		}
		
		return in;

	}

	/**
	 * Determines if s is a palindrome. Ignores case, spaces, digits, and
	 * punctuation in the string parameter s.
	 *
	 * @param s a string
	 * @return true if s is a palindrome, false otherwise
	 */
	public static boolean isPalindrome(final String s) {
        int n = s.length();
        int i = 0;
        boolean palindrome = true;
        while(i < n/2 && palindrome) {
            palindrome = s.charAt(i) == s.charAt(n - i);
            i += 1;

        }

        return palindrome;

	}

	/**
	 * Determines if name is a valid Java variable name. Variables names must start
	 * with a letter or an underscore, but cannot be an underscore alone. The rest
	 * of the variable name may consist of letters, numbers and underscores.
	 *
	 * @param name a string to test as a Java variable name
	 * @return true if name is a valid Java variable name, false otherwise
	 */
	public static boolean isValid(final String name) {
        int i = 0;
        char c = name.charAt(0);
        
        boolean valid = (Character.isLowerCase(c) && Character.isAlphabetic(c)) || (name.length() > 1 && c == '_');
        while(i < name.length() && valid) {
            c = name.charAt(i);
            valid = Character.isDigit(c) || Character.isAlphabetic(c) || c == '_';

        }

        return valid;
        
	}

	/**
	 * Determines the smallest, largest, total, and average of the values in the 2D
	 * list a. You may assume there is at least one value in a and that a is a
	 * square matrix - i.e. the number of columns per row is the same. a must be
	 * unchanged.
	 *
	 * @param a - a 2D list of numbers (2D list of double)
	 *
	 * @return a list of four double values containing the smallest number in a,the
	 *         largest number in a, the total of all numbers in a, and the average
	 *         of all numbers in a, in that order.
	 */
	public static double[] matrixStats(double[][] a) {
        double smallest = a[0][0];
        double largest = a[0][0];
        double total = 0;

        for(double[] y : a) {
            for(double x : y) {
                if(smallest > x)
                    smallest = x;

                else if(largest < x)
                    largest = x;

                total += x;
            }
        }

        return new double[] {smallest, largest, total, total / a.length};

	}

	/**
	 * Sums and returns the total of a partial harmonic series. This series is the
	 * sum of all terms 1/i, where i ranges from 1 to n (inclusive).
	 *
	 * @param n an integer
	 * @return sum of partial harmonic series from 1 to n
	 */
	public static double sumPartialHarmonic(int n) {
        double sum = 0;
        for(int i = 1; i <= n; i++)
            sum += 1/i;
            
        return sum;

	}

	/**
	 * Determines if a string is a good serial number. Good serial numbers are of
	 * the form 'SN/nnnn-nnn', where 'n' is a digit.
	 *
	 * @param sn The serial number to test.
	 * @return true if the serial number is valid in form, false otherwise.
	 */
	public static boolean validSn(String sn) {
        return sn.matches("SN/\\d\\d\\d\\d-\\d\\d\\d");

	}

	/**
	 * Evaluates serial numbers from a file. Writes valid serial numbers to
	 * good_sns, and invalid serial numbers to bad_sns.
	 *
	 * @param fileIn  a file already open for reading
	 * @param goodSns a file already open for writing
	 * @param badSns  a file already open for writing
	 */
	public static void validSnFile(Scanner fileIn, PrintStream goodSns, PrintStream badSns) {
        while(fileIn.hasNextLine()) {
            String next = fileIn.nextLine();
            if(OldA01.validSn(next))
            	goodSns.println(next);
            
            else
            	badSns.println(next);
            
        }

        fileIn.close();
        goodSns.close();
        badSns.close();
        
	}

}