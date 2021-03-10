package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Nausher Rao 190906250
 * @version 20201-01-29
 */
public class A02_Main {

	public static void main(String[] args) throws FileNotFoundException {
		Food a = new Food("Food A", 2, false, 1200);
		Food b = new Food("Food B", 7, true, 500);
		Food c = new Food("Food B", 7, true, 760);
		Food d = new Food("Food D", 9, false, 320);
		Food e = new Food("Food E", 8, false, 610);
		Food f = new Food("Food F", 2, false, 530);
		Food g = new Food("Food G", 1, false, 5320);
		Food h = new Food("Food H", 7, true, 150);
		ArrayList<Food> foods = new ArrayList<>(Arrays.asList(a, b, c, d, e, f, g, h));
		File file = new File("foods.txt");

		System.out.println("Testing Food.originsMenu()");
		System.out.println(Food.originsMenu());
		System.out.println("-----------------------------------\n");

		System.out.println("Testing Food.compareTo()");
		System.out.println("a.compareTo(b): " + a.compareTo(b));
		System.out.println("b.compareTo(a): " + b.compareTo(a));
		System.out.println("b.compareTo(c): " + b.compareTo(c));
		System.out.println("c.compareTo(b): " + c.compareTo(b));
		System.out.println("-----------------------------------\n");

		System.out.println("Testing Food.toString()");
		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println(c.toString());
		System.out.println(d.toString());
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.averageCalories(foods)");
		System.out.println(FoodUtilities.averageCalories(foods));
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.averageCaloriesByOrigin(foods, 2)");
		System.out.println(FoodUtilities.averageCaloriesByOrigin(foods, 7));
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.getByOrigin(food, 2)");
		for (Food food : FoodUtilities.getByOrigin(foods, 2))
			System.out.println(food);
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.getFood()");
		Scanner keyboard = new Scanner(System.in);
		System.out.println(FoodUtilities.getFood(keyboard));
		keyboard.close();
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.getVegetarian(foods)");
		for (Food food : FoodUtilities.getVegetarian(foods))
			System.out.println(food);
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.foodSearch(foods, 7, 600, false)");
		for (Food food : FoodUtilities.foodSearch(foods, 7, 600, false))
			System.out.println(food);
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.writeFoods(foods, output)");
		PrintStream output = new PrintStream(file);
		FoodUtilities.writeFoods(foods, output);
		output.close();
		
		System.out.println("Testing FoodUtilities.writeFoods(foods, input)");
		Scanner input = new Scanner(file);
		ArrayList<Food> newFoods = FoodUtilities.readFoods(input);
		boolean equal = newFoods.size() == foods.size();
		int i = 0;
		while(i < newFoods.size()) {
			equal = newFoods.get(i).compareTo(foods.get(i)) == 0;
			++i;
		}
		
		System.out.println("newFoods == foods: " + equal);
		input.close();
		System.out.println("-----------------------------------\n");

		System.out.println("Testing FoodUtilities.foodSearch(foods, 7, 600, false");
		for (Food food : FoodUtilities.foodSearch(foods, 7, 600, false))
			System.out.println(food);
		System.out.println("-----------------------------------\n");

	}
}