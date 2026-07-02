import java.util.Scanner;

public class L6Q4PalindromeChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a string (max 15 characters): ");
        String str = input.nextLine();

        if (str.length() > 15){
            System.out.println("String maximum size is 15 characters");
            input.close();
            return;
        }

        L6Q1MyStack<Character> stack = new L6Q1MyStack<>();

        for (int i = 0; i < str.length(); i++){
            stack.push(str.charAt(i));
        }

        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }

        if (str.equalsIgnoreCase(reversed.toString())) {
            System.out.println("\"" + str + "\" is a palindrome");
        } else {
            System.out.println("\"" + str + "\" is not a palindrome");
        }
        input.close();
    }
}
// Enter into str, str push into stack, stack pops into reversed, reversed checks against str if same