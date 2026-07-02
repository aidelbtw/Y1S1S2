public class Main {
    public static void main(String[] args) {
        LectureCourse Lec1 = new LectureCourse("CS101", "Intro to Programming", "Dr. Smith", 3);
        LectureCourse Lec2 = new LectureCourse("CS102", "OOP", "Prof. Lee", 4);
        LabCourse Lab1 = new LabCourse("CS201", "Data Structures Lab", "Prof. Jones", 2.5, 2);
        LabCourse Lab2 = new LabCourse("CS202", "Algorithms Lab", "Dr.Brown", 2.0, 1);

        CourseManager<Course> cm = new CourseManager<>();

        cm.addCourse(Lec1);
        cm.addCourse(Lec2);
        cm.addCourse(Lab1);
        cm.addCourse(Lab2);

        System.out.println("Course with highest total workload: ");
        cm.getCourseWithHighestWorkload().printCourseDetails();
        System.out.println();

        System.out.println("All Courses in Sorted order");
        cm.sortCoursesByWorkload();        
        cm.printAllCourses();

        System.out.println("After removing course CS101");
        cm.removeCourse("CS101");
        cm.printAllCourses();

    }
}
