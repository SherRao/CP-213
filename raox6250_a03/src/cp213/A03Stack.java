package cp213;

/**
 * Sample testing for Assignment 3 Data Structures.
 *
 * @author your name here
 * @version 2021-02-05
 */
public class A03Stack {

	static final String LINE = new String(new char[40]).replace("\0", "-");
    
    /**
     * Note that not all the data structure methods are called in this main. The
     * main method is just an illustration of how you may test your code. Test your
     * code thoroughly.
     *
     * When you start, comment out all code in the main, and un-comment as you add
     * code to the class.
     *
     * @param args (unused)
     */
    public static void main(String... vargs) {
        stack();

    }

    public static void stack() {
        System.out.println(LINE + "\nSingleList\n" + LINE);
        SingleStack<Integer> list = new SingleStack<>();
        System.out.println("Length: " + list.getLength());
        print(list);
        System.out.println("***Add values:");
        for (int i = 0; i < 6; i++) 
            list.push(i);
        
        System.out.println("Length: " + list.getLength());
        System.out.println("Peek: " + list.peek());
        print(list);    

        System.out.println("***Clear list:");
        while (!list.isEmpty()) 
            System.out.println("Removed: " + list.pop());
            
        print(list);
        System.out.println("Length: " + list.getLength());
        System.out.println(LINE);
        
    }

    public static void print(SingleLink<Integer> struct) {
        System.out.println("F: " + (struct.front == null ? null : struct.front.getData()));
        System.out.println("R: " + (struct.rear == null ? null : struct.rear.getData()));
        System.out.print("A: ");
        for(Integer t : struct)
            System.out.print(t + "->");
        
        System.out.println("\n");

    }

}