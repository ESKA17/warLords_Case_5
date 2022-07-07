package com.example.mycli.controllers;

import com.example.mycli.model.UserInformation;
import com.example.mycli.services.ScreeningFormService;
import com.example.mycli.model.ScreeningRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/screening")
@CrossOrigin(origins = "http://localhost:3000")
public class ScreeningFormController {
    private final ScreeningFormService screeningFormService;
    @PostMapping()
    public void fillScreeningForm(@RequestBody @Valid ScreeningRequest screeningRequest,
                                  HttpServletRequest httpServletRequest) {
        screeningFormService.fillScreeningForm(screeningRequest, httpServletRequest);
    }

    @GetMapping()
    public UserInformation getScreeningForm(HttpServletRequest httpServletRequest) {
        return screeningFormService.getScreeningForm(httpServletRequest);
    }
    @GetMapping("/all")
    public List<UserInformation> getAllScreeningForms() {
        return screeningFormService.getAllScreeningForms();
    }
}
