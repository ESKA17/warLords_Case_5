package com.example.mycli.controllers;

import com.example.mycli.model.RankingForm;
import com.example.mycli.services.RankingService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/ranking_form")
@RequiredArgsConstructor
public class RankingFormController {
    private final RankingService rankingService;

    @PostMapping()
    public ResponseEntity<String> fillRankingForm(@RequestBody RankingForm rankingForm,
                                                  HttpServletRequest httpServletRequest) {
        rankingService.fillRankingForm(rankingForm, httpServletRequest);
        return ResponseEntity.ok("Form was successfully filled");
    }
}
