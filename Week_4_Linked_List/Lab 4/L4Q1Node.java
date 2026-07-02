public class L4Q1Node<E> {
    E element;
    L4Q1Node<E> next;

    public L4Q1Node(){
        element = null;
        next = null;
    }

    public L4Q1Node(E o){
        element = o;
        next = null;
    }
}