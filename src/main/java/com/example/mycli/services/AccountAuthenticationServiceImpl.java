package com.example.mycli.services;

import com.example.mycli.exceptions.*;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.UserEntityRepo;
import com.example.mycli.web.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
@Log
public class AccountAuthenticationServiceImpl implements AccountAuthenticationService{
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public String authenticateAccount(String email, String password,
                                                      HttpServletResponse httpServletResponse) {
        UserEntity userEntity = userService.findByLoginAndPassword(email, password);
        if (userEntity != null) {
            String token = jwtProvider.generateToken(email);
            httpServletResponse.addHeader("token", token);
            log.info("Token: " + token);
            return token;
        }
        return "";
    }
}
