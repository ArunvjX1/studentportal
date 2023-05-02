package com.arun.studentportal.service;

import com.arun.studentportal.entity.Enrollment;
import com.arun.studentportal.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    public List<Enrollment> list(String studentID) {
        return enrollmentRepository.findByAccountId(studentID);
    }

    public Enrollment enrollCourse(String studentID, String courseId, float fee) {
        Enrollment enrollment= new Enrollment();
        enrollment.setCourseId(Integer.valueOf(courseId));
        enrollment.setAccountId(studentID);
        enrollmentRepository.save(enrollment);

        //------
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, Object> map= new HashMap<>();

        map.put("amount", fee);
        map.put("dueDate", "2023-11-06");
        map.put("type", "TUITION_FEES");

        Map<String, String> accountMap = new HashMap();
        accountMap.put("studentId", studentID);

        map.put("account", accountMap);

        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8081/accounts/invoices", request , String.class );
        //------
        return enrollment;
    }
}