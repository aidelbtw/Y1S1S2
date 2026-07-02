public interface Course {
    String getCourseCode();                //To return a unique code for a certain course
                                        //Paramaters - 

    String getCourseTitle();            //To return a title for a certain course
                                        //Parameters -

    double calculateTotalWorkload();    //A method To calculate the total workload hours of a specific course for each semester
                                        //Parameters -

    String getInstructorName();         //Method to return a designated Instructor's name for a specific course
                                        //Parameters -

    void printCourseDetails();        //A method to return relevant details for a certain course
}
