package com.arun.studentportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue
    private int id;
    private int courseId;
    private int accountId;
}
