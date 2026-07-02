public class T2Q5<A,B> {
    private A first;
    private B second;

    public T2Q5(A first, B second){
        this.first = first;
        this.second = second;
    }

    public A getFirst(){
        return first;
    }

    public B getSecond(){
        return second;
    }

    @Override
    public String toString(){
        return first + ", " + second;
    }

    public static void main(String[] args) {
        T2Q5<String, Integer> sideShape = new T2Q5("Triangle", 50);
        System.out.println("Side Shape: " + sideShape);
        System.out.println("First: " + sideShape.getFirst());
        System.out.println("Second: " + sideShape.getSecond());

        T2Q5<Double, Double> points = new T2Q5(4.5, 9.8);
        System.out.println("points : " + points);
        System.out.println("First : " + points.getFirst());
        System.out.println("Second : " + points.getSecond());        
    }
    
}