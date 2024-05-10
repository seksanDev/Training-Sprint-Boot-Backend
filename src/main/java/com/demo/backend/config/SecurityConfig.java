package com.demo.backend.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.demo.backend.config.token.TokenFilterConfiguerer;
import com.demo.backend.service.TokenService;

@EnableWebSecurity
public class SecurityConfig {
    private final TokenService tokenService;
    private final String[] PUBLIC = {
            "/actuator/**", "/user/register", "/user/login", "/socket/**", "/chat/**"
    };

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
                .authorizeRequests(
                        requests -> requests.antMatchers(PUBLIC).anonymous()
                                .anyRequest().authenticated())
                .apply(new TokenFilterConfiguerer(tokenService)).and().build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // config.addAllowedOrigin("http://localhost:4200");
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost*"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

    }
}