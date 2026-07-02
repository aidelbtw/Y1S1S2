public class LinkedListStack {
    public static class CartAction {
        int productId;
        int quantity;

        public CartAction(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    private static class StackNode {
        CartAction data;
        StackNode next;

        StackNode(CartAction data){
            this.data = data;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public LinkedListStack(){
        top = null;
        size = 0;
    }

    public void push(CartAction action){
        StackNode newNode = new StackNode(action);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public CartAction pop(){
        if (isEmpty()){
            System.out.println("Nothing to undo");
            return null;
        }
        CartAction action = top.data;
        top = top.next;
        size--;
        return action;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public int getSize(){
        return size;
    }

    public void clear(){
        top = null;
        size = 0;
    }
}