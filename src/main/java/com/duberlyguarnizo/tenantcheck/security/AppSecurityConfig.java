package com.duberlyguarnizo.tenantcheck.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    LoggedUserDetailsService loader;

    public AppSecurityConfig(LoggedUserDetailsService loader) {
        this.loader = loader;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/settings/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/api/v1/logged-user/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    PasswordEncoder pwdEncoder() {
        return new BCryptPasswordEncoder();
    }
}
