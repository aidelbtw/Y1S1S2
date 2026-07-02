import java.util.Stack;

public class L6Q5TowerOfHanoi {
    public static void main(String[] args) {
        int numDisks = 3; // change as needed

        Stack<Integer> sourceStack = new Stack<>();
        Stack<Integer> auxStack = new Stack<>();
        Stack<Integer> destStack = new Stack<>();

        // Initialize source stack with disks (largest at bottom)
        for (int i = numDisks; i >= 1; i--) {
            sourceStack.push(i);
        }

        System.out.println("Initial state:");
        System.out.println("Source: " + sourceStack);
        System.out.println("Auxiliary: " + auxStack);
        System.out.println("Destination: " + destStack);
        System.out.println();

        towerOfHanoi(numDisks, sourceStack, destStack, auxStack, "Source", "Destination", "Auxiliary");

        System.out.println("\nFinal state:");
        System.out.println("Source: " + sourceStack);
        System.out.println("Auxiliary: " + auxStack);
        System.out.println("Destination: " + destStack);
    }

    public static void towerOfHanoi(int n, Stack<Integer> source, Stack<Integer> dest,
                                     Stack<Integer> aux, String sourceName, String destName, String auxName) {
        if (n == 0) {
            return;
        }

        // Move n-1 disks from source to aux, using dest as helper
        towerOfHanoi(n - 1, source, aux, dest, sourceName, auxName, destName);

        // Move the nth disk from source to dest
        int disk = source.pop();
        dest.push(disk);
        System.out.println("Move disk " + disk + " from " + sourceName + " to " + destName);

        // Move n-1 disks from aux to dest, using source as helper
        towerOfHanoi(n - 1, aux, dest, source, auxName, destName, sourceName);
    }
}
