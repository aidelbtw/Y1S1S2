public class L8Q1TestMyQueue {
    public static void main(String[] args) {
        String[] initialFruits = {"Durian", "Blueberry"};
        L8Q1MyQueue<String> fruitQ = new L8Q1MyQueue<>(initialFruits);
        
        fruitQ.enqueue("Apple");
        fruitQ.enqueue("Orange");
        fruitQ.enqueue("Grape");
        fruitQ.enqueue("Cherry");
        
        System.out.println("Queue: " + fruitQ);

        System.out.println("Top Item: " + fruitQ.peek());

        System.out.println("Queue size: " + fruitQ.getSize());

        String removed = fruitQ.dequeue();
        System.out.println("Removed item: " + removed);
        System.out.println("Queue after removal: " + fruitQ);

        System.out.println("Contains Cherry? " + fruitQ.contains("Cherry"));

        System.out.println("Contains Durian? " + fruitQ.contains("Durian"));

        System.out.println("Displaying queue elements:");
        L8Q1MyQueue<String> tempQ = new L8Q1MyQueue<>();
        for (int i = 0 ; i < fruitQ.getSize(); i++){
            tempQ.enqueue(fruitQ.getElement(i));
        }
        while (!tempQ.isEmpty()) {
            System.out.println(tempQ.dequeue());
        }
    }
}
