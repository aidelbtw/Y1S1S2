import java.util.LinkedList;

public class L8Q1MyQueue<E> {
    private LinkedList<E> list = new LinkedList<>();

    public L8Q1MyQueue(E[] a){
        for (E item : a){
            list.addLast(item);
        }
    }

    public L8Q1MyQueue(){
        list = new LinkedList<>();
    }

    public void enqueue(E e){
        list.addLast(e);
    }

    public E dequeue(){
        return list.removeFirst();
    }

    public E getElement(int i){
        return list.get(i);
    }

    public E peek(){
        return list.getFirst();
    }

    public int getSize(){
        return list.size();
    }

    public boolean contains(E e){
        return list.contains(e);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public String toString(){
        return "Queue" + list.toString();
    }
}
