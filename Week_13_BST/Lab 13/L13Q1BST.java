import java.util.ArrayList;

public class L13Q1BST<E extends Comparable<E>> {
    protected L13Q1TreeNode<E> root;
    protected int size = 0;

    public L13Q1BST(){
        root = null;
    }

    public L13Q1BST(E[] objects){
        for (E e : objects){
            insert(e);
        }
    }

    public boolean insert (E e){
        if (root == null){
            root = new L13Q1TreeNode<>(e);
            size++;
            return true;
        }

        L13Q1TreeNode<E> parent = null;
        L13Q1TreeNode<E> current = root; //compares the value and decides if go left or right

        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0){
                parent = current;
                current = current.left;
            } else if (cmp > 0){
                parent = current;
                current = current.right;
            } else {
                return false;
            }
        }

        if (e.compareTo(parent.element) < 0){
            parent.left = new L13Q1TreeNode<>(e); //places it referring to parent punya left and right
        } else {
            parent.right = new L13Q1TreeNode<>(e);
        }
        size++;
        return true;
    }

    public boolean search(E e){
        L13Q1TreeNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0){
                current = current.left;
            } else if (cmp > 0){
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        return size;
    }

    public int height(){
        return height(root);
    }

    public int height(L13Q1TreeNode<E> node){
        if (node == null){
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public E getRoot(){
        if (root == null){
            return null;
        }
        return root.element;
    }

    public E minValue(){
        if (root == null){
            return null;
        }
        L13Q1TreeNode<E> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.element;
    }

    public E maxValue(){
        if (root == null){
            return null;
        }
        L13Q1TreeNode<E> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.element;
    }
    

    public java.util.ArrayList<L13Q1TreeNode<E>> path(E e){
        ArrayList<L13Q1TreeNode<E>> list = new ArrayList<>();
        L13Q1TreeNode<E> current = root;
        while (current != null) {
            list.add(current);
            int cmp = e.compareTo(current.element);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list;
    }

    public boolean delete(E e){
        L13Q1TreeNode<E> parent = null;
        L13Q1TreeNode<E> current = root;

        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0){
                parent = current;
                current = current.left;
            } else if (cmp > 0) {
                parent = current;
                current = current.right;
            } else {
                break; //found
            }
        }

        if (current == null){
            return false; //not found
        }

        if (current.left == null){ //no child AND only right
            if (parent == null){
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0){
                    parent.left = current.right;    //if the child of the current one should be on left, when it gets adopted, it goes on left also
                } else {
                    parent.right = current.right;   //like a linked list, if the current just went to the top, it needs someone to link to
                }
            }

        } else { //left child AND both
            L13Q1TreeNode<E> parentOfRightMost = current;
            L13Q1TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
        
            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;   //because nothing should be on the right anymore,
            } else {                                        //right means biggest, nothing can be bigger anymore
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; 
    }



    public boolean clear(){
        root = null;
        size = 0;
        return true;
    }

    protected void inorder(L13Q1TreeNode<E> root){
        if (root == null){
            return;
        }

        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    protected void postorder(L13Q1TreeNode<E> root){
        if (root == null){
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    protected void preorder(L13Q1TreeNode<E> root){
        if (root == null){
            return;
        }

        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void inorder(){
        inorder(root);
    }

    public void postorder(){
        postorder(root);
    }

    public void preorder(){
        preorder(root);
    }
}
