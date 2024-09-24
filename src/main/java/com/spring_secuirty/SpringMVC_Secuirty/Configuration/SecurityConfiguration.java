package com.spring_secuirty.SpringMVC_Secuirty.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    //Adding Database Connection for retrieving users from Database.

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        //Adding all the Users --->in-memory user details strategy.
//        //Here we are storing all the passwords with no encoding algorithms=====>{noop}
//        UserDetails suvendu= User.builder()
//                .username("suvendu")
//                .password("{noop}test@123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails sonali= User.builder()
//                .username("sonali")
//                .password("{noop}test@123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//        UserDetails akash= User.builder()
//                .username("akash")
//                .password("{noop}test@123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(suvendu,sonali,akash);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer.requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form->
                        form
                                .loginPage("/showCustomLogin")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll()
                )
                .logout(logout->logout.permitAll()
                ).exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }
}
