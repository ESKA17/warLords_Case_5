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

    private final UserEntityRepo userEntityRepo;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public String authenticateAccount(String email, String password,
                                                      HttpServletResponse httpServletResponse) {
        if (userEntityRepo.findByEmail(email) == null) throw new AccountNotFound(email);
        UserEntity userEntity = userService.findByLoginAndPassword(email, password);
        if (userEntity != null) {
            String token = jwtProvider.generateToken(email);
//            Cookie cookie = new Cookie("token", token);
//            cookie.setHttpOnly(true);
//            httpServletResponse.addCookie(cookie);
            httpServletResponse.addHeader("token", token);
            log.info("Authenticated");
            return token;
        } else {
            log.info("bad request of email or password");
            throw new AccountCheckLoginPassword();
        }
    }
}
