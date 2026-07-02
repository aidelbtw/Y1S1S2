import java.util.Scanner;

public class L5Q2Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L5Q2StudentList<String> students = new L5Q2StudentList<>();

        // Enter student names
        System.out.println("Enter your student name list. Enter 'n' to end.....");
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("n")) {
            students.add(input);
            input = sc.nextLine();
        }

        // Display entered names
        System.out.print("You have entered the following students' name : ");
        students.printList();

        // Display count
        System.out.println("The number of students entered is : " + students.getSize());

        // Rename option
        System.out.println("All the names entered are correct? Enter 'r' to rename the student name, 'n' to proceed.");
        String choice = sc.nextLine().trim();
        if (choice.equalsIgnoreCase("r")) {
            System.out.print("Enter the existing student name that u want to rename : ");
            String oldName = sc.nextLine();
            System.out.print("Enter the new name : ");
            String newName = sc.nextLine();
            students.replace(oldName, newName);
            System.out.print("The new student list is : ");
            students.printList();
        }

        // Remove option
        System.out.println("Do you want to remove any of your student name? Enter 'y' for yes, 'n' to proceed.");
        String removeChoice = sc.nextLine().trim();
        if (removeChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter a student name to remove : ");
            String nameToRemove = sc.nextLine();
            students.removeElement(nameToRemove);
            System.out.println("The number of updated student is :" + students.getSize());
            System.out.print("The updated students list is : ");
            students.printList();
        }

        System.out.println("All student data captured complete. Thank you!");
        sc.close();
    }
}
