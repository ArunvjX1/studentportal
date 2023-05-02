package com.arun.studentportal.controller;

import com.arun.studentportal.entity.Account;
import com.arun.studentportal.entity.Course;
import com.arun.studentportal.service.AccountService;
import com.arun.studentportal.service.CourseService;
import com.arun.studentportal.service.EnrollmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
  @PostMapping({"/login", "/"})
  public String loginProcess(@RequestBody Account login) {
    return "login";
  }


  @GetMapping("/home")
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
  public String profile(Model model,Principal principal) {
    Account studentByUsername = accountService.getStudentByUsername(principal.getName());
    model.addAttribute("student", accountService.getStudent(studentByUsername.getStudentId()));

    return "profile";
  }

  @PostMapping("/profile")
  public String profileProcess(@ModelAttribute Account student,Principal principal) {
    Account studentByUsername = accountService.getStudentByUsername(principal.getName());
    accountService.updateStudent(studentByUsername.getStudentId(), student);
    return "redirect:/profile";
  }


  @GetMapping("/courses")
  public String courses(Model model) {

    model.addAttribute("list", courseService.list());
    return "courses";
  }

  @GetMapping("/graduation")
  public String graduation(Model model,Principal principal) throws JsonProcessingException {
    Account studentByUsername = accountService.getStudentByUsername(principal.getName());

    model.addAttribute("isNotGraduate", accountService.isGraduate(studentByUsername.getStudentId()));
    return "graduation";
  }

  @GetMapping("/enroll")
  public String enroll(Model model, Principal principal) {
    Account studentByUsername = accountService.getStudentByUsername(principal.getName());
    model.addAttribute("list", enrollmentService.list(studentByUsername.getStudentId()));
    return "enroll";
  }

  @GetMapping("/enrollcourse/{courseId}")
  public String enroll(Model model, Principal principal, @PathVariable String courseId) {
    Account studentByUsername = accountService.getStudentByUsername(principal.getName());
    Course course = courseService.findById(Integer.parseInt(courseId));
    model.addAttribute("list", enrollmentService.enrollCourse(studentByUsername.getStudentId(), courseId, course.getCourseFee()));
    return "redirect:/enroll";
  }



}