//package com.example.mycli.services;
//
//import com.example.mycli.exceptions.AuthenticationFailed;
//import com.example.mycli.entity.UserInformation;
//import com.example.mycli.repository.UserInfoRepo;
//import com.example.mycli.web.JwtProvider;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Service
//@RequiredArgsConstructor
//@Log
//public class UserStatusServiceImpl implements UserStatusService {
//
//    private final UserInfoRepo userInfoRepo;
//    private final JwtProvider jwtProvider;
//
//    @Override
//    public void changeStatus(int status, String email, HttpServletRequest httpServletRequest) {
//            UserInformation userInformation = userInfoRepo.findByEmail(email);
//            userInformation.setStatus(status);
//            userInfoRepo.save(userInformation);
//            log.info("user info was saved");
//
//    }
//    @Override
//    public Integer getStatus(HttpServletRequest httpServletRequest) {
//        String token = httpServletRequest.getHeader("token");
//        if (token != null) {
//            String email = jwtProvider.getLoginFromToken(token);
//            log.info("get user status");
//            return userInfoRepo.findByEmail(email).getStatus();
//        } else {
//            log.info("token is null");
//            throw new AuthenticationFailed();
//        }
//
//    }
//}
