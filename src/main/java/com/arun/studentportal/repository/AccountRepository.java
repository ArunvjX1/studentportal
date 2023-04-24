package com.arun.studentportal.repository;

import com.arun.studentportal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findAccountByStudentId(String studentId);

    public Account findAccountByUsername(String username);
}