public class L3Q1test {
    public static void main(String[] args) {
        L3Q1<String> bag1 = new L3Q1<>();
        L3Q1<String> bag2 = new L3Q1<>();

        String[] contentsOfBag1 = {"A", "A", "B", "A", "C", "A"};
        String[] contentsOfBag2 = {"A", "B", "A", "C", "B", "C", "D", "another"};
        System.out.println("Bag 1: ");
        testAdd(bag1, contentsOfBag1);
        displayBag(bag1);
        System.out.println("\nBag 2: ");
        testAdd(bag2, contentsOfBag2);
        displayBag(bag2);

        System.out.println("\nBag 3, testing the union method of Bag 1 & 2: ");
        BagInterface<String> bag3 = bag1.union(bag2);
        displayBag(bag3);

        System.out.println("\nBag 4, testing the intersection method of Bag 1 & 2: ");
        BagInterface<String> bag4 = bag1.intersection(bag2);
        displayBag(bag4);

        System.out.println("\nBag 5, testing the difference method of Bag 1 & 2: ");
        BagInterface<String> bag5 = bag1.difference(bag2);
        displayBag(bag5);
    }

    private static void testAdd(BagInterface<String> aBag, String[] content){
        System.out.println("Adding ");
        for (int index = 0; index < content.length; index++){
            aBag.add(content[index]);
            System.out.print(content[index] + " ");
        }
        
        System.out.println();
    }

    private static void displayBag(BagInterface<String> aBag){
        System.out.println("The bag contains: " + aBag.getCurrentSize() + " string(s), as follows:");
        Object[] bagArray = aBag.toArray();
        for (int index = 0; index < bagArray.length; index++){
            System.out.print(bagArray[index] + " ");
        }
        System.out.println();
    }
}
