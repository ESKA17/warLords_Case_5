package com.example.mycli.services;

import com.example.mycli.exceptions.AuthenticationFailed;
import com.example.mycli.model.StudyDegree;
import com.example.mycli.model.UserEntity;
import com.example.mycli.model.UserInformation;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.repository.UserInformationRepository;
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
public class ScreeningFormServiceImpl implements ScreeningFormService{
    private final UserInformationRepository userInformationRepository;
    private final UserEntityRepository userEntityRepository;
    private final JwtProvider jwtProvider;
    @Override
    public void fillScreeningForm(ScreeningRequest screeningRequest, HttpServletRequest httpServletRequest) {
        UserInformation userInformation;
        String token = httpServletRequest.getHeader("token");
        String email;
        if (token != null) {
            email = jwtProvider.getLoginFromToken(token);
        } else {
            throw new AuthenticationFailed();
        }
        if (userInformationRepository.findByEmail(email) == null) {
            userInformation = new UserInformation();
        } else {
            userInformation = userInformationRepository.findByEmail(email);
        }
        userInformation.setName(screeningRequest.getName());
        userInformation.setSurname(screeningRequest.getSurname());
        userInformation.setEmail(email);
        userInformation.setAge(screeningRequest.getAge());
        userInformation.setMiddleName(screeningRequest.getFatherName());
        int choice = screeningRequest.getStudyDegree();
        if (choice == 0) {
            userInformation.setStudyDegree(StudyDegree.FRONTEND);
        } else if (screeningRequest.getStudyDegree() == 1) {
            userInformation.setStudyDegree(StudyDegree.BACKEND);
        } else if (choice == 2) {
            userInformation.setStudyDegree(StudyDegree.IOS);
        } else if (choice == 3) {
            userInformation.setStudyDegree(StudyDegree.ANDROID);
        } else {
            userInformation.setStudyDegree(StudyDegree.DEVOPS);
        }
        userInformationRepository.save(userInformation);
    }

    @Override
    public UserInformation getScreeningForm(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (token != null) {
            String email = jwtProvider.getLoginFromToken(token);
            log.info("getting screening form");
            return userInformationRepository.findByEmail(email);
        } else {
            log.info("token is null");
        }
        return null;
    }

    @Override
    public List<UserInformation> getAllScreeningForms() {
        return userInformationRepository.findAll();
    }
    @Override
    public void fillTemplate(ScreeningRequest screeningRequest, Long id) {
        UserInformation userInformation;
        userInformation = new UserInformation();
        userInformation.setName(screeningRequest.getName());
        userInformation.setSurname(screeningRequest.getSurname());
        UserEntity user = userEntityRepository.findById(id).orElse(null);
        userInformation.setEmail(user.getEmail());
        userInformation.setAge(screeningRequest.getAge());
        userInformation.setMiddleName(screeningRequest.getFatherName());
        int choice = screeningRequest.getStudyDegree();
        if (choice == 0) {
            userInformation.setStudyDegree(StudyDegree.FRONTEND);
        } else if (screeningRequest.getStudyDegree() == 1) {
            userInformation.setStudyDegree(StudyDegree.BACKEND);
        } else if (choice == 2) {
            userInformation.setStudyDegree(StudyDegree.IOS);
        } else if (choice == 3) {
            userInformation.setStudyDegree(StudyDegree.ANDROID);
        } else {
            userInformation.setStudyDegree(StudyDegree.DEVOPS);
        }
        userInformationRepository.save(userInformation);
    }
}
