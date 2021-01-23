package raox6250;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Your name and id here
 *
 */
public class OldA01 {

    // Constants
    public static final String VOWELS = "aeiouAEIOU";
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();


    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the original plaintext string
     */
    public static String shift(String s, int n) {
        for(int i = 0; i < n; i++) 
            s = s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
        
        return s;

    }


    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the plaintext string
     */
    public static String substitute(String s, String ciphertext) {
        return ciphertext.substring(0, s.length());

    }


    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(String str) {
        return !str.matches(".*\\d+.*");

    }


    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(double target, double v1, double v2) {
    	return (Math.abs(target - v1) < Math.abs(target - v2)) ? v1 : v2;

    }


    /**
     * Looks for a integer in an array of integers.
     *
     * @param values array of int
     * @param target value to search for
     * @return true if target in values, false otherwise
     */
    public static boolean contains(int[] values, int target) {
        int i = 0;
        boolean exists = false;
        while(i < values.length && !exists) {
            exists = values[i] == target;
            i += 1;

        }

        return exists;

    }


    /**
     * Generates a sorted list of unique lottery numbers.
     *
     * @param n    number of lottery numbers to generate
     * @param low  low value of the lottery number range
     * @param high high value of the lottery number range
     * @return a list of unique random lottery numbers
     */
    public static int[] getLottoNumbers(int n, int low, int high) {
        int[] numbers = new int[n];
        Random rand = new Random();

        for(int i = 0; i < n; i++)
            numbers[i] = rand.nextInt(high - low) + high;
        
        return numbers;

    }


    /**
     * Determines whether or not a year is a leap year.
     *
     * @param year The year to test (int greater than 0)
     * @return true if year is a leap year, false otherwise.
     */
    public static boolean isLeapYear(final int year) {
        return (year % 4 == 0 || year % 400 == 0) && year % 100 != 0;

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
     * Determines if n is a prime number.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(int n) {
        int i = 2;
        boolean prime = true;
        while(i < n && prime) {
            prime = n % i != 0; 
            i += 1;

        }

        return prime;

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
     * Counts and returns how may positives, negatives, and zeroes there are in an
     * array of integers.
     *
     * @param numbers array of int
     * @return number of negative values, number of zero values, number of positive
     *         values
     */
    public static int[] numCategories(int[] numbers) {
        int neg = 0;
        int zer = 0;
        int pos = 0;
        for(int n : numbers)
            if(n > 0)
                pos += 1;

            else if(n < 0)
                neg += 1;

            else
                zer =+ 1;

        return new int[] {neg, zer, pos};

    }


    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
        if(VOWELS.indexOf(word.charAt(0)) != -1)
            word = word + "way";        
        
        return null;
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
