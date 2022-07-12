package com.example.mycli.services;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ConnectionsService {
    void match(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatch(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatchByID(Long posterID, Long accepterID);

    List<Long> getConnections(HttpServletRequest httpServletRequest);
}
