public class T3Q1cashregister {
    private int cashAvailable;

    public T3Q1cashregister(int cashAvailable){
        cashAvailable = this.cashAvailable;
    }

    public void acceptAmount(int amount){
        cashAvailable += amount;
    }

    public void returnChange(){}

    public void getCashAvailable(){}

    public void setCashAvailable(){}
}
