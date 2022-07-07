package com.example.mycli.controllers;

import com.example.mycli.repository.UserInformationRepository;
import com.example.mycli.services.AccountDeleteService;
import com.example.mycli.services.UserStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Log
public class JobApplicationControllers {

    private final UserStatusService userStatusService;
    private final AccountDeleteService accountDeleteService;

    @PostMapping("/changeStatus")
    public void changeStatus(@RequestParam int param, String email, HttpServletRequest httpServletRequest) {
        log.info("status change");
        userStatusService.changeStatus(param, email, httpServletRequest);
    }

    @GetMapping("/getStatus")
    public Integer changeStatus(HttpServletRequest httpServletRequest) {
        log.info("status get");
        return userStatusService.getStatus(httpServletRequest);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> userDelete(@RequestParam Long id) {
        accountDeleteService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body("Account was deleted");
    }
}
