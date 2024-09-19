package com.spring_secuirty.SpringMVC_Secuirty.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        //Adding all the Users --->in-memory user details strategy.
        //Here we are storing all the passwords with no encoding algorithms=====>{noop}
        UserDetails suvendu= User.builder()
                .username("suvendu")
                .password("{noop}test@123")
                .roles("EMPLOYEE")
                .build();

        UserDetails sonali= User.builder()
                .username("sonali")
                .password("{noop}test@123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails akash= User.builder()
                .username("akash")
                .password("{noop}test@123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(suvendu,sonali,akash);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer.anyRequest().authenticated()
                )
                .formLogin(form->
                        form
                                .loginPage("/showCustomLogin")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll()
                )
                .logout(logout->logout.permitAll()
                );
        return http.build();
    }
}
