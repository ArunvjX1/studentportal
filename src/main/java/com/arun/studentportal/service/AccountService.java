package com.arun.studentportal.service;

import com.arun.studentportal.entity.Account;
import com.arun.studentportal.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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


    }

    public void updateStudent(String studentId, Account signup) {
       Account student = accountRepository.findAccountByStudentId(studentId);
        student.setPassword(passwordEncoder.encode(signup.getPassword()));
        student.setFullName(signup.getFullName());
        student.setUsername(signup.getUsername());
        accountRepository.save(student);
    }
}