import java.util.*;

/*
 * A COLLECTION OF RECURSION EXAMPLES
 * ------------------------------------------------------
 * Each method below is a classic recursion pattern.
 * Run main() to see them all in action.
 *
 * General recursion template to keep in mind for ALL of these:
 *
 *   returnType recurse(params) {
 *       if (baseCase) {          // <-- stops infinite recursion
 *           return baseValue;
 *       }
 *       // do a little work, then call yourself with a SMALLER problem
 *       return combine(work, recurse(smallerParams));
 *   }
 */
public class RecursionExamples {

    // ===================== 1. FACTORIAL =====================
    // n! = n * (n-1) * (n-2) * ... * 1     (0! = 1)
    public static long factorial(int n) {
        if (n <= 1) {              // base case
            return 1;
        }
        return n * factorial(n - 1); // recursive case: smaller problem (n-1)
    }

    // ===================== 2. FIBONACCI =====================
    // fib(n) = fib(n-1) + fib(n-2), fib(0)=0, fib(1)=1
    // NOTE: this "naive" version is O(2^n) -- slow for large n, shown below with memoization too
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n; // base cases: fib(0)=0, fib(1)=1
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Fibonacci with memoization (much faster: O(n))
    public static long fibonacciMemo(int n, Map<Integer, Long> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n); // already computed, reuse it
        long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // ===================== 3. REVERSE A STRING =====================
    public static String reverseString(String s) {
        if (s.length() <= 1) {        // base case: empty or single char is already "reversed"
            return s;
        }
        // take the first char, move it to the end AFTER reversing the rest
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    // ===================== 4. REVERSE AN INTEGER (digit by digit) =====================
    // e.g. reverseInt(1234) -> 4321
    public static int reverseInt(int n) {
        return reverseIntHelper(n, 0);
    }
    private static int reverseIntHelper(int n, int reversedSoFar) {
        if (n == 0) {                        // base case: no digits left
            return reversedSoFar;
        }
        int lastDigit = n % 10;
        reversedSoFar = reversedSoFar * 10 + lastDigit;
        return reverseIntHelper(n / 10, reversedSoFar); // shrink n by removing last digit
    }

    // ===================== 5. SUM OF DIGITS =====================
    // e.g. sumDigits(1234) -> 1+2+3+4 = 10
    public static int sumDigits(int n) {
        if (n == 0) {
            return 0;
        }
        return (n % 10) + sumDigits(n / 10);
    }

    // ===================== 6. SUM OF AN ARRAY =====================
    public static int sumArray(int[] arr, int index) {
        if (index == arr.length) {   // base case: ran off the end
            return 0;
        }
        return arr[index] + sumArray(arr, index + 1);
    }

    // ===================== 7. POWER (exponentiation) =====================
    // base^exp, e.g. power(2, 10) -> 1024
    public static long power(int base, int exp) {
        if (exp == 0) {
            return 1;             // anything^0 = 1
        }
        return base * power(base, exp - 1);
    }

    // Faster version using "divide and conquer": O(log n) instead of O(n)
    public static long powerFast(int base, int exp) {
        if (exp == 0) return 1;
        long half = powerFast(base, exp / 2);
        if (exp % 2 == 0) {
            return half * half;
        } else {
            return half * half * base;
        }
    }

    // ===================== 8. CHECK PALINDROME =====================
    public static boolean isPalindrome(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }
    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) {                 // base case: pointers met/crossed in the middle
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;                     // mismatch found, no need to go further
        }
        return isPalindromeHelper(s, left + 1, right - 1); // shrink inward
    }

    // ===================== 9. GREATEST COMMON DIVISOR (Euclidean algorithm) =====================
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;                 // base case
        }
        return gcd(b, a % b);         // recursive case
    }

    // ===================== 10. BINARY SEARCH =====================
    // array MUST be sorted; returns index of target, or -1 if not found
    public static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1;                     // base case: search space is empty, not found
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            return mid;                    // base case: found it
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high); // search right half
        } else {
            return binarySearch(arr, target, low, mid - 1);  // search left half
        }
    }

    // ===================== 11. COUNT OCCURRENCES OF A CHARACTER =====================
    public static int countChar(String s, char target, int index) {
        if (index == s.length()) {
            return 0;
        }
        int count = (s.charAt(index) == target) ? 1 : 0;
        return count + countChar(s, target, index + 1);
    }

    // ===================== 12. PRINT ALL SUBSETS (POWER SET) =====================
    // For a set of n elements, there are 2^n subsets.
    // Classic "include or exclude" recursion pattern.
    public static void printSubsets(int[] arr, int index, List<Integer> current) {
        if (index == arr.length) {
            System.out.println(current);   // base case: made a decision for every element
            return;
        }
        // choice 1: don't include arr[index]
        printSubsets(arr, index + 1, current);

        // choice 2: include arr[index]
        current.add(arr[index]);
        printSubsets(arr, index + 1, current);
        current.remove(current.size() - 1); // backtrack: undo the choice before returning
    }

    // ===================== 13. PERMUTATIONS OF A STRING =====================
    public static void printPermutations(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);    // base case: nothing left to arrange
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            // pick each character in turn, remove it from "remaining", recurse
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            printPermutations(prefix + remaining.charAt(i), newRemaining);
        }
    }

    // ===================== 14. TOWER OF HANOI (moves counter, simplified) =====================
    public static int hanoiMoves(int n) {
        if (n == 0) {
            return 0;
        }
        // move n-1 disks, move disk n, move n-1 disks again
        return hanoiMoves(n - 1) + 1 + hanoiMoves(n - 1);
    }

    // ===================== 15. LINKED LIST LENGTH (recursive) =====================
    static class Node {
        int data;
        Node next;
        Node(int data, Node next) { this.data = data; this.next = next; }
    }
    public static int listLength(Node head) {
        if (head == null) {
            return 0;                       // base case: empty list
        }
        return 1 + listLength(head.next);
    }

    // ===================== MAIN: DEMO EVERYTHING =====================
    public static void main(String[] args) {
        System.out.println("1. Factorial(5) = " + factorial(5));

        System.out.println("2. Fibonacci(10) = " + fibonacci(10));
        System.out.println("   FibonacciMemo(50) = " + fibonacciMemo(50, new HashMap<>())
                + "  <- naive version would take forever at n=50!");

        System.out.println("3. reverseString(\"hello\") = " + reverseString("hello"));

        System.out.println("4. reverseInt(1234) = " + reverseInt(1234));

        System.out.println("5. sumDigits(1234) = " + sumDigits(1234));

        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("6. sumArray({1,2,3,4,5}) = " + sumArray(nums, 0));

        System.out.println("7. power(2, 10) = " + power(2, 10));
        System.out.println("   powerFast(2, 10) = " + powerFast(2, 10));

        System.out.println("8. isPalindrome(\"racecar\") = " + isPalindrome("racecar"));
        System.out.println("   isPalindrome(\"hello\") = " + isPalindrome("hello"));

        System.out.println("9. gcd(48, 18) = " + gcd(48, 18));

        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
        System.out.println("10. binarySearch for 23 -> index " + binarySearch(sorted, 23, 0, sorted.length - 1));
        System.out.println("    binarySearch for 99 -> index " + binarySearch(sorted, 99, 0, sorted.length - 1));

        System.out.println("11. countChar(\"mississippi\", 's') = " + countChar("mississippi", 's', 0));

        System.out.println("12. printSubsets({1,2,3}):");
        printSubsets(new int[]{1, 2, 3}, 0, new ArrayList<>());

        System.out.println("13. printPermutations(\"abc\"):");
        printPermutations("", "abc");

        System.out.println("14. hanoiMoves(3) = " + hanoiMoves(3) + " moves (formula: 2^n - 1)");

        Node list = new Node(1, new Node(2, new Node(3, new Node(4, null))));
        System.out.println("15. listLength(1->2->3->4) = " + listLength(list));
    }
}