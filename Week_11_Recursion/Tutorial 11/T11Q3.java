import java.util.Scanner;

public class T11Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string to reverse: ");
        String text = input.nextLine();

        System.out.println(reverse(text));
        input.close();
    }

    public static String reverse(String str){
        if (str.length() <= 1){
            return str;
        } else {           // strats from the index of 1 of the str
            return reverse(str.substring(1)) + str.charAt(0);
        }
    }
    //reverse("string") = reverse("tring") + "s"
    //reverse("tring")  = reverse("ring")  + "t"
    //reverse("ring")   = reverse("ing")   + "r"
    //reverse("ing")    = reverse("ng")    + "i"
    //reverse("ng")     = reverse("g")     + "n"
    //reverse("g")      = "g"   <- base case
}
