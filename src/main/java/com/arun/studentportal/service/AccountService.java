package com.arun.studentportal.service;

import com.arun.studentportal.entity.Account;
import com.arun.studentportal.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    public void signupStudent(Account signup) {

        Random rand = new Random();

        // Obtain a number between [0 - 1000000].
        int n = rand.nextInt(1000000);

        signup.setStudentId("c"+n);
        accountRepository.save(signup);
    }
}