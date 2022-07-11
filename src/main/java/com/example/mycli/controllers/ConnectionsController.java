package com.example.mycli.controllers;

import com.example.mycli.services.ConnectionsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/connections")
//@SecurityRequirement(name = "basicauth")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor

public class ConnectionsController {
    private final ConnectionsService connectionsService;
    @PostMapping("/match")
    public void match(@RequestParam Long matchID, HttpServletRequest httpServletRequest) {
        connectionsService.match(matchID, httpServletRequest);
    }
    @PostMapping("/break_match")
    public void breakMatch(@RequestParam Long matchID, HttpServletRequest httpServletRequest) {
        connectionsService.breakMatch(matchID, httpServletRequest);
    }
}
