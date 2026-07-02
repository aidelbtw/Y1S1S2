public class L13Q1TreeNode<E extends Comparable<E>> {
    protected E element;
    protected L13Q1TreeNode<E> left;
    protected L13Q1TreeNode<E> right;

    public L13Q1TreeNode(E e){
        element = e;
        left = null;
        right = null;
    }

    public E getElement(){
        return element;
    }

    public void setElement(E e){
        element = e;
    }

    public L13Q1TreeNode<E> getRight(){
        return right;
    }

    public void setRight(L13Q1TreeNode<E> right){
        this.right = right;
    }

    public L13Q1TreeNode<E> getLeft(){
        return left;
    }

    public void setLeft(L13Q1TreeNode<E> left){
        this.left = left;
    }

    @Override
    public String toString(){
        return element.toString();
    }
}
