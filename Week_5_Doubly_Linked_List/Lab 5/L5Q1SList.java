public class L5Q1SList<E> {
    private L5Q1SNode<E> head;
    private int size;

    public L5Q1SList(){
        this.head = null;
        this.size = 0;
    }

    public void appendEnd(E e){
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

    public E removeInitial(){
        if (head == null){
            System.out.println("List is empty, nothing to remove");
            return null;
        }
        E removed = head.item;
        head = head.next;
        size--;
        return removed;
    }

    public boolean contains(E e){
        L5Q1SNode<E> current = head;
        while (current != null){
            if (current.item.equals(e)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear(){
        head = null;
        size = 0;
        System.out.println("List is empty");
    }

    public void display(){
        if (head == null){
            System.out.println("List is empty");
            return;
        }
        L5Q1SNode<E> current = head;
        while (current != null){
            System.out.println(current.item);
            current = current.next;
        }
        System.out.println();
    }
}
