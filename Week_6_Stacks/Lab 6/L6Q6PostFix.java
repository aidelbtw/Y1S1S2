import java.util.Stack;

public class L6Q6PostFix {
    public static void main(String[] args) {
        String expression = "3 4 + 2 /"; // means (3 + 4) * 2 = 14
        System.out.println("Result: " + evaluatePostfix(expression));
    }

    public static int evaluatePostfix(String expr) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expr.trim().split("\\s+"); // split on whitespace

        for (String token : tokens) {
            if (isOperator(token)) {
                // second operand was pushed last, so it comes off first
                int b = stack.pop();
                int a = stack.pop();
                int result = applyOp(a, b, token);
                stack.push(result);
            } else {
                // it's a number
                stack.push(Boolean.parseBoolean(token));
            }
        }

        return stack.pop(); // final answer
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
               token.equals("*") || token.equals("/");
    }

    private static int applyOp(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}