public class CartList { //do undo
    private CartNode head;
    private CartNode tail;
    private int size;

    public CartList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void addItem(Product p, int qty){
        CartNode existing = findItem(p.getId());
        if (existing != null){
            existing.quantity += qty;
            System.out.println("Updated quantity of " + p.getName() + " in the cart\n");
            return;
        }

        CartNode newNode = new CartNode(p, qty);

        if (tail == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        System.out.println("Added (" + qty + ") " + p.getName() + " to cart");
    }

    public CartNode removeItem(int productId){
        if (head == null) return null;

        if (head.product.getId() == productId){//find head first cause can terus deal with it
            CartNode removed = head;
            head = head.next;
            if (head == null) tail = null;
            size--;
            return removed;
        }
        
        CartNode current = head;
        while (current.next != null){
            if (current.next.product.getId() == productId){
                CartNode removed = current.next;

                if (removed == tail) tail = current;

                current.next = removed.next;
                size--;
                return removed;
            }
            current = current.next;
        }
        return null;
    }

    public int updateQuantity(int productId, int newQty){
        CartNode node = findItem(productId);
        if(node == null) return -1;

        int diff = newQty - node.quantity;
        node.quantity = newQty;
        return diff;
    }

    public CartNode findItem(int productId){
        CartNode current = head;
        while (current != null){
            if (current.product.getId() == productId) return current;
            current = current.next;
        }
        return null;
    }

    public void displayCart(){
        if (head == null){
            System.out.println("Cart is empty");
            return;
        }
        CartNode current = head;
        while (current != null) {
            double subtotal = current.product.getPrice()*current.quantity;
            System.out.printf("ID: %d | Name: %s | Quntity: %-2d | Price: RM%.2f%n", 
            current.product.getId(), current.product.getName(), current.quantity, current.product.getPrice(), subtotal);
            current = current.next;
        }
        System.out.printf("Total: RM%.2f%n" , calculateTotal());
    }

    public CartNode undo(){
        if (head == null){
            return null;
        }

        if (head == tail){
            CartNode removed = head;
            head = null;
            tail = null;
            size--;
            return removed;
        }

        CartNode current = head;
        while (current.next != tail){
            current = current.next;
        }
        CartNode removed = tail;
        tail = current;
        tail.next = null;
        size--;
        return removed;
    }

    public double calculateTotal(){
        double total = 0;
        CartNode current = head;
        while (current != null){
            total += current.product.getPrice() * current.quantity;
            current = current.next;
        }
        return total;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public CartNode getHead(){
        return head;
    }

    public boolean isEmpty(){
        return head == null;
    }
}
