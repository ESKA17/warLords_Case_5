package com.example.mycli.services;

import com.example.mycli.entity.MessageHistory;

import javax.servlet.http.HttpServletRequest;

public interface MessageService {

    MessageHistory getAllMessages(Long friendID, HttpServletRequest httpServletRequest);

    void saveMessage(Long toWhom, String message, HttpServletRequest httpServletRequest);
}
