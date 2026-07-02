public class L6Q1TestMyStack {
    public static void main(String[] args) {
        L6Q1MyStack<Character> charStack = new L6Q1MyStack<>();
        charStack.push('a');
        charStack.push('b');
        charStack.push('c');

        System.out.println(charStack);

        System.out.println("Is 'b' in stack? " + charStack.search('b'));
        System.out.println("Is 'k' in stack? " + charStack.search('k'));

        L6Q1MyStack<Integer> intStack = new L6Q1MyStack<>();
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        
        System.out.println(intStack);

        System.out.println("Is 6 in stack? " + intStack.search(6));
    }
}
