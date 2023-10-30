package com.fpt.MeetLecturer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {
    //private JwtAuthEntryPoint authEntryPoint;
    //private CustomAccountDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges ->
                        exchanges
                                .pathMatchers("/lecturer/**").hasRole("LECTURER")
                                //.pathMatchers(HttpMethod.GET, "/public/**").permitAll()
                                .pathMatchers("/admin/**").hasRole("ADMIN")
                                .anyExchange().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        return (SecurityFilterChain) http.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new MapReactiveUserDetailsService(
                User.withUsername("user")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .password(passwordEncoder.encode("adminpassword"))
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public  JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
