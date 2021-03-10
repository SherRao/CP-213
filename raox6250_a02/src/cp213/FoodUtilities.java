package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author Nausher Rao 190906250
 * @version 2021-01-29
 */
public class FoodUtilities {

	/**
	 * Determines the average calories in a list of foods. No rounding necessary.
	 * Foods list parameter may be empty.
	 *
	 * @param foods a list of Food
	 * @return average calories in all Food objects
	 */
	public static int averageCalories(final ArrayList<Food> foods) {
		int average = 0;
		int count = 0;
		for (Food food : foods) {
			average += food.getCalories();
			count += 1;
		}

		return average / count;
	}

	/**
	 * Determines the average calories in a list of foods for a particular origin.
	 * No rounding necessary. Foods list parameter may be empty.
	 *
	 * @param foods  a list of Food
	 * @param origin the origin of the Food
	 * @return average calories for all Foods of the specified origin
	 */
	public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {
		int average = 0;
		int count = 0;
		for (Food food : foods)
			if (food.getOrigin() == origin) {
				average += food.getCalories();
				count += 1;
			}

		return average / count;
	}

	/**
	 * Creates a list of foods by origin.
	 *
	 * @param foods  a list of Food
	 * @param origin a food origin
	 * @return a list of Food from origin
	 */
	public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {
		ArrayList<Food> list = new ArrayList<>();
		for (Food food : foods)
			if (food.getOrigin() == origin)
				list.add(food);

		return list;

	}

	/**
	 * Creates a Food object by requesting data from a user. Uses the format:
	 * 
	 * <pre>
	Name: name
	Origins
	 0 Canadian
	 1 Chinese
	...
	11 English
	Origin: origin number
	Vegetarian (Y/N): Y/N
	Calories: calories
	 * </pre>
	 *
	 * @param keyboard a keyboard Scanner
	 * @return a Food object
	 */
	public static Food getFood(final Scanner keyboard) {
		System.out.print("Name: ");
		String name = keyboard.nextLine();

		System.out.println(Food.originsMenu());
		System.out.print("Origin: ");
		int origin = keyboard.nextInt();

		System.out.print("Vegetarian (Y/N): ");
		boolean isVegetarian = keyboard.next().equals("Y");

		System.out.print("Calories: ");
		int calories = keyboard.nextInt();

		return new Food(name, origin, isVegetarian, calories);

	}

	/**
	 * Creates a list of vegetarian foods.
	 *
	 * @param foods a list of Food
	 * @return a list of vegetarian Food
	 */
	public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {
		ArrayList<Food> list = new ArrayList<>();
		for (Food food : foods)
			if (food.isVegetarian())
				list.add(food);

		return list;

	}

	/**
	 * Creates and returns a Food object from a line of string data.
	 *
	 * @param line a vertical bar-delimited line of food data in the format
	 *             name|origin|isVegetarian|calories
	 * @return the data from line as a Food object
	 */
	public static Food readFood(final String line) {
		String[] input = line.split("\\|");
		return new Food(input[0], Integer.parseInt(input[1]), Boolean.parseBoolean(input[2]),
				Integer.parseInt(input[3]));

	}

	/**
	 * Reads a file of food strings into a list of Food objects.
	 *
	 * @param fileIn a Scanner of a Food data file in the format
	 *               name|origin|isVegetarian|calories
	 * @return a list of Food
	 */
	public static ArrayList<Food> readFoods(final Scanner fileIn) {
		ArrayList<Food> list = new ArrayList<>();
		while (fileIn.hasNextLine())
			list.add(readFood(fileIn.nextLine()));

		fileIn.close();
		return list;
	}

	/**
	 * Searches for foods that fit certain conditions.
	 *
	 * @param foods        a list of Food
	 * @param origin       the origin of the food; if -1, accept any origin
	 * @param maxCalories  the maximum calories for the food; if 0, accept any
	 * @param isVegetarian whether the food is vegetarian or not; if false accept
	 *                     any
	 * @return a list of foods that fit the conditions specified
	 */
	public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
			final boolean isVegetarian) {
		ArrayList<Food> list = new ArrayList<>();
		for (Food food : foods)
			if ((origin == -1 || food.getOrigin() == origin) && (maxCalories == 0 || food.getCalories() <= maxCalories)
					&& (!isVegetarian || food.isVegetarian()))
				list.add(food);

		return list;
	}

	/**
	 * Writes the contents of a list of Food to a PrintStream.
	 *
	 * @param foods a list of Food
	 * @param ps    the PrintStream to write to
	 */
	public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {
		for (Food food : foods)
			food.write(ps);

		ps.flush();

	}
}
