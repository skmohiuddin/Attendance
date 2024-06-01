package com.turningpoint.attendance;

public class CourseModel3 {
    // variables for our course
    // name and description.
    private String courseName;
    private String courseDescription;
    private String sendornort;


    // creating constructor for our variables.
    public CourseModel3(String courseName,
                        String courseDescription, String sendornot) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.sendornort = sendornot;



    }

    // creating getter and setter methods.
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

//

    public String getSendornort() {
        return sendornort;
    }

    public void setSendornot(String sendornort) {
        this.sendornort = sendornort;
    }


}
