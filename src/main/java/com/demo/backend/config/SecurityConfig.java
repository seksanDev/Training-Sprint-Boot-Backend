package com.demo.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.demo.backend.config.token.TokenFilterConfiguerer;
import com.demo.backend.service.TokenService;

@EnableWebSecurity
public class SecurityConfig {
    private final TokenService tokenService;

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public SecurityFilterChain configure(AuthenticationManagerBuilder auth)
    // throws Exception {
    // super.configure(auth);
    // // TODO:
    // }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(requests -> requests.antMatchers("/user/register", "/user/login").anonymous()
                        .anyRequest().authenticated())
                .apply(new TokenFilterConfiguerer(tokenService)).and().build();
    }

}