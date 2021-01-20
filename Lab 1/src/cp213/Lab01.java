package cp213;

import java.util.Scanner;

public class Lab01 {

	/**
	 * Returns the greatest command divisor of two integers.
	 * 
	 * @param a The first number.
	 * @param b The second number.
	 * @return gcd The greatest command factor of a and b.
	 * @author Nausher Rao
	 * 
	 */
	public static int gcd(int a, int b) {
		int gcd = 1;
		int i = 1;
		while(i > 0 && i <= a && i <= b) {
			if(a%i == 0 && b%i == 0) {
				gcd = i;
				i = 0;
		
			} else 
				continue;
			
		}
		
		return gcd;

	}

	/**
	 * The main function entered by the JVM
	 * 
	 * @param vargs The virtual arguments.
	 * @author Nausher Rao
	 *
	 */
	public static void main(String... vargs) {
		Scanner keyboard = new Scanner(System.in);
		int a = 1;
		int b = 1;
		int c = 1;

		while(a != 0 && b != 0) {
			// Read an integer from the keyboard.
			System.out.print("Enter the first number (0 to quit): ");
			a = keyboard.nextInt();

			// Read another integer from the keyboard.
			System.out.print("Enter a second number (0 to quit): ");
			b = keyboard.nextInt();
		
			// Gets the GCD of a and b.
			c = Lab01.gcd(a, b);
			System.out.println( String.format("GCD(%d, %d) = %d\n", a, b, c) );
			
		}
		
		keyboard.close();

	}

}