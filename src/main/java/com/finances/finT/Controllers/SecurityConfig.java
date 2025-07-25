package com.finances.finT.Controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // .authorizeHttpRequests(auth -> auth
            //     .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
            //     .anyRequest().authenticated()
            // )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Временно разрешаем все запросы
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // Обрабатывает POST запросы с формы
                .defaultSuccessUrl("/expenses", true) // Всегда перенаправлять после входа
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            )
            .userDetailsService(userDetailsService) // Критически важно!
            .csrf(csrf -> csrf.disable()); 

        return http.build();
    }
}