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
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionsServiceImpl implements ConnectionsService {
    private final UserService userService;
    private final EmitterService emitterService;
    private final ConnectionRepo connectionRepo;
    @Override
    public void matchFromMentee(Long matchID, HttpServletRequest httpServletRequest) {
        if (matchID == null) {
            throw new AccountBadRequest("check matchID");
        }
        log.info("starting matching ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity poster = userService.findByAuthDataEmail(email);
        UserEntity accepter =  userService.findUserByID(matchID);
        Connection connection = Connection.builder()
                .userID(poster.getId())
                .friendID(matchID)
                .sseEmitter(emitterService.addEmitter())
                .connectionStatus(1)
                .messageHistory("")
                .build();
        List<Connection> posterList = poster.getConnections();
        List<Connection> accepterList = accepter.getConnections();
        posterList.add(connection);
        accepterList.add(connection);
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
    public List<Long> getConnectionsStatusOne(HttpServletRequest httpServletRequest) {
        log.info("retrieving connections status one ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        List<Connection> connectionsList = userService.findByAuthDataEmail(email).getConnections();
        List<Long> out = new ArrayList<>();
        for (Connection connection: connectionsList) {
            if (connection.getConnectionStatus() == 1) {
                Long friendID = userService.findUserByID(connection.getFriendID()).getId();
                out.add(friendID);
            }
        }
        log.info("connections were retrieved: " + out);
        return out;
    }
    @Override
    public List<Long> getConnectionsStatusTwo(HttpServletRequest httpServletRequest) {
        log.info("retrieving connections status two ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        List<Connection> connectionsList = userService.findByAuthDataEmail(email).getConnections();
        List<Long> out = new ArrayList<>();
        for (Connection connection: connectionsList) {
            if (connection.getConnectionStatus() == 2) {
                Long friendID = userService.findUserByID(connection.getFriendID()).getId();
                out.add(friendID);
            }
        }
        log.info("connections were retrieved: " + out);
        return out;
    }

    @Override
    public void matchFromMentor(Long matchID, HttpServletRequest httpServletRequest) {
        if (matchID == null) {
            throw new AccountBadRequest("check matchID");
        }
        log.info("starting matching ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity poster = userService.findByAuthDataEmail(email);
        UserEntity accepter =  userService.findUserByID(matchID);
        List<Connection> posterList = poster.getConnections();
        List<Connection> accepterList = accepter.getConnections();
        Connection search = null;
        int posterSearch = -1, accepterSearch = -1;
        for (Connection connection: posterList) {
            if (Objects.equals(connection.getFriendID(), matchID)) {
                search = connection;
                posterSearch = posterList.indexOf(connection);
                accepterSearch = accepterList.indexOf(connection);
            }
        }
        if (search != null) {
            search.setConnectionStatus(2);
        } else {
            throw new AccountNotFound("emitter between users "+ matchID + " and " +  poster.getId() +
                    " was not found");
        }
        posterList.set(posterSearch, search);
        accepterList.set(accepterSearch, search);
        userService.saveUser(poster);
        userService.saveUser(accepter);
        log.info("users matched \u2665\u2665\u2665");
    }


    private void matchBreaker(Long accepterID, UserEntity poster) {
        if (accepterID == null) {
            throw new AccountBadRequest("check matchID");
        }
        Connection connection = connectionRepo.findByFriendIDAndUserID(accepterID, poster.getId()).orElseThrow(
                () -> new AccountNotFound("emitter between users "+ accepterID + " and " +  poster.getId() +
                        " was not found")
        );
        UserEntity accepter =  userService.findUserByID(accepterID);
        List<Connection> posterList = poster.getConnections();
        List<Connection> accepterList = accepter.getConnections();
        posterList.remove(connection);
        accepterList.remove(connection);
        userService.saveUser(poster);
        userService.saveUser(accepter);
        connectionRepo.delete(connection);
        log.info("users unmatched(((: " + accepter + " and " + poster);
    }
}
