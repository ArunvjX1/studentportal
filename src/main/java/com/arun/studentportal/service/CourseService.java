package com.arun.studentportal.service;

import com.arun.studentportal.entity.Course;
import com.arun.studentportal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> list() {
       return courseRepository.findAll();
    }

    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }
}