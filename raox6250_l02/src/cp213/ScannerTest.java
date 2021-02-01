package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerTest {
    
	public static void main(String args[]) {
		task1();
		task23();
	
	}
    
	
	public static void task1() {
        System.out.println("Enter a series of integers. Press 'q' to quit.");
        Scanner s = new Scanner(System.in);
        int result = 0;
        while (s.hasNext()) {
        	String token = s.nextLine();
        	if(!token.equals("q")) {
        		int number = Integer.parseInt(token);
        		if(number > 0)
        			result += number;
        		
        		else 
        			System.out.println("Invalid input! Must be a positive number!");
        	
        	} else
        		break;
        }
        
        s.close();
        System.out.println("The total is " + result);
		
	}
    
	public static void task23() {
    	File file = new File("src/cp213/ScannerTest.java");
		int tokenSum = 0;
    	try(Scanner source = new Scanner(file)){
			while(source.hasNext()) {
				System.out.println(source.next());
				tokenSum += 1;
				
			}
			
			System.out.println("Amount of tokens: " + tokenSum);
			
        } catch (FileNotFoundException e) { e.printStackTrace(); }
	}
	
}