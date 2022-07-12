package com.example.mycli.services;

import com.example.mycli.entity.Connection;
import com.example.mycli.web.SerializableSSE;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ConnectionsService {
    void match(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatch(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatchByID(Long posterID, Long accepterID);

    List<Long> getConnections(HttpServletRequest httpServletRequest);

    void addEmitterConnection(Connection connection);

    SerializableSSE getEmitter(Long toWhom, Long from);
}
