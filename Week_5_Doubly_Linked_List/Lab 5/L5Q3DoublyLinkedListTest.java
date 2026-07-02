public class L5Q3DoublyLinkedListTest {
    public static void main(String[] args) {

    L5Q3DoublyLinkedList<Integer>dll = new L5Q3DoublyLinkedList<>();
    dll.addFirst(1);
    dll.addLast(100);
    dll.addAtIndex(10,2);
    dll.removeAtIndex(3);
    dll.traverseForward();
    dll.traverseBackward();
    System.out.println("Size: " + dll.getSize());
    dll.clear();
    System.out.println("Size: " + dll.getSize());
    }
}
