import java.util.ArrayList;
import java.util.Comparator;

public class CourseManager<T extends Course> {
    ArrayList<T> courses = new ArrayList<>();

    public void addCourse(T Course){
        courses.add(Course);
    }

    public void removeCourse(String courseCode){
        courses.removeIf(c -> c.getCourseCode().equals(courseCode));
    }

    public T getCourseWithHighestWorkload(){
        T highest = null;
        for (T c : courses){
            if(highest == null || highest.calculateTotalWorkload() < c.calculateTotalWorkload()){  //Must be highest.calculate because is not int, its T
                highest = c;
        }
        }
        return highest;
    }

    public void sortCoursesByWorkload(){
        courses.sort(Comparator.comparingDouble(c -> c.calculateTotalWorkload()));

        //Or can be done using 2 for loops to sort
    }


    public void printAllCourses(){
        for(T c : courses){
            c.printCourseDetails();
            System.out.println();
        }
    }
}
