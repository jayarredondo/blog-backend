//package com.example.blogbackend.security.filters;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.example.blogbackend.security.SecurityConstants;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//
//public class JWTAuthFilter extends OncePerRequestFilter {
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String header = request.getHeader("Authorization");
//
//        if (header == null || !header.startsWith(SecurityConstants.BEARER)) {
//            // If the header is null or does not include bearer than there is no JWT token to validate (indicating a new user is registering)
//            filterChain.doFilter(request, response);
//            return;
//            // the return keyword stops the process when the if statement is correct.
//        }
//
//        String token = header.replace(SecurityConstants.BEARER, "");
//        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
//                // Verifies that the given token matches the original token. require returns a verification builder.
//                .build()
//                .verify(token) // this returns a decoded JWT
//                .getSubject(); // which stores the username of the client
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList());
//        // Typically the second argument is the password, however the JWT token does not include sensitive data and thus does not have the password stored, therefore we include null as a default.
//        // Third argument should be authorizations, since we haven't set any specific authorizations yet: we can just set it to an empty array list
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//        // this is required to guide the authentication process. Even though this is the last filter in the chain. we indicate for the system to move forward to into the controller to perform the method that they requested.
//    }
//}
