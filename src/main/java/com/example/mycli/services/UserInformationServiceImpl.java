package com.example.mycli.services;

import com.example.mycli.exceptions.AccountBadRequest;
import com.example.mycli.model.*;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.entity.UserInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.mycli.model.SubjectType.*;

@Service
@RequiredArgsConstructor
@Log
public class UserInformationServiceImpl implements UserInformationService {
    private final UserService userService;
    @Override
    public void fillUserInformationForm(ScreeningRequest screeningRequest, HttpServletRequest httpServletRequest) {
        log.info("filling information form ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        String[] strings = screeningRequest.getDateOfBirth().split("-");
        int[] numbers = Arrays.stream(strings)
                .mapToInt(Integer::parseInt)
                .toArray();;
        LocalDate localDate = LocalDate.of(numbers[2], numbers[1], numbers[0]);
        UserInformation userInformation = UserInformation.builder()
                .dateOfBirth(localDate)
                .city(screeningRequest.getCity())
                .school(screeningRequest.getSchool())
                .IIN(screeningRequest.getIIN())
                .phoneNumber(screeningRequest.getPhoneNumber())
                .university(screeningRequest.getUniversity())
                .aboutMe(screeningRequest.getAboutMe())
                .build();
        UserEntity user = userService.findByAuthDataEmail(email);
        user.setUserInformation(userInformation);
        userService.saveUser(user);
        log.info("user: " + user);
        log.info("user information was saved: " + userInformation);
    }

    @Override
    public UserInfoResponse getUserInformationForm(HttpServletRequest httpServletRequest) {
        log.info("retrieving user info ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity user = userService.findByAuthDataEmail(email);
        Integer roleID = user.getAuthdata().getRoleEntity().getId();
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .aboutMe(user.getUserInformation().getAboutMe())
                .city(user.getUserInformation().getCity())
                .dateOfBirth(user.getUserInformation().getDateOfBirth())
                .IIN(user.getUserInformation().getIIN())
                .phoneNumber(user.getUserInformation().getPhoneNumber())
                .university(user.getUserInformation().getUniversity())
                .school(user.getUserInformation().getSchool())
                .roleID(roleID)
                .build();
        log.info("getting screening form for email " + email + ": " + user);
        log.info("user info: " + userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public void fillMajors(Majors majors, HttpServletRequest httpServletRequest) {
        log.info("filling majors ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity user = userService.findByAuthDataEmail(email);
        List<SubjectType> subjectList = user.getSubjectTypeList();
        for (int in: majors.getMajors()) {
            switch (in) {
                case 0: {
                    if (!subjectList.contains(MATH)) {subjectList.add(MATH);}
                    break;
                }
                case 1: {
                    if (!subjectList.contains(PHYSICS)) {subjectList.add(PHYSICS);}
                    break;
                }
                case 2: {
                    if (!subjectList.contains(CHEMISTRY)) {subjectList.add(CHEMISTRY);}
                    break;
                }
                case 3: {
                    if (!subjectList.contains(BIOLOGY)) {subjectList.add(BIOLOGY);}
                    break;
                }
                case 4: {
                    if (!subjectList.contains(INFORMATICS)) {subjectList.add(INFORMATICS);}
                    break;
                }
                case 5: {
                    if (!subjectList.contains(HISTORY)) {subjectList.add(HISTORY);}
                    break;
                }
                default: throw new AccountBadRequest("major with id " + in + " does not match any subject");
            }
        }
        user.setSubjectTypeList(subjectList);
        userService.saveUser(user);
        log.info("majors were filled");
    }

    @Override
    public void changeFullName(String fullName, HttpServletRequest httpServletRequest) {
        log.info("changing full name ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity user = userService.findByAuthDataEmail(email);
        user.setFullName(fullName);
        userService.saveUser(user);
        log.info("full name was changed");
    }

    @Override
    public List<Integer> getMajors(HttpServletRequest httpServletRequest) {
        log.info("getting majors ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity userEntity = userService.findByAuthDataEmail(email);
        List<SubjectType> subjectList = userEntity.getSubjectTypeList();
        List<Integer> out = new ArrayList<>();
        for (SubjectType in: subjectList) {
            switch (in) {
                case MATH: {
                    out.add(0);
                    break;
                }
                case PHYSICS: {
                    out.add(1);
                    break;
                }
                case CHEMISTRY: {
                    out.add(2);
                    break;
                }
                case BIOLOGY: {
                    out.add(3);
                    break;
                }
                case INFORMATICS: {
                    out.add(4);
                    break;
                }
                case HISTORY: {
                    out.add(5);
                    break;
                }
                default:
                    throw new AccountBadRequest("major with id " + in + " does not match any subject");
            }
        }
        log.info("majors sent");
        return out;
    }

}
