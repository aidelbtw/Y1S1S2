import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {    //adding searchingID/Name, removing, updating stock, displaying inventory, use arraylist
    private ArrayList<Product> inventory;

    public InventoryManager(){
        inventory = new ArrayList<Product>();
    }

    public void loadFromFile(String filename){
        try{
            Scanner inputStream = new Scanner(new FileInputStream(filename));
            while (inputStream.hasNextLine()){
                String line = inputStream.nextLine().trim();

                if (line.isEmpty()) continue;

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                int stock = Integer.parseInt(parts[3].trim());

                inventory.add(new Product(id, name, price, stock));
            }
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println("File was not found");
        }
    }

    public void saveToFile(String filename){
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(filename));

            for (Product p : inventory){
                outputStream.println(p.toString());
            }
            outputStream.close();
            System.out.println("Inventory saved to " + filename);
        } catch (IOException e){
            System.out.println("Error saving to file");
        }
    }

    public void addProduct(Product p){
        if (searchByID(p.getId()) != null){
            System.out.println("Error : Product already exists");
            return;
        }
        inventory.add(p);
        System.out.println(p.getName() + " successfully added\n");
    }

    public void removeProduct(int id){
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).getId() == id){
                System.out.println("Succesfully removed " + inventory.get(i).getName() + "\n");
                inventory.remove(i);
                return;
            }
        }
        System.out.println("Product ID " + id + " was not found");
    }

    public Product searchByID(int id){
        for (Product p : inventory){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Product> searchByPrice(double price){
        ArrayList<Product> result = new ArrayList<>();
        for(Product p : inventory){
            if(p.getPrice() == price){
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Product> searchByName(String name){
        ArrayList<Product> result = new ArrayList<Product>();
        String search = name.toLowerCase();

        for (Product p : inventory){
            if (p.getName().toLowerCase().contains(search)){
                result.add(p);
            }
        }
        return result;
    }

    public void updateStock(int id, int newStock){
        Product p = searchByID(id);
        if (p == null){
            System.out.println("Product ID " + id + " was not found");
            return;
        }
        p.setStock(newStock);
        System.out.println("Stock of " + p.getName() + " was successfully updated to " +newStock);
    }

    public void displayAll(){
        if (inventory.isEmpty()){
            System.out.println("Inventory is empty");
            return;
        }

        for (Product p : inventory){
            System.out.printf("ID: %-6d | Name: %s | Price: RM%.2f | Stock: %d%n",
                p.getId(), p.getName(), p.getPrice(), p.getStock());
        }
        System.out.println("Total products: " + inventory.size());
    }

    public Product getProductById(int id){
        return searchByID(id);
    }

    public boolean isAvailable(int id, int requestedQty){
        Product p = searchByID(id);
        return (p != null && p.getStock() >= requestedQty);
    }
}