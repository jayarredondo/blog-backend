//package com.example.blogbackend.security;
//import com.example.blogbackend.models.User;
//import com.example.blogbackend.repos.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthManager implements AuthenticationManager {
//
//    private final UserRepository userDao;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public CustomAuthManager(UserRepository userDao) {
//        this.userDao = userDao;
//        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        User user = userDao.findByUsername(authentication.getName());
//        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
//            throw new BadCredentialsException("Incorrect password");
//        }
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getName());
//        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
//    }
//}
