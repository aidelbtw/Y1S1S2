public class L4Q3TestLinkedList {
    public static void main(String[] args) {
        L4Q2MyLinkedList<String> list = new L4Q2MyLinkedList<>();
        System.out.println("1. Append a,b,c,d,e");
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");
        list.addLast("e");

        list.print();

        list.reverse();

        System.out.println(list.size());

        System.out.println("First: " + list.getFirst() );
        System.out.println("Last: " + list.getLast());

        System.out.println(list.remove(2));
        list.print();

        System.out.println(list.get(1));
        System.out.println(list.get(2));

        System.out.println(list.contains("c"));

        list.set(0, "j");
        list.set(1, "a");
        list.set(2, "v");
        list.set(3, "a");

        list.print();

        System.out.println(list.getMiddleValue());

    }
}
