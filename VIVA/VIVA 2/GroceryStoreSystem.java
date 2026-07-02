import java.util.Scanner;

public class GroceryStoreSystem {
    private static String FileName = "inventory.txt";
    private static InventoryManager inventory = new InventoryManager();
    private static CartList cart = new CartList();
    private static Scanner input = new Scanner(System.in);
    private static LinkedListStack LinkedListStack = new LinkedListStack();

    private static void displayMenu(){
        System.out.println("         GROCERY STORE SYSTEM          ");
        System.out.println("========================================");
        System.out.println("  1.  Display All Products");
        System.out.println("  2.  Search Product by ID");
        System.out.println("  3.  Search Product by Name");
        System.out.println("  4.  Add Product");
        System.out.println("  5.  Remove Product");
        System.out.println("  6.  Update Stock");
        System.out.println("  7.  Add to Cart");
        System.out.println("  8.  View Cart");
        System.out.println("  9.  Remove from Cart");
        System.out.println("  10. Update Cart Quantity");
        System.out.println("  11. Clear Cart");
        System.out.println("  12. Undo Last Cart Addition");
        System.out.println("  13. Checkout");
        System.out.println("  14. Exit");
        System.out.println("15. Search By Price");
        System.out.println("========================================");
    }
    public static void main(String[] args) {
        inventory.loadFromFile(FileName);
        int choice = -1;

        while (choice != 14) {
            displayMenu();
            System.out.print("Enter choice: ");
            try { 
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Invalid input, Please enter a number between 1 - 14\n");
                continue;
            }

            switch (choice) {
                case 1: //displayAll
                    inventory.displayAll();
                    break;

                case 2: {//searchID
                    System.out.print("Please enter product ID: ");
                    try {
                        int id = Integer.parseInt(input.nextLine().trim());
                        Product p = inventory.searchByID(id);
                        if (p == null){
                            System.out.println("Product ID " + id + " was not found\n");
                        } else {
                            System.out.printf("ID: %d | Name: %s | Price: RM%.2f | Stock: %d%n",
                            p.getId(), p.getName(), p.getPrice(), p.getStock());
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Invalid ID, Please enter a number\n");
                    }
                    break;
                }

                case 3: {//searchName
                    System.out.print("Please enter product name: ");
                    String name = input.nextLine().trim();
                    java.util.ArrayList<Product> result = inventory.searchByName(name);
                    if (result.isEmpty()){
                        System.out.println("No products found\n");
                    } else {
                        for (Product p : result) {
                            System.out.printf("ID: %d | Name: %s | Price: RM%.2f | Stock: %d%n",
                            p.getId(), p.getName(), p.getPrice(), p.getStock());
                        }
                    }
                    break;
                }
                
                case 4: {//addProduct
                    try{
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(input.nextLine().trim());
                        System.out.print("Enter name: ");
                        String name = input.nextLine().trim();
                        System.out.print("Enter Price: ");
                        double price = Double.parseDouble(input.nextLine().trim());
                        System.out.print("Enter stock: ");
                        int stock = Integer.parseInt(input.nextLine().trim());
                        inventory.addProduct(new Product(id, name, price, stock));
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input, only numbers can be used\n");
                    }
                    break;
                }

                case 5: {//removeProduct
                    try{
                        System.out.print("Enter ID of product to remove: ");
                        int id = Integer.parseInt(input.nextLine().trim());
                        inventory.removeProduct(id);
                    } catch (NumberFormatException e){
                        System.out.println("Invalid ID, only numbers can be used\n");
                    }
                    break;
                }

                case 6: {//updateStock
                    try{
                        System.out.print("Enter product ID: ");
                        int id = Integer.parseInt(input.nextLine().trim());
                        System.out.print("Enter new stock: ");
                        int stock = Integer.parseInt(input.nextLine().trim());
                        inventory.updateStock(id, stock);
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input, please enter a number\n");
                    }
                    break;
                }

                case 7: {//addToCart
                    try{
                        System.out.print("Enter product ID: ");
                        int id = Integer.parseInt(input.nextLine().trim());
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(input.nextLine().trim());
                        

                        if (!inventory.isAvailable(id, qty)){
                            System.out.println("Product not found or Insufficient stock\n");
                            break;
                        }

                        Product p = inventory.getProductById(id);
                        p.setStock(p.getStock()-qty);
                        cart.addItem(p, qty);
                        LinkedListStack.push(new LinkedListStack.CartAction(id, qty));

                        System.out.println("Current Cart:");
                        cart.displayCart();
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input, please enter a number\n");
                    }
                    break;
                }

                case 8: {//viewCart
                    cart.displayCart();
                    break;
                    }

                case 9: {//removeFromCart
                    System.out.println("Current Items in cart: ");
                    cart.displayCart();
                    try{
                        System.out.print("Enter product ID to be removed from cart: ");
                        int id = Integer.parseInt(input.nextLine().trim());
                        CartNode removed = cart.removeItem(id);
                        if (removed == null) {
                            System.out.println("Product was not found in cart\n");
                        break;
                        }

                        Product p = inventory.getProductById(id);
                        if (p != null) {
                            p.setStock(p.getStock() + removed.quantity);
                        }
                        System.out.println("Removed " + removed.product.getName() + " from the cart\n");
                    } catch (NumberFormatException e){
                        System.out.println("Invalid ID, please enter a number\n");
                    }                    
                    break;
                }
                

                case 10: {//updateCartQty
                    try{
                        System.out.print("Please enter product ID: ");
                        int id = Integer.parseInt(input.nextLine().trim());
                        System.out.print("Enter new quantity: ");
                        int newQty = Integer.parseInt(input.nextLine().trim());

                        CartNode node = cart.findItem(id);
                        if (node == null) {
                            System.out.println("Product not in cart\n");
                            break;
                        }

                        Product p = inventory.getProductById(id);
                        int diff = newQty - node.quantity;

                        if (diff > 0 && p.getStock() < diff){
                            System.out.println("Not enough stock\n");
                            break;
                        }

                        p.setStock(p.getStock() - diff);
                        cart.updateQuantity(id, newQty);
                        System.out.println("Quantity updated\n");
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input, please enter a number\n");
                    }
                    break;
                }

                case 11: {//clearCart
                    if (cart.isEmpty()){
                        System.out.println("Cart is already empty\n");
                        break;
                    }

                    CartNode current = cart.getHead();
                    while (current != null) {
                        Product p = inventory.getProductById(current.product.getId());
                        if (p != null) p.setStock(p.getStock() + current.quantity);
                        current = current.next;
                    }
                    cart.clear();
                    LinkedListStack.clear();
                    System.out.println("Cart cleared, Stock restored\n");
                    break;
                }

                case 12: {//undoLastAddition
                    if (LinkedListStack.isEmpty()){
                        System.out.println("Nothing to undo\n");
                        break;
                    }

                    LinkedListStack.CartAction action = LinkedListStack.pop(); //pop from stack
                    CartNode removed = cart.removeItem(action.productId);      // remove from cart

                    if (removed != null){
                        Product p = inventory.getProductById(action.productId);
                        if(p != null){
                            p.setStock(p.getStock() + action.quantity);
                            System.out.println("Undo successful, stock restored\n");
                        }
                    } else {
                        System.out.println("Undo failed, item was not found in cart\n");
                    }
                    break;
                    }

                case 13: { //checkout
                    if (cart.isEmpty()){
                        System.out.println("Cart is empty\n");
                        break;
                    }

                    cart.displayCart();
                    cart.clear();
                    LinkedListStack.clear();

                    System.out.println("Save inventory to file? (yes/no): ");
                    String answer = input.nextLine().trim().toLowerCase();
                    if (answer.equals("yes") || answer.equals("y")){
                        inventory.saveToFile(FileName);
                    }
                    System.out.println("Checkout complete\n");
                    break;
                    }
                    

                case 14: {
                    if (!cart.isEmpty()){
                        CartNode current = cart.getHead();
                        while (current != null) {
                            Product p = inventory.getProductById(current.product.getId());
                            if (p != null){
                                p.setStock(p.getStock() + current.quantity);
                            }
                            current = current.next;
                        }
                    }
                    inventory.saveToFile(FileName);
                    System.out.println("Thank you");
                    input.close();
                    break;
                }

                case 15: {//searchprice
                    
                    System.out.print("Please enter product price: ");
                    double price = Double.parseDouble(input.nextLine().trim());
                    java.util.ArrayList<Product> result = inventory.searchByPrice(price);
                    if (result.isEmpty()){
                        System.out.println("No products found\n");
                    } else {
                        for (Product p : result) {
                            System.out.printf("ID: %d | Name: %s | Price: RM%.2f | Stock: %d%n",
                            p.getId(), p.getName(), p.getPrice(), p.getStock());
                        }
                    }
                    break;
                }

                default:
                    System.out.println("Invalid Option\n");
                    break;
            }
        }
    }
}