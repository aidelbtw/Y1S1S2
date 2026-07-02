//T3Q1 - Dispenser
public class T3Q1dispenser {
    private double cost;
    private int numItems;
    private String type;

    T3Q1dispenser(String type, double cost, int numItems){
        numItems = this.numItems;
        cost = this.cost;
        type = this.type;
    }

    public int getCost(){return cost;}
    public int getCount(){return numItems;}

    public void makeSale(){
        if (numItems > 0){numItems--;}
    }
}