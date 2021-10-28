package com.blueteam.tracker.security;

import com.auth0.jwt.JWT;
import com.blueteam.tracker.entity.User;
import com.blueteam.tracker.repository.UserRepository;
import com.blueteam.tracker.security.properties.AppUser;
import com.blueteam.tracker.security.properties.JwtConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public JwtTokenAuthenticationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader(JwtConfig.HEADER_STRING);
        // 2. validate the header and check the prefix
        if (header == null || !header.startsWith(JwtConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);        // If not valid, go to the next filter.
            return;
        }
        // If there is no token provided and hence the user won't be authenticated it's Ok.
        // Maybe the user accessing a public path or asking for a token.
        // All secured paths that needs a token are already defined and secured in config class.
        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.
        // 3. Get the token
        String token = header.replace(JwtConfig.TOKEN_PREFIX, "");
        try {    // exceptions might be thrown in creating the claims if for example the token is expired
            // 4. Validate the token
            String userName = JWT.require(HMAC512(JwtConfig.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            if (userName != null) {
                // 5. Create auth object
                User user = userRepository.findByUsername(userName);
                AppUser appUser = new AppUser(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        appUser, null, appUser.getAuthorities());
                // 6. Authenticate the user
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }
}
