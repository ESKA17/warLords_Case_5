package com.example.mycli.controllers;

import com.example.mycli.model.NotificationRequest;
import com.example.mycli.services.ConnectionsService;
import com.example.mycli.services.EmitterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/connections")
//@SecurityRequirement(name = "basicauth")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor

public class ConnectionsController {
    private final ConnectionsService connectionsService;
    private final EmitterService emitterService;
    @PostMapping("/match")
    public ResponseEntity<String> match(@RequestParam Long matchID, HttpServletRequest httpServletRequest) {
        connectionsService.match(matchID, httpServletRequest);
        emitterService.addEmitter(matchID, httpServletRequest);
        return ResponseEntity.ok("Connection was made");
    }
    @PostMapping("/break_match")
    public ResponseEntity<String> breakMatch(@RequestParam Long matchID, HttpServletRequest httpServletRequest) {
        connectionsService.breakMatch(matchID, httpServletRequest);
        return ResponseEntity.ok("Match was broken");
    }
    @GetMapping("/show_connections")
    public List<Long> getConnections(HttpServletRequest httpServletRequest) {
        return connectionsService.getConnections(httpServletRequest);
    }





}
