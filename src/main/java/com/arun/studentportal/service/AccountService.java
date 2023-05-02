package com.arun.studentportal.service;

import com.arun.studentportal.entity.Account;
import com.arun.studentportal.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Account getStudentByUsername(String username){
        return accountRepository.findAccountByUsername(username);
    }
    public Account getStudent(String studentId){
        Account student = accountRepository.findAccountByStudentId(studentId);
        return student;
    }
    public void signupStudent(Account signup) {

        Random rand = new Random();

        // Obtain a number between [0 - 1000000].
        int n = rand.nextInt(1000000);
        signup.setPassword(passwordEncoder.encode(signup.getPassword()));
        signup.setStudentId("c"+n);
        accountRepository.save(signup);

        //------
        {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            Map<String, Object> map= new HashMap<>();

            map.put("studentId", "c"+n);


            HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8081/accounts", request , String.class );

        }
        //------

        //------
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            Map<String, Object> map= new HashMap<>();

            map.put("studentId", "c"+n);

            HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8081/api/register", request , String.class );

        }
        //------

    }

    public void updateStudent(String studentId, Account signup) {
       Account student = accountRepository.findAccountByStudentId(studentId);
        student.setPassword(passwordEncoder.encode(signup.getPassword()));
        student.setFullName(signup.getFullName());
        student.setUsername(signup.getUsername());
        accountRepository.save(student);
    }

    public boolean isGraduate(String studentId) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, Object> map= new HashMap<>();


        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8081/accounts/student/"+studentId, request , String.class );

        ObjectMapper objectMapper = new ObjectMapper();
        Map jsonpObjectMap = objectMapper.readValue(response.getBody(), Map.class);
        boolean isNotGraduate = (boolean) jsonpObjectMap.get("hasOutstandingBalance");
        return isNotGraduate;
    }
}