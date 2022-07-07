package com.example.mycli.services;

import com.example.mycli.model.ScreeningRequest;
import com.example.mycli.model.UserInformation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ScreeningFormService {
    void fillScreeningForm(ScreeningRequest screeningRequest, HttpServletRequest httpServletRequest);
    UserInformation getScreeningForm(HttpServletRequest httpServletRequest);
    List<UserInformation> getAllScreeningForms();

    void fillTemplate(ScreeningRequest screeningRequest, Long id);
}
