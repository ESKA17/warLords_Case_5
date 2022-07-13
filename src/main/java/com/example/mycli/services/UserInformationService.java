package com.example.mycli.services;

import com.example.mycli.entity.RoleEntity;
import com.example.mycli.model.Majors;
import com.example.mycli.model.ScreeningRequest;
import com.example.mycli.entity.UserInformation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserInformationService {
    void fillUserInformationForm(ScreeningRequest screeningRequest, HttpServletRequest httpServletRequest);
    UserInformation getUserInformationForm(HttpServletRequest httpServletRequest);

    void fillMajors(Majors majors, HttpServletRequest httpServletRequest);

    void changeFullName(String fullName, HttpServletRequest httpServletRequest);

    List<Integer> getMajors(HttpServletRequest httpServletRequest);

    void getUsersRoleById(Long id, HttpServletRequest httpServletRequest);
}
