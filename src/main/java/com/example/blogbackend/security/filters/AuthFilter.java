package com.example.blogbackend.security.filters;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.example.blogbackend.models.User;
//import com.example.blogbackend.security.CustomAuthManager;
//import com.example.blogbackend.security.SecurityConstants;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//public class AuthFilter extends UsernamePasswordAuthenticationFilter {
//
//    private CustomAuthManager authManager;
//
//    public AuthFilter(CustomAuthManager authManager) {
//        this.authManager = authManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
//            Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//            return authManager.authenticate(auth);
//        } catch(IOException e) {
//            throw new RuntimeException();
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String token = JWT.create()
//                .withSubject(authResult.getName())
//                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION)) // expires after 12 hours
//                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
//        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write(failed.getMessage());
//        response.getWriter().flush();
//    }
//}
