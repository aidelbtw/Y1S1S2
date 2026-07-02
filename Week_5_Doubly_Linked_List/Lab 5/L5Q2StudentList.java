public class L5Q2StudentList<E> {
    private L5Q1SNode<E> head;
    private int size;

    public L5Q2StudentList(){
        head = null;
        size = 0;
    }

    public void add(E e){
        L5Q1SNode<E> newNode = new L5Q1SNode<>(e);
        if (head == null){
            head = newNode;
        } else {
            L5Q1SNode<E> current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void removeElement(E e){
        if (head == null){
            return;
        }

        if (head.item.equals(e)){
            head = head.next;
            size--;
            return;
        }

        L5Q1SNode<E> current = head;
        while (current.next != null){
            if (current.next.item.equals(e)){
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
        System.out.println("Element not found");
    }

    public void printList(){
        if (head == null) {
            System.out.println("");
            return;
        }
        L5Q1SNode<E> current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null){
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append(".");
        System.out.println(sb);
    }

    public int getSize(){
        return size;
    }

    public boolean contains(E e){
        L5Q1SNode current = head;
        while (current != null){
            if (current.item.equals(e)) return true;
            current = current.next;
        }
        return false;
    }

    public void replace(E e, E newE){
        L5Q1SNode<E> current = head;
        while (current != null) {
            if (current.item.equals(e)){
                current.item = newE;
                return;
            }
            current = current.next;
        }
        System.out.println("Element not found");
    }
}