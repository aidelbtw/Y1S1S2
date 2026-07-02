import java.util.Arrays;
import java.util.PriorityQueue;

public class L9Q2PriorityQueueName {
    public static void main(String[] args) {
        String[] names1 = {"George", "Jim", "John", "Blake", "Kevin", "Michael"};
        String[] names2 = {"George", "Katie", "Kevin", "Michelle", "Ryan"};     
        
        PriorityQueue<String> pq1 = new PriorityQueue<>(Arrays.asList(names1));
        PriorityQueue<String> pq2 = new PriorityQueue<>(Arrays.asList(names2));

        System.out.println("Priority Queue 1: " + pq1);
        System.out.println("Priority Queue 2: " + pq2);

        //Union
        PriorityQueue<String> union = new PriorityQueue<>(pq1);
        union.addAll(pq2);
        System.out.println("\nUnion: " + union);

        //Difference
        PriorityQueue<String> difference = new PriorityQueue<>(pq1);
        difference.removeAll(pq2);
        System.out.println("\nDifference (PQ1 - PQ2): " + difference);

        //Intersection
        PriorityQueue<String> intersection = new PriorityQueue<>(pq1);
        intersection.retainAll(pq2);
        System.out.println("\nIntersection: " + intersection);
    }
}
