package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountDeactivationServiceImpl implements AccountDeactivationService {

    private final UserService userService;
    @Override
    public void deactivateAccount(String email) {
        log.info("deactivating account ...");
        UserEntity userEntity = userService.findByAuthDataEmail(email);
        userEntity.setActive(false);
        log.info("account was deactivated: " + email);
    }
}
