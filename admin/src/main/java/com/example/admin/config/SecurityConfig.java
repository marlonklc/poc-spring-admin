package com.example.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Enable so that the clients can authenticate via HTTP basic for registering
            .httpBasic()
        .and()
            // Page with login form is served as /login.html and does a POST on /login
            .formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/login")
            .permitAll()
        .and()
            // The UI does a POST on /logout on logout
            .logout().logoutUrl("/logout")
        .and()
            // Requests for the login page and the static assets are allowed
            .authorizeRequests()
            .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
            .permitAll()
        .and()
            // ... and any other request needs to be authorized
            .authorizeRequests().antMatchers("/**").authenticated()
        .and()
            // The ui currently doesn't support csrf
            .csrf().disable();
    }
}
