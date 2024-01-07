package com.example.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/login/**", "/css/**", "/images/**", "/js/**")
                            .permitAll()
                            .requestMatchers("/","/customer/**",  "/api/**", "register/**", "/product/**", "/category/**", "/verify-email/**", "/forgot-password/**", "/my/**", "api/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")

                )
                .logout(logout -> logout.logoutUrl("/perform_logout")
                                .deleteCookies("JSESSIONID")
                )
        ;
        return http.build();
    }
}