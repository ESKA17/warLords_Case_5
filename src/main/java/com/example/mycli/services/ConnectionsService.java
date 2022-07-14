package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ConnectionsService {
    void matchFromMentee(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatch(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatchByID(Long posterID, Long accepterID);

    List<UserEntity> getConnectionsStatusOne(HttpServletRequest httpServletRequest);

    List<UserEntity> getConnectionsStatusTwo(HttpServletRequest httpServletRequest);

    void matchFromMentor(Long matchID, HttpServletRequest httpServletRequest);
}
