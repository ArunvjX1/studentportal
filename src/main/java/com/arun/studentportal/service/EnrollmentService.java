package com.arun.studentportal.service;

import com.arun.studentportal.entity.Enrollment;
import com.arun.studentportal.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    public List<Enrollment> list() {
        return enrollmentRepository.findAll();
    }
}