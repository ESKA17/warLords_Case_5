package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionsServiceImpl implements ConnectionsService {
    private final UserService userService;
    @Override
    public void match(Long matchID, HttpServletRequest httpServletRequest) {
        log.info("starting matching ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity poster = userService.findByAuthDataEmail(email);
        UserEntity accepter =  userService.findUserByID(matchID);
        List<UserEntity> posterList = poster.getConnections();
        List<UserEntity> accepterList = accepter.getConnections();
        posterList.add(accepter);
        accepterList.add(poster);
        userService.saveUser(poster);
        userService.saveUser(accepter);
        log.info("users matched \u2665\u2665\u2665");
    }

    @Override
    public void breakMatch(Long matchID, HttpServletRequest httpServletRequest) {
        log.info("breaking match ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity poster = userService.findByAuthDataEmail(email);
        UserEntity accepter =  userService.findUserByID(matchID);
        List<UserEntity> posterList = poster.getConnections();
        List<UserEntity> accepterList = accepter.getConnections();
        posterList.add(accepter);
        accepterList.add(poster);
        userService.saveUser(poster);
        userService.saveUser(accepter);
        log.info("users unmatched \1F494");
    }
}
