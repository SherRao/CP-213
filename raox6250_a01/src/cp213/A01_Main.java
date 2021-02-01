package cp213;

import java.util.Arrays;

/**
 * @author Your name and id here
 *
 */
public class A01_Main {
    // Constants
    public static final String SEP = "-".repeat(40);
    public static final String CIPHERTEXT = "AVIBROWNZCEFGHJKLMPQSTUXYD"; // for testing substitute method

    public static void main(String[] args) {
        int[] arrayA = new int[] {43, 34, 34, 23213, 42345, 324, -423432, -3, 234234, 432, 243, -423432};
        int a = 10;
        int b = 423;
        int c = 432;
        int d = -32130;
        int e = -1210;
        System.out.println("Contains");
        System.out.println("Array: " + Arrays.toString(arrayA));
        System.out.println("Trying to find " + a + ": " + A01.contains(arrayA, a));
        System.out.println("Trying to find " + b + ": " + A01.contains(arrayA, b));
        System.out.println("Trying to find " + c + ": " + A01.contains(arrayA, c));
        System.out.println("Trying to find " + d + ": " + A01.contains(arrayA, d));
        System.out.println("Trying to find " + e + ": " + A01.contains(arrayA, e));

        String f = "Hello";
        String g = "racecar";
        String h = "_like";
        System.out.println("\n");
        System.out.println("Palindrome");
        System.out.println(f + ": " + A01.isPalindrome(f));
        System.out.println(g + ": " + A01.isPalindrome(g));
        System.out.println(h + ": " + A01.isPalindrome(h));

        System.out.println("\n");
        System.out.println("Valid");
        System.out.println(f + ": " + A01.isValid(f));
        System.out.println(g + ": " + A01.isValid(g));
        System.out.println(h + ": " + A01.isValid(h));

        double[][] arrayB = new double[][] {{43.0, 34.0, 34.0}, {23213.0, 42345.0, 324.0}, {-423432.312, -3.123, 234234.32}};
        System.out.println("\n");
        System.out.println("Matrix Stats");
        System.out.println("Array: " + Arrays.toString(arrayB));
        System.out.println("Answers: " + Arrays.toString(A01.matrixStats(arrayB)));

        int i = 6;
        int j = 321;
        int k = 4;
        System.out.println("\n");
        System.out.println("Sum Harmonics");
        System.out.println(i + ": " + A01.sumPartialHarmonic(i));
        System.out.println(j + ": " + A01.sumPartialHarmonic(j));
        System.out.println(k + ": " + A01.sumPartialHarmonic(k));

        String l = "SN/5403-321";
        String m = "SN/503-32";
        String n = "FN/5403-321";
        System.out.println("\n");
        System.out.println("Valid SN");
        System.out.println(l + ": " + A01.validSn(l));
        System.out.println(m + ": " + A01.validSn(m));
        System.out.println(n + ": " + A01.validSn(n));

    }

}
