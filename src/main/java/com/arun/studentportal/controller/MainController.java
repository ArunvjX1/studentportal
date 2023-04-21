package com.arun.studentportal.controller;

import com.arun.studentportal.entity.Account;
import com.arun.studentportal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  AccountService accountService;
  @GetMapping("/login")
  public String login(Model model) {
    Account userDto = new Account();
    model.addAttribute("user", userDto);
    return "login";
  }
//  @PostMapping("/login")
//  public String loginProcess(@RequestBody Account login) {
//    return "login";
//  }
//

  @GetMapping("/")
  public String home() {
    return "home";
  }


  @GetMapping("/signup")
  public String showRegistrationForm(Model model) {
    Account userDto = new Account();
    model.addAttribute("user", userDto);
    return "signup";
  }

  @PostMapping("/signup")
  public String signupProcess(@RequestBody Account signup) {
    accountService.signupStudent(signup);
    return "login";
  }

  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }

  @GetMapping("/courses")
  public String courses() {
    return "courses";
  }

  @GetMapping("/graduation")
  public String graduation() {
    return "graduation";
  }

  @GetMapping("/enroll")
  public String enroll() {
    return "enroll";
  }

}