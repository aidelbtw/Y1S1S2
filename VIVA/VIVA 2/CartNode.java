public class CartNode {
    Product product;
    int quantity;
    CartNode next;

    public CartNode(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        next = null;
    }
}
