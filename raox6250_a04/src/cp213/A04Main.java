package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main method and table generation methods for Assignment 4.
 *
 * @author Nausher Rao
 * @version 2021-03-18
 */
public class A04Main {

	public static final NumberFormat NF = NumberFormat.getInstance();
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String POPULAR = "ETAOINSHRDLUCMPFYWGBVKJXZQ";
	public static final String SPLIT = "MFTCJPWADHKNRUYBEIGLOQSVXZ";
	public static final String SEPARATOR = "------------------------------";

	public static final String[] STRING_DATA = new String[] { ALPHABET, SPLIT, POPULAR };
	public static final String FILENAME = "decline.txt";

	/**
	 * Print a formatted table of character counts and percentages in the format:
	 *
	 * <pre>
	 * Character Table for Comparisons File
	 *
	 * Char    Count Percent
	 *    A  206,946    8.17
	 *    B   37,498    1.48
	 *    ...
	 * </pre>
	 *
	 * Note: your data may not match this as it is file dependent.
	 *
	 * @param tree The BST to generate the table from.
	 */
	private static void characterTable(final BST<Character> tree) {

		System.out.print("\nChar    Count Percent\n");

		double total = 0;
		int count;
		double percent;

		ArrayList<CountedData<Character>> tree_array = tree.inOrder();

		for (CountedData<Character> tree_node : tree_array)
			total += (double) tree_node.getCount();

		for (CountedData<Character> tree_node : tree_array) {
			count = tree_node.getCount();
			percent = (count / total) * 100;
			System.out.printf("%4c %,8d %7.2f\n", tree_node.getData(), count, percent);
		}

	}

	/**
	 * Fill a tree by inserting all letters from a string into the tree. Letters
	 * must be converted to upper-case. Non-letters are ignored. The order that
	 * letters are inserted into the tree determine its shape.
	 *
	 * @param tree   The BST/AVL/PopularityTree to fill.
	 * @param string The string to read into the tree.
	 */
	private static void fillTree(final BST<Character> tree, final String string) {

		for (final char curr_char : string.toCharArray()) {
			CountedData<Character> char_data = new CountedData<>(Character.toUpperCase(curr_char)); // create a new
																									// object for the																		// char
			tree.insert(char_data);
		}

	}

	/**
	 * Determine the number of comparisons to retrieve the contents of a file from a
	 * tree. Attempt to retrieve every letter in the file from tree. All letters
	 * must be converted to upper case.
	 *
	 * @param tree The BST to process.
	 * @param file The file to process.
	 * @return The number of comparisons necessary to find every letter in file in
	 *         tree.
	 * @throws FileNotFoundException Thrown if file not found.
	 */
	private static int retrieve(final BST<Character> tree, final Scanner fileScan) {

		// iterating through every line in the file
		while (fileScan.hasNextLine()) {

			// grab the current line in the file
			final String line = fileScan.nextLine();

			for (Character curr_c : line.toCharArray()) { // taking each character in the line

				if (Character.isLetter(curr_c)) {

					CountedData<Character> char_key = new CountedData<>(Character.toUpperCase(curr_c)); // create object
																										// for current
																										// character
					tree.retrieve(char_key); // comparison count will be updated inside of the retrieve function
				}
			}
		}

		int comparisons = tree.getComparisons();

		return comparisons;

	}

	/**
	 * Program for Assignment 4.
	 *
	 * @param args unused
	 * @throws IOException If error on files.
	 */
	public static void main(final String[] args) throws IOException {
		final File comparisonsFile = new File(FILENAME);

		for (final String string : STRING_DATA) {
			int minComparisons = Integer.MAX_VALUE;
			String treeType = null;
			String minTree = null;
			System.out.println("Data String: " + string);
			System.out.println();
			final ArrayList<BST<Character>> trees = new ArrayList<>();
			trees.add(new BST<Character>());
			trees.add(new PopularityTree<Character>());
			trees.add(new AVL<Character>());

			for (final BST<Character> tree : trees) {
				treeType = tree.getClass().getSimpleName();
				System.out.println("  Tree Type: " + treeType);
				A04Main.fillTree(tree, string);
				final Scanner fileScan = new Scanner(comparisonsFile);
				final int comparisons = A04Main.retrieve(tree, fileScan);
				fileScan.close();
				System.out.println("  Height: " + tree.getHeight());
				System.out.println("  Comparisons: " + NF.format(comparisons));

				if (comparisons < minComparisons) {
					minComparisons = comparisons;
					minTree = treeType;
				}

				System.out.println();
			}

			System.out.println("Tree with minimum comparisons: " + minTree);
			System.out.println(SEPARATOR);
		}

		final PopularityTree<Character> bst = new PopularityTree<>();
		A04Main.fillTree(bst, ALPHABET);
		final Scanner fileScan = new Scanner(comparisonsFile);
		A04Main.retrieve(bst, fileScan);
		fileScan.close();
		System.out.println("Character Table for Comparisons File");
		System.out.println();
		A04Main.characterTable(bst);

	}
}