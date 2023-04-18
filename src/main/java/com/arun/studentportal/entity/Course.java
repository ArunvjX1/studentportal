package com.arun.studentportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id @GeneratedValue
    private int id;
    private String courseName;
    private float courseFee;


    public Course(String courseName, float courseFee) {
        this.courseName = courseName;
        this.courseFee = courseFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(float courseFee) {
        this.courseFee = courseFee;
    }
}
