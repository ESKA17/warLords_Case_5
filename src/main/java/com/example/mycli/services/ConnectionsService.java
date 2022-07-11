package com.example.mycli.services;

import javax.servlet.http.HttpServletRequest;

public interface ConnectionsService {
    void match(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatch(Long matchID, HttpServletRequest httpServletRequest);

    void breakMatchByID(Long posterID, Long accepterID);
}
