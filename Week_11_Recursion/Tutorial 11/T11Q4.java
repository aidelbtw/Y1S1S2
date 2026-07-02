import java.util.Scanner;

public class T11Q4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input number: ");
        int num = input.nextInt();

        System.out.println(sum(num));
        input.close();
    }

    public static int sum(int num){
        if (num == 0){
            return 0;
        } else {
            return num + sum(num-1);
        }
    }
}
