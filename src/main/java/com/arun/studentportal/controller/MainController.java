package com.arun.studentportal.controller;

import com.arun.studentportal.entity.Account;
import com.arun.studentportal.service.AccountService;
import com.arun.studentportal.service.CourseService;
import com.arun.studentportal.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MainController {

  @Autowired
  AccountService accountService;

  @Autowired
  CourseService courseService;

  @Autowired
  EnrollmentService enrollmentService;

  @GetMapping("/login")
  public String login(Model model) {
    Account userDto = new Account();
    model.addAttribute("user", userDto);
    return "login";
  }
  @PostMapping("/login")
  public String loginProcess(@RequestBody Account login) {
    return "login";
  }


  @GetMapping("/")
  public String home() {
    return "home";
  }


  @GetMapping("/signup")
  public String showRegistrationForm(Model model) {
    model.addAttribute("student", new Account());
    return "signup";
  }

  @PostMapping("/signup")
  public String signupProcess(@ModelAttribute Account student) {
    accountService.signupStudent(student);
    return "redirect:/login";
  }

  @GetMapping("/profile")
  public String profile(Model model) {
    model.addAttribute("student", new Account());

    return "profile";
  }

  @PostMapping("/profile")
  public String profileProcess(@ModelAttribute Account student,Principal principal) {
    accountService.updateStudent("c1", student);
    return "redirect:/login";
  }


  @GetMapping("/courses")
  public String courses(Model model) {

    model.addAttribute("list", courseService.list());
    return "courses";
  }

  @GetMapping("/graduation")
  public String graduation(Model model) {
    return "graduation";
  }

  @GetMapping("/enroll")
  public String enroll(Model model, Principal principal) {
    model.addAttribute("list", enrollmentService.list("c1"));
    return "enroll";
  }

  @GetMapping("/enrollcourse/{courseId}")
  public String enroll(Model model, Principal principal, @PathVariable String courseId) {
    model.addAttribute("list", enrollmentService.enrollCourse("c1", courseId));
    return "redirect:/enroll";
  }



}