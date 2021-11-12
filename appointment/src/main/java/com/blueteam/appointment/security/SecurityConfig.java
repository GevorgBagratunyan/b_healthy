package com.blueteam.appointment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtTokenAuthorizationFilter(authenticationManager()))
                .authorizeRequests()
                // allow all who are accessing "login" service
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/**").permitAll() // ITS TEMPORARY antMatcher FOR TESTING PURPOSES ONLY!
                .antMatchers("/appointment/patient/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/appointment/doctor/**").hasAnyAuthority("DOCTOR", "ADMIN")
                .anyRequest().authenticated();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
