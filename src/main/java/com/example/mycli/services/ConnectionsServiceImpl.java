package com.example.mycli.services;

import com.example.mycli.entity.Connection;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountBadRequest;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.repository.ConnectionRepo;
import com.example.mycli.web.SerializableSSE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionsServiceImpl implements ConnectionsService {
    private final UserService userService;
    private final ConnectionRepo connectionRepo;
    @Override
    public void match(Long matchID, HttpServletRequest httpServletRequest) {
        if (matchID == null) {
            throw new AccountBadRequest("check matchID");
        }
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
        matchBreaker(matchID, poster);
    }

    @Override
    public void breakMatchByID(Long posterID, Long accepterID) {
        log.info("breaking match by id ...");
        if (posterID == null) {
            throw new AccountBadRequest("check matchID");
        }
        UserEntity poster = userService.findUserByID(posterID);
        matchBreaker(accepterID, poster);
    }

    @Override
    public List<Long> getConnections(HttpServletRequest httpServletRequest) {
        log.info("retrieving connections ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        List<UserEntity> connectionsList = userService.findByAuthDataEmail(email).getConnections();
        List<Long> out = new ArrayList<>();
        for (UserEntity user: connectionsList) {
            out.add(user.getId());
        }
        log.info("connections were retrieved: " + out);
        return out;
    }

    @Override
    public void addEmitterConnection(Connection connection) {
        log.info("saving connection ...");
        connectionRepo.save(connection);
        log.info("saving connection done");
    }

    @Override
    public SerializableSSE getEmitter(Long toWhom, Long from) {
        log.info("accessing base for emitter between users "+ toWhom + " and " +  from);
        Connection connection = connectionRepo.findByFriendIDAndUserID(toWhom, from).orElseThrow(
                () -> new AccountNotFound("emitter between users "+ toWhom + " and " +  from));
        log.info("emitter was found");
        return connection.getSseEmitter();
    }

    private void matchBreaker(Long accepterID, UserEntity poster) {
        if (accepterID == null) {
            throw new AccountBadRequest("check matchID");
        }
        UserEntity accepter =  userService.findUserByID(accepterID);
        List<UserEntity> posterList = poster.getConnections();
        List<UserEntity> accepterList = accepter.getConnections();
        posterList.remove(accepter);
        accepterList.remove(poster);
        userService.saveUser(poster);
        userService.saveUser(accepter);
        log.info("users unmatched(((: " + accepter + " and " + poster);
    }
}
