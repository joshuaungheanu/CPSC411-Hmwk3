package com.example.Hmwk3.model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class StudentDB extends Activity {

    File database;
    private static SQLiteDatabase sqlDB;

    public StudentDB(Context context) {
        database = context.getDatabasePath("student.db");
        sqlDB = SQLiteDatabase.openOrCreateDatabase(database, null);

        String sqlQ = "CREATE TABLE IF NOT EXISTS STUDENT (FirstName Text, LastName Text, Cwid Text)";
        sqlDB.execSQL(sqlQ);

        sqlQ = "CREATE TABLE IF NOT EXISTS COURSES (Cwid Text, Course Text, Grade Text)";
        sqlDB.execSQL(sqlQ);

    }

    // Retrieves the database student list
    public static ArrayList<Student> getStudentList() {
        ArrayList<Student> stuList = new ArrayList<>();
        Student stu;
        ArrayList<CourseEnrollment> courses = new ArrayList<>();
        Cursor curr = sqlDB.query("STUDENT", null, null, null, null, null, null);
        if(curr.getCount() > 0) {
            while(curr.moveToNext()) {
                stu = new Student(curr.getString(0), curr.getString(1), curr.getString(2));

                Cursor curr2 = sqlDB.query("COURSES", null, "CWID=?", new String[]{curr.getString(2)}, null, null, null);

                if(curr2.getCount() > 0) {
                    courses = new ArrayList<>();
                    while(curr2.moveToNext()) {
                        courses.add(new CourseEnrollment(curr2.getString(1), curr2.getString(2)));
                    }
                }
            stu.setCourses(courses);
            stuList.add(stu);
            }
        }
        return stuList;
    }

    // Add full list of students to the database
    public static void addListToDB(ArrayList<Student> studentList) {
        for(Student student:studentList){
            sqlDB.execSQL("INSERT INTO STUDENT VALUES (?, ?, ?)", new String[]{student.getFirstName(), student.getLastName(), student.getCwid()});

            for(CourseEnrollment course:student.getCourses()) {
                sqlDB.execSQL("INSERT INTO COURSES VALUES (?, ?, ?)", new String[]{student.getCwid(), course.getCourseId(), course.getGrade()});
            }
        }
    }

    // Update student first name or last name based on cwid, CWID should not be allowed to be updated
    public static void updateDB(Student updatedStu) {
        ContentValues cv = new ContentValues();
        cv.put("FirstName", updatedStu.getFirstName());
        cv.put("LastName", updatedStu.getLastName());
        sqlDB.update("STUDENT", cv, "Cwid=?", new String[]{updatedStu.getCwid()});
    }
}
