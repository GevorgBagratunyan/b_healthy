package com.blueteam.authentication.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blueteam.authentication.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public CustomAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository=userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("authorization");
        if(header == null || !header.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }
        Authentication authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

    private Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("authorization").replace("Bearer ","");
        if(token != null){
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256("BlueTeam".getBytes()))
                    .build().verify(token);
            String username = jwt.getSubject();
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) jwt.getClaim("authorities");
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, token, authorities);
            return authentication;
        }
        return null;
    }
}
