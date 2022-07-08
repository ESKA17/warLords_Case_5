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
    private final UserInfoRepo userInfoRepo;
    private final UserEntityRepo userEntityRepo;
    private final JwtProvider jwtProvider;
    @Override
    public void fillUserInformationForm(ScreeningRequest screeningRequest, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String email;
        if (token != null) {
            log.info("email was found in database");
            email = jwtProvider.getLoginFromToken(token);
        } else {
            throw new AuthenticationFailed();
        }
        int studyDegreeResponse = screeningRequest.getStudyDegree();
        StudyDegree studyDegree;
        log.info("choosing study degree");
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
        UserEntity user = userEntityRepo.findByAuthdata_Email(email);
        user.setUserInformation(userInformation);
        userInfoRepo.save(userInformation);
        userEntityRepo.save(user);
        log.info("user information was saved: " + userInformation);
    }

    @Override
    public UserInformation getUserInformationForm(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (token != null) {
            String email = jwtProvider.getLoginFromToken(token);
            log.info("getting screening form");
            UserEntity user = userEntityRepo.findByAuthdata_Email(email);
            return userInfoRepo.findById(user.getId()).orElseThrow(() -> new AccountNotFound(email));
        } else {
            log.info("token is null");
            throw new AccountBadRequest(" token is null");
        }
    }
}
