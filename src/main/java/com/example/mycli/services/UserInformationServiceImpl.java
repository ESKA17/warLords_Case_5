package com.example.mycli.services;

import com.example.mycli.exceptions.AccountBadRequest;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.exceptions.AuthenticationFailed;
import com.example.mycli.model.StudyDegree;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.entity.UserInformation;
import com.example.mycli.repository.UserEntityRepo;
import com.example.mycli.repository.UserInfoRepo;
import com.example.mycli.model.ScreeningRequest;
import com.example.mycli.web.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class UserInformationServiceImpl implements UserInformationService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    @Override
    public void fillUserInformationForm(ScreeningRequest screeningRequest, HttpServletRequest httpServletRequest) {
        log.info("filling information form ...");
        String token = httpServletRequest.getHeader("token");
        String email;
        if (token != null) {
            email = jwtProvider.getLoginFromToken(token);
            log.info("account was found in database: " + email);
        } else {
            throw new AuthenticationFailed("token is null");
        }
        int studyDegreeResponse = screeningRequest.getStudyDegree();
        StudyDegree studyDegree;

        if (studyDegreeResponse == 0) {
            studyDegree = StudyDegree.HIGH_SCHOOL_UNFINISHED;
        }
        else if (studyDegreeResponse == 1) {
            studyDegree = StudyDegree.HIGH_SCHOOL_FINISHED;
        }
        else if (studyDegreeResponse == 2) {
            studyDegree = StudyDegree.BACHELOR;
        } else if (studyDegreeResponse == 3) {
            studyDegree = StudyDegree.MASTER;
        } else {
            throw new AccountBadRequest("study degree - " + studyDegreeResponse);
        }
        log.info("study degree: " + studyDegree);
        UserInformation userInformation = UserInformation.builder()
                .age(screeningRequest.getAge())
                .city(screeningRequest.getCity())
                .school(screeningRequest.getSchool())
                .graduationYear(screeningRequest.getGraduationYear())
                .IIN(screeningRequest.getIIN())
                .phoneNumber(screeningRequest.getPhoneNumber())
                .university(screeningRequest.getUniversity())
                .studyDegree(studyDegree)
                .build();
        UserEntity user = userService.findByAuthDataEmail(email);
        user.setUserInformation(userInformation);
        userService.saveUser(user);
        log.info("user: " + user);
        log.info("user information was saved: " + userInformation);
    }

    @Override
    public UserInformation getUserInformationForm(HttpServletRequest httpServletRequest) {
        log.info("retrieving user info ...");
        String token = httpServletRequest.getHeader("token");
        if (token != null) {
            String email = jwtProvider.getLoginFromToken(token);
            UserEntity user = userService.findByAuthDataEmail(email);
            log.info("getting screening form for email " + email + ": " + user);
            log.info("user info: " + user.getUserInformation());
            return user.getUserInformation();
        } else {
            throw new AccountBadRequest(" token is null");
        }
    }
}
