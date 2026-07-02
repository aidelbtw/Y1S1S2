import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class L8Q2palindrome {
 
    public static void main(String[] args) {
        System.out.print("Enter any string: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();   // madam
        Queue<Character> queue = new LinkedList<>();
        for (int i = input.length() - 1; i >= 0; i--) {
            queue.add(input.charAt(i));
        }
        String reverseString = "";
        while (!queue.isEmpty()) {
            reverseString = reverseString + queue.remove();
        }
        if (input.equals(reverseString)) {
            System.out.println("The input \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The input \"" + input + "\" is not a palindrome.");
        }
    }
}  