package cp213;

/**
 * Sample testing for Assignment 3 Data Structures.
 *
 * @author your name here
 * @version 2021-02-05
 */
public class A03Main {

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
        list();

    }

    public static void list() {
        System.out.println(LINE + "\nSingleList\n" + LINE);
        SingleList<Integer> list = new SingleList<>();
        System.out.println("Length: " + list.getLength());
        print(list);
        System.out.println("***Add values:");
        for (int i = 0; i < 6; i++) 
            list.append(i);
        
        System.out.println("Length: " + list.getLength());
        System.out.println("Peek: " + list.peek());
        print(list);    

        System.out.println("***Clear list:");
        while (!list.isEmpty()) 
            System.out.println("Removed: " + list.removeFront());
            
        print(list);
        System.out.println("Length: " + list.getLength());

        System.out.println("***Add values:");
        list.append(0);
        list.append(3);
        list.append(5);
        for (int i = 0; i < 6; i++) 
            list.append(i);
        System.out.println("Length: " + list.getLength());
        print(list);

        System.out.println("***Clean:");
        System.out.println("Length: " + list.getLength());
        list.clean();
        print(list);

        split();
        splitAlt();
        union();
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

    public static void split() {
        SingleList<Integer> list = new SingleList<Integer>();
        SingleList<Integer> a = new SingleList<Integer>();
        SingleList<Integer> b = new SingleList<Integer>();
        for(int i = 0; i < 10; i++)
            list.append(i);

        list.split(a, b);
        System.out.println("***Split:");
        print(list);
        print(a);
        print(b);


    }

    
    public static void splitAlt() {
        SingleList<Integer> list = new SingleList<Integer>();
        SingleList<Integer> a = new SingleList<Integer>();
        SingleList<Integer> b = new SingleList<Integer>();
        for(int i = 0; i < 10; i++)
            list.append(i);

        list.splitAlternate(a, b);
        System.out.println("***Split Alt:");
        print(list);
        print(a);
        print(b);

    }

    public static void union() {
        SingleList<Integer> list = new SingleList<Integer>();
        SingleList<Integer> a = new SingleList<Integer>();
        SingleList<Integer> b = new SingleList<Integer>();
        for(int i = 0; i < 5; i++)
            a.append(i);

        for(int i = 3; i < 10; i++)
            b.append(i);

        list.union(a, b);
        System.out.println("***Union:");
        print(list);
        print(a);
        print(b);
        
    }

}