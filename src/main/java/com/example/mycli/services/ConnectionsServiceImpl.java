package com.example.mycli.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class ConnectionsServiceImpl implements ConnectionsService {
    private final UserService userService;
    @Override
    public void match(Long matchID, HttpServletRequest httpServletRequest) {
        String email = userService.getEmailFromToken(httpServletRequest);
    }
}
