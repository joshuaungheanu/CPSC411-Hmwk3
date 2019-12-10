package com.example.Hmwk3.model;

public class CourseEnrollment {

    protected String mCourseId;
    protected String mGrade;

    public CourseEnrollment(String crsId, String crsGrade) {
        mCourseId = crsId;
        mGrade = crsGrade;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public void setCourseId(String courseId) {
        mCourseId = courseId;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }
}
