import java.util.ArrayList;

public class L6Q1MyStack<E> {
    private ArrayList<E> list = new ArrayList<>();

    public void push(E o){
        list.add(o);
    }

    public E pop(){
        E temp = list.get(getSize()-1);
        list.remove(getSize()-1);
        return temp;
    }

    public E peek(){
        return list.get(getSize() - 1);
    }

    public int getSize(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public String toString(){
        return "Stack: " + list.toString();
    }

    public boolean search(E o){
        return list.contains(o);
    }
}
