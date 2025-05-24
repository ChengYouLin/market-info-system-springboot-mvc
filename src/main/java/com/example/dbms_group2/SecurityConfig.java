package com.example.dbms_group2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 🔓 所有請求都放行
                )
                .logout(logout -> logout.disable()) // 不用 Spring 的 logout
                .httpBasic(httpBasic -> httpBasic.disable()) // 禁用 basic auth 視窗
                .formLogin(login -> login.disable())         // ✅ Spring 6.1 寫法，替代 .formLogin().disable()
                .csrf(csrf -> csrf.disable());               // 如果你沒用 CSRF token，這行非常重要

        return http.build();
    }
}


