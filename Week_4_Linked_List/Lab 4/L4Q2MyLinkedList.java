public class L4Q2MyLinkedList<E> {
    private L4Q1Node<E> head;
    private L4Q1Node<E> tail;
    private int size;
    
    public L4Q2MyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(E e){
        L4Q1Node<E> newNode = new L4Q1Node<>(e); //creates a new node

        if(head == null){
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public int size(){
        return size;
    }

    public void addLast(E e){
        L4Q1Node<E> newNode = new L4Q1Node<>(e);
        if(tail == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, E e){
        L4Q1Node<E> newNode = new L4Q1Node<>(e);
        if(index < 0 || index > size){
            System.out.println("Index out of bounds: " + index);
            return;
        }

        if(index == 0) {{addFirst(e);} return;}
        if(index == size) {{addLast(e);} return;}

        L4Q1Node<E> current = head;
        for(int i = 0; i < index - 1; i++){
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public E removeFirst(){
        if (head == null) {
            System.out.println("List is empty");
            return null;
        }

        E removed = head.element;
        head = head.next;

        if(head==null) tail = null;
        size--;
        return removed;
    }

    public E removeLast(){
        if(head == null){
            System.out.println("List is empty");
            return null;
        }

        E removed;

        if(head == tail) {
            removed = head.element;
            head = null;
            tail = null;
            size--;
            return removed;
        }

        L4Q1Node<E> current = head;
        while (current.next != tail) {
            current = current.next;
        }

        removed = tail.element;
        tail = current;
        tail.next = null;
        size--;
        return removed;
    }

    public E remove(int index){
        if (index < 0 || index >= size){
            System.out.println("Out of bounds: " + index);
            return null;
        }

        if (index == 0) return removeFirst();
        if (index == size) return removeLast();

        L4Q1Node<E> current = head;
        for(int i = 0; i < index-1; i++){
            current = current.next;
        }

        E removed = current.next.element;
        current.next = current.next.next;
        size--;
        return removed;
    }

    public boolean contains(E e) {
        L4Q1Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e)) return true;
            current = current.next;
        }
        return false;
    }

    public E get(int index){
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds: " + index);
            return null;
        }
 
        L4Q1Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public E getFirst() {
        if (head == null) { System.out.println("List is empty."); return null; }
        return head.element;
    }

    public E getLast() {
        if (tail == null) { System.out.println("List is empty."); return null; }
        return tail.element;
    }

    public int indexOf(E e) {
        L4Q1Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(e)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(E e) {
        L4Q1Node<E> current = head;
        int index = 0, lastFound = -1;
        while (current != null) {
            if (current.element.equals(e)) lastFound = index;
            current = current.next;
            index++;
        }
        return lastFound;
    }

    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds: " + index);
            return null;
        }
 
        L4Q1Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
 
        E old = current.element;
        current.element = e;
        return old;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void print() {
        L4Q1Node<E> current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.element);
            if (current.next != null) System.out.print(" --> ");
            current = current.next;
        }
        System.out.println();
    }

    public void reverse() {
        if (head == null) { System.out.println("List is empty."); return; }
 
        Object[] arr = new Object[size];
        L4Q1Node<E> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.element;
            current = current.next;
        }
 
        System.out.print("Reversed: ");
        for (int i = size - 1; i >= 0; i--) {
            System.out.print(arr[i]);
            if (i > 0) System.out.print(" --> ");
        }
        System.out.println();
    }
    

    public E getMiddleValue(){
        if (head == null) {
            System.out.println("List is empty");
            return null;
        }

        L4Q1Node<E> back = head;
        L4Q1Node<E> front = head;

        while (front != null && front.next != null) {
            back = back.next;
            front = front.next.next;
        }

        return back.element;
    }
}
