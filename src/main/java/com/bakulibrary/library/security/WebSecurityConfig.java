package com.bakulibrary.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers("/", "/book/**", "/search/**", "/contact").permitAll()
                            .requestMatchers("/registration/**", "/login/**").permitAll()
                            .requestMatchers("/css/**", "/image/**", "/js/**").permitAll()
                            .requestMatchers("/test", "/admin/**").hasRole("ADMIN")
                            .requestMatchers("/addBookToList","/account/books/**").hasRole("USER")
                            .anyRequest().permitAll()
                )

                .formLogin(form ->
                    form
                            .loginPage("/login")
                            .loginProcessingUrl("/authUser")
                            .defaultSuccessUrl("/")
                            .permitAll()
                )

                .logout(logout ->
                    logout
                            .logoutSuccessUrl("/")
                            .permitAll()
                );

        return http.build();
    }

}
