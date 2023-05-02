package com.arun.studentportal;

import com.arun.studentportal.entity.Course;
import com.arun.studentportal.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import java.util.Locale;

@Configuration
class MiscellaneousBeans {


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.UK);
        return sessionLocaleResolver;
    }

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {

//            courseRepository.save(new Course("SESC", 90l));
//            courseRepository.save(new Course("Project Management", 50l));
//            courseRepository.save(new Course("Cloud Computing", 80l));
//            courseRepository.save(new Course("Eco Engineering", 50l));
//            courseRepository.save(new Course("Research Practice", 60l));

        };
    }
}