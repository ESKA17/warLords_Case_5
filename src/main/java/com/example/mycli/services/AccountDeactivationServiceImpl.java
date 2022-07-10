package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.repository.AuthDataRepo;
import com.example.mycli.repository.UserEntityRepo;
import com.example.mycli.repository.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountDeactivationServiceImpl implements AccountDeactivationService {

    private final UserInfoRepo userInfoRepo;
    private final UserEntityRepo userEntityRepo;
    private final AuthDataRepo authDataRepo;
    @Override
    public void deactivateAccount(String email) {
        log.info("deactivating account ...");
        if (userEntityRepo.findByAuthdata_Email(email) != null) {
            UserEntity userEntity = userEntityRepo.findByAuthdata_Email(email);
            userEntity.setActive(false);
            log.info("account was deactivated: " + email);
        } else {
            log.info("account not found");
            throw new AccountNotFound(email);
        }
    }
}
