import java.util.Scanner;

public class T11Q5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter numbers: ");
        int num = input.nextInt();

        printDigit(num);
        input.close();
    }

    public static void printDigit(int n){
        if (n < 10){
            System.out.print(n);
        } else {
            printDigit(n/10);
            System.out.print(" " + n % 10);
        }
    }
}
