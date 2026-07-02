public class L5Q3DoublyLinkedList<E> {
    private static class DNode<E> {
        E item;
        DNode<E> prev;
        DNode<E> next;

        DNode(E item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    private DNode<E> head;
    private DNode<E> tail;
    private int size;

    public L5Q3DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(E e){
        DNode<E> newNode = new DNode<>(e);
        System.out.println("Adding: " + e);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev= newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e){
        DNode<E> newNode = new DNode<>(e);
        System.out.println("Adding: " + e);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addAtIndex(E e, int index){
        if (index <= 1) {
            addFirst(e);
            return;
        }
        
        DNode<E> newNode = new DNode<>(e);
        System.out.println("Adding: " + e);
        DNode<E> current = head;
        for (int i = 1; i < index - 1 ; i++){
            current = current.next;
        }

        DNode<E> nextNode = current.next;
        newNode.prev = current;
        newNode.next = nextNode;
        current.next = newNode;
        if (nextNode != null){
            nextNode.prev = newNode;
        } else {
            tail = newNode;
        }
        size++;
    }

    public void removeAtIndex(int index) {
        if (head == null){
            System.out.println("List is empty");
            return;
        }
        DNode<E> current = head;
        for (int i = 1; i < index ; i++){
            if (current == null){
                System.out.println("Index out of bounds");
                return;
            }
            current = current.next;
        }
        System.out.println("Deleted: " + current.item);
        
        if (current.prev != null){
            current.prev.next = current.next;
        } else head = current.next;

        if (current.next != null){
            current.next.prev = current.prev;
        } else tail = current.prev;
        size--;
    }
    
    public void traverseForward(){
        System.out.println("Iterating forward..");
        DNode<E> current = head;
        while (current != null) {
            System.out.println(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void traverseBackward(){
        System.out.println("Iterating backward..");
        DNode<E> current = tail;
        while (current != null) {
            System.out.println(current.item + " ");
            current = current.prev;
        }
        System.out.println();
    }

    public int getSize(){
        return size;
    }

    public void clear(){
        System.out.println("Succesfully clear" + size + "node(s)");
        head = null;
        tail = null;
        size = 0;
    }
}
