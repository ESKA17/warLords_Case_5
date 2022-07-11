package com.example.mycli.controllers;

import com.example.mycli.entity.UserInformation;
import com.example.mycli.model.Majors;
import com.example.mycli.services.UserInformationService;
import com.example.mycli.model.ScreeningRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user_info")
@CrossOrigin(origins = "http://localhost:3000")
public class UserInformationFormController {
    private final UserInformationService userInformationService;
    @PostMapping()
    public ResponseEntity<String> fillScreeningForm(@RequestBody @Valid ScreeningRequest screeningRequest,
                                            HttpServletRequest httpServletRequest) {
        userInformationService.fillUserInformationForm(screeningRequest, httpServletRequest);
        return ResponseEntity.ok("Form was successfully filled");
    }

    @PostMapping()
    public ResponseEntity<String> fillMajors(@RequestBody @Valid Majors majors,
                                                    HttpServletRequest httpServletRequest) {
        userInformationService.fillMajors(majors, httpServletRequest);
        return ResponseEntity.ok("Majors were successfully filled");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInformation getScreeningForm(HttpServletRequest httpServletRequest) {
        return userInformationService.getUserInformationForm(httpServletRequest);
    }

    @PostMapping()
    public ResponseEntity<String> changeFullName(@RequestParam String fullName,
                                             HttpServletRequest httpServletRequest) {
        userInformationService.changeFullName(fullName, httpServletRequest);
        return ResponseEntity.ok("Name was successfully changed");
    }
}
