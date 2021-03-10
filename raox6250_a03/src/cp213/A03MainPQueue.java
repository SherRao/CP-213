package cp213;

/**
 * Sample testing for Assignment 3 Data Structures.
 *
 * @author your name here
 * @version 2021-02-05
 */
public class A03MainPQueue {

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
        queue();

    }

    public static void queue() {
        System.out.println(LINE + "\nSinglePQueue\n" + LINE);
        SinglePriorityQueue<Integer> list = new SinglePriorityQueue<>();
        System.out.println("Length: " + list.getLength());
        print(list);
        System.out.println("***Add values:");
        for (int i = 0; i < 6; i++) 
            list.insert(i);
        
        System.out.println("Length: " + list.getLength());
        System.out.println("Peek: " + list.peek());
        print(list);    

        System.out.println("***Clear list:");
        while(!list.isEmpty()) 
            System.out.println("Removed: " + list.remove());
            
        print(list);
        System.out.println("Length: " + list.getLength());

        System.out.println("***Add values:");
        list.insert(0);
        list.insert(3);
        list.insert(5);
        for (int i = 0; i < 6; i++) 
            list.insert(i);

        System.out.println("Length: " + list.getLength());
        print(list);
        System.out.println(LINE);
        
    }

    public static void print(SinglePriorityQueue<Integer> struct) {
        System.out.println("F: " + (struct.front == null ? null : struct.front.getData()));
        System.out.println("R: " + (struct.rear == null ? null : struct.rear.getData()));
        System.out.print("A: ");
        for(Integer t : struct)
            System.out.print(t + "->");
        
        System.out.println("\n");

    }

    public static void splitQueue() {
        SinglePriorityQueue<Integer> list = new SinglePriorityQueue<Integer>();
        SinglePriorityQueue<Integer> a = new SinglePriorityQueue<Integer>();
        SinglePriorityQueue<Integer> b = new SinglePriorityQueue<Integer>();
        for(int i = 0; i < 10; i++)
            list.insert(i);

        list.splitByKey(3, a, b);
        System.out.println("***Split:");
        print(list);
        print(a);
        print(b);


    }

}