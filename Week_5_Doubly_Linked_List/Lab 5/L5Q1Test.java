public class L5Q1Test {
    public static void main(String[] args) {
        L5Q1SList<String> list = new L5Q1SList();
        list.appendEnd("Linked list");
        list.appendEnd("is");
        list.appendEnd("easy");

        System.out.println("Current list");
        list.display();

        String removed = list.removeInitial();
        System.out.println("List after removal: ");
        list.display();

        System.out.println("Contains difficult?: " + list.contains("difficult"));
        list.clear();
    }
}
