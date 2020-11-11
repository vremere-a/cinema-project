package com.dev.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder encoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(PasswordEncoder encoder, UserDetailsService userDetailsService) {
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/users/**")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls/**",
                        "/movies/**",
                        "/movie-sessions/**").hasRole("ADMIN")
                .antMatchers("/orders/**",
                        "/shopping-carts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls/**",
                        "/movies/**",
                        "/movie-sessions/available/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/register/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
