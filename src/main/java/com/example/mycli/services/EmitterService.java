package com.example.mycli.services;

import javax.servlet.http.HttpServletRequest;

public interface EmitterService {

    void addEmitter(Long subscribeToID, HttpServletRequest httpServletRequest);


    void pushMessage(Long toWhom, String message, HttpServletRequest httpServletRequest);
}
