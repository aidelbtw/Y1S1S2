public class LabCourse implements Course {
    private String courseCode;
    private String courseTitle;
    private String instructorName;
    private double labHoursPerWeek;
    private int numberOfSessions;

    public LabCourse(String courseCode, String courseTitle, String instructorName, double labHoursPerWeek, int numberOfSessions) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.instructorName = instructorName;
        this.labHoursPerWeek = labHoursPerWeek;
        this.numberOfSessions = numberOfSessions;
    }

    @Override
    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String getCourseTitle() {
        return courseTitle;
    }

    @Override
    public String getInstructorName(){
        return instructorName;
    }

    public double calculateTotalWorkload(){
        return labHoursPerWeek*numberOfSessions*14.0;
    }

    public void printCourseDetails(){
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Title: " + courseTitle);
        System.out.println("Instructor Name: " + instructorName);
        System.out.println("Lab Hours Per Week: " + labHoursPerWeek);
        System.out.println("Number of Sessions " + numberOfSessions);
        System.out.println("Total Workload: " + calculateTotalWorkload() + " hours");
    }
}