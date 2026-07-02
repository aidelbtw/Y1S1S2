public class DSLab3 {

    public static void main(String[] args) {

        ArrayBag<String> bag1 = new ArrayBag<>();
        ArrayBag<String> bag2 = new ArrayBag<>();
       
        

        String[] contentsOfBag1 = {"A", "A", "B", "A", "C", "A"};
        String[] contentsOfBag2 = {"A", "B", "A", "C", "B", "C", "D", "another"};
        System.out.println("bag1:");
        testAdd(bag1, contentsOfBag1);
        displayBag(bag1);
        System.out.println("\nbag2:");
        testAdd(bag2, contentsOfBag2);
        displayBag(bag2);

        System.out.println("\nbag3, test the method union +"
                + "of bag1 and bag2:");
        BagInterface<String> bag3 = bag1.union(bag2);
        displayBag(bag3);

        System.out.println("\nbag4, test the method intersection +"
                + "of bag1 and bag2:");

        BagInterface<String> bag4 = bag1.intersection(bag2);
        displayBag(bag4);
        System.out.println("\nbag5, test the method difference "
                + "+ of bag1 and bag2:");

        BagInterface<String> bag5 = bag1.difference(bag2);
        displayBag(bag5);
    }

    private static void testAdd(BagInterface<String> aBag, String[] content) {
        System.out.print("Adding ");
        for (int index = 0; index < content.length; index++) {
            aBag.add(content[index]);
            System.out.print(content[index] + " ");
        }
        System.out.println();
    }

    private static void displayBag(BagInterface<String> aBag) {
        System.out.println("The bag contains " + aBag.getCurrentSize()
                + " string(s), as follows:");
        Object[] bagArray = aBag.toArray();
        for (int index = 0; index < bagArray.length; index++) {
            System.out.print(bagArray[index] + " ");
        }
        System.out.println();
    }
}