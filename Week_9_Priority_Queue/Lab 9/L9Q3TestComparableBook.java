import java.util.Queue;
import java.util.PriorityQueue;
public class L9Q3TestComparableBook {
    public static void main(String[] args) {
        Queue<L9Q3ComparableBook> BookQueue = new PriorityQueue<>();

        BookQueue.add(new L9Q3ComparableBook(1065, "Effective Java: Third Edition"));
        BookQueue.add(new L9Q3ComparableBook(3012, "Java: A Beginner Guide Seventh Edition"));
        BookQueue.add(new L9Q3ComparableBook(1097, "Learn Java in One Day and Learn It Well"));
        BookQueue.add(new L9Q3ComparableBook(7063, "Beginning Programming with Java (Dummies)"));
        BookQueue.add(new L9Q3ComparableBook(6481, "Java: Programming Basic for Absolute Beginner"));

        System.out.println(BookQueue);

        while (BookQueue.peek() != null) {
            System.out.println();
            System.out.println("Head Element: " + BookQueue.peek());
            BookQueue.remove();
            System.out.println("Priority queue: " + BookQueue);
        }
    }
}
