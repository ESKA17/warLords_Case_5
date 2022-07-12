package com.example.mycli.services;

import com.example.mycli.entity.MessageHistory;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountBadRequest;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.repository.MessageHistoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageHistoryRepo messageHistoryRepo;
    private final UserService userService;
    @Override
    public MessageHistory getAllMessages(Long friendID, HttpServletRequest httpServletRequest) {
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity user = userService.findByAuthDataEmail(email);
        UserEntity friend = userService.findUserByID(friendID);
        log.info("finding message history for users " + user.getFullName() + " and " + friend.getFullName());
        MessageHistory messageHistory = messageHistoryRepo.findByFriendIDAndUserID(friendID, user.getId())
                .orElseThrow(() -> new AccountNotFound("message history for users " + user.getFullName() +
                        " and " + friend.getFullName()));
        log.info("message history for users " + user.getFullName() + " and " + friend.getFullName() + " was found");
        return messageHistory;
    }

    @Override
    public void saveMessage(Long toWhom, String message, HttpServletRequest httpServletRequest) {
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity user = userService.findByAuthDataEmail(email);
        UserEntity friend = userService.findUserByID(toWhom);
        log.info("saving message for users " + user.getFullName() + " and " + friend.getFullName());
        if (toWhom == null || message.isEmpty()) {
            throw new AccountBadRequest(" to whom ID / empty message");
        }
        MessageHistory messageHistory = messageHistoryRepo.findByFriendIDAndUserID(toWhom, user.getId())
                .orElse(null);
        if (messageHistory == null) {
            messageHistory = MessageHistory.builder()
                    .friendID(toWhom)
                    .userID(user.getId())
                    .text(message)
                    .build();
        } else {
            messageHistory.setText(messageHistory.getText() + "\n" + message);
        }
        messageHistoryRepo.save(messageHistory);
        log.info("message for users " + user.getFullName() + " and " + friend.getFullName() + " was saved");
    }
}
