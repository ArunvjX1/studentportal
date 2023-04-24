package com.arun.studentportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private DataSource dataSource;
    // [...] 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("user").password(new BCryptPasswordEncoder().encode("password")).roles("All");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as username, password, 'true' as enabled" //full_name
                        + " from account where username=?").authoritiesByUsernameQuery("select username, 'STUDENT' as authority "+ " from account where username=?");;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers("/api/**", "/login*", "/signup*", "/css/**", "/js/**", "/assets/**")


                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID");
        return http.build();
    }
}