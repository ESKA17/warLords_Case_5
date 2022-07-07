package com.example.mycli.services;

import javax.servlet.http.HttpServletRequest;

public interface UserStatusService {
    void changeStatus(int status, String id, HttpServletRequest httpServletRequest);
    Integer getStatus(HttpServletRequest httpServletRequest);
}
