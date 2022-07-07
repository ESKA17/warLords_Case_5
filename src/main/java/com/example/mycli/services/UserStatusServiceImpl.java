package com.example.mycli.services;

import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.exceptions.AuthenticationFailed;
import com.example.mycli.model.UserInformation;
import com.example.mycli.repository.UserInformationRepository;
import com.example.mycli.web.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Log
public class UserStatusServiceImpl implements UserStatusService {

    private final UserInformationRepository userInformationRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void changeStatus(int status, String email, HttpServletRequest httpServletRequest) {
            UserInformation userInformation = userInformationRepository.findByEmail(email);
            userInformation.setStatus(status);
            userInformationRepository.save(userInformation);
            log.info("user info was saved");

    }
    @Override
    public Integer getStatus(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (token != null) {
            String email = jwtProvider.getLoginFromToken(token);
            log.info("get user status");
            return userInformationRepository.findByEmail(email).getStatus();
        } else {
            log.info("token is null");
            throw new AuthenticationFailed();
        }

    }
}
