public class T2Q2 {

    public static <T> void listAll(T[] array){
        for (T item : array){
            System.out.print(item + " ");
        }
    }

    public static void main(String[] args) {
        Integer[] arrIntegers = {1,2,3,4,5};
        String[] arrStrings = {"Jane", "Tom", "Bob"};
        Character[] arrAlphabet = {'a', 'b', 'c'};

        System.out.println("Integer Array: "); 
        listAll(arrIntegers);

        System.out.println("\nString Array: "); 
        listAll(arrStrings);        

        System.out.println("\nCharacter Array: "); 
        listAll(arrAlphabet);
    }
}
