import java.util.Scanner;

public class L6Q2TestIntMyStack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter an integer value ");
        int n = input.nextInt();

        L6Q1MyStack<Integer> stack = new L6Q1MyStack();

        for (int i = 1; i <= n ; i++){
            stack.push(i);
        }

        System.out.println("Size of stack: " + stack.getSize());

        System.out.println("Contents (popped in order):");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        input.close();
    }
}