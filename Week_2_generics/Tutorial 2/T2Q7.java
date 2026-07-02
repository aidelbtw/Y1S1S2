import java.util.ArrayList;

public class T2Q7 {
    public static void allTransportation(ArrayList<?> vehicle, ArrayList<?> transportation){
        System.out.println("Vehicle :");
        for (Object v : vehicle){
            System.out.print(v + " ");
        }
        System.out.println();

        System.out.println("Transportation: ");
        for(Object t : transportation){
            System.out.println(t + " ");
        }
        System.out.println();
    }

    public static void displayList(ArrayList<?> list){
        for(Object l : list){
            System.out.println(l + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<String> vehicle = new ArrayList<>();
        vehicle.add("Proton");
        vehicle.add("Perodua");

        ArrayList<Object> transportation = new ArrayList<>();
        transportation.add("Plane");
        transportation.add("Train");

        System.out.println("All Transportation: ");
        allTransportation(vehicle, transportation);

        ArrayList<Integer> numOfCars = new ArrayList<>();
        numOfCars.add(10);
        numOfCars.add(20);

        ArrayList<Double> milesPerHour = new ArrayList<>();
        milesPerHour.add(60.5);
        milesPerHour.add(90.3);

        System.out.println("---Display List---");
        System.out.println("Number Of Cars: ");
        displayList(numOfCars);

        System.out.println("Miles Per Hour");
        displayList(milesPerHour);
    }
}
