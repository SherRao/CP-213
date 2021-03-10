package cp213;

/**
 * Sample testing for Assignment 3 Data Structures.
 *
 * @author your name here
 * @version 2021-02-05
 */
public class A03MainQueue {

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
        System.out.println(LINE + "\nSingleQueue\n" + LINE);
        SingleQueue<Integer> list = new SingleQueue<>();
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

        splitQueue();
        System.out.println(LINE);
        
    }

    public static void print(SingleQueue<Integer> struct) {
        System.out.println("F: " + (struct.front == null ? null : struct.front.getData()));
        System.out.println("R: " + (struct.rear == null ? null : struct.rear.getData()));
        System.out.print("A: ");
        for(Integer t : struct)
            System.out.print(t + "->");
        
        System.out.println("\n");

    }

    public static void splitQueue() {
        SingleQueue<Integer> list = new SingleQueue<Integer>();
        SingleQueue<Integer> a = new SingleQueue<Integer>();
        SingleQueue<Integer> b = new SingleQueue<Integer>();
        for(int i = 0; i < 10; i++)
            list.insert(i);

        list.split(a, b);
        System.out.println("***Split:");
        print(list);
        print(a);
        print(b);


    }

    public static void splitCombine() {
        SingleQueue<Integer> list = new SingleQueue<Integer>();
        SingleQueue<Integer> a = new SingleQueue<Integer>();
        SingleQueue<Integer> b = new SingleQueue<Integer>();
        for(int i = 0; i < 5; i++)
            a.insert(i);

        for(int i = 4; i < 9; i++)
            b.insert(i);

        list.combine(a, b);
        System.out.println("***Combine:");
        print(list);
        print(a);
        print(b);

    }


}