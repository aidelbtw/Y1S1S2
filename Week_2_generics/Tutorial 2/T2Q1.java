public class T2Q1<T> {
    private T t;

    public T2Q1 (){}

    public void add(T t){
        this.t = t;
    }

    public T retrieve(){
        return t;
    }

    public static void main(String[] args) {
        T2Q1<Integer> intContainer = new T2Q1<>();
        T2Q1<String> stringContainer = new T2Q1<>();

        stringContainer.add("Java");
        intContainer.add(50);

        System.out.println("String Container: " + stringContainer.retrieve());
        System.out.println("Integer Container: " + intContainer.retrieve());
    }
}
