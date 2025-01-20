package com.example.aidsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.aidsystem.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Отключение CSRF для упрощения
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/register", "/auth/login").permitAll()  // Доступ без аутентификации
                .anyRequest().authenticated()  // Все остальные запросы требуют аутентификации
            )
            .addFilter(new JwtAuthenticationFilter()) // Добавление JWT-фильтра
            .formLogin(form -> form
                .loginPage("/auth/login")  // URL формы логина
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/auth/logout")  // URL выхода из системы
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Шифрование паролей
    }
}
