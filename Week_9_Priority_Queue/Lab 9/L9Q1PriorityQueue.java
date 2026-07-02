import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class L9Q1PriorityQueue {
    public static void main(String[] args) {
        int[] numbers = {4, 8, 1, 2, 9, 6, 3, 7};
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : numbers){
            pq.offer(num);
        }

        System.out.println("Priority Queue: " + pq);

        int polled = pq.poll();
        System.out.println("Polled element: " + polled);
        System.out.println("Priority Queue after poll(): " + pq);

        pq.add(5);
        System.out.println("Priority Queue after adding 5: " + pq);

        Object[] arr = pq.toArray();
        System.out.println("Array representation: " + Arrays.toString(arr));

        System.out.println("Peek (first element): " + pq.peek());

        System.out.println("Does queue contain 1? " + pq.contains(1));

        System.out.println("Current size: " + pq.size());

        System.out.println("Removing all elements:");
        while (!pq.isEmpty()) {
            System.out.println("Removed: " + pq.poll());
        }
        System.out.println("Is queue empty now? " + pq.isEmpty());

        PriorityQueue<Integer> reversePQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : numbers) {
            reversePQ.offer(num);
        }
        System.out.println("\nReverse Order Priority Queue: " + reversePQ);

        System.out.println("Removing in reverse order:");
        while (!reversePQ.isEmpty()) {
            System.out.println("Removed: " + reversePQ.poll());
        }
    }
}
