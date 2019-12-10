package com.example.Hmwk3.model;

import java.util.ArrayList;

public class Student {
    protected String mFirstName;
    protected String mLastName;
    protected String mCWID;

    protected ArrayList<CourseEnrollment> mCourses;

    public Student(String fname, String lname, String cwid) {
        mFirstName = fname;
        mLastName = lname;
        mCWID = cwid;
    }

    public String getFirstName() {
        return mFirstName;
    }
    public String getLastName() {
        return mLastName;
    }
    public String getCwid() {
        return mCWID;
    }
    public ArrayList<CourseEnrollment> getCourses() {
        return mCourses;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }
    public void setLastName(String lastName) {
        mLastName = lastName;
    }
    public void setCwid(String sCwid) {
        mCWID = sCwid;
    }
    public void setCourses(ArrayList<CourseEnrollment> courses) {
        mCourses = courses;
    }

}
