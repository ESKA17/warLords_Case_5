package com.example.mycli.services;

import com.example.mycli.exceptions.AccountConflict;
import com.example.mycli.exceptions.AccountCreated;
import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log
public class AccountRegistrationServiceImpl implements AccountRegistrationService{

    private final UserEntityRepository userEntityRepository;
    private final UserService userService;

    @Override
    public void registerAccount(String email, String password) {
        UserEntity userEntity = userEntityRepository.findByEmail(email);
        if (userEntity == null) {
            UserEntity user = new UserEntity();
            user.setPassword(password);
            user.setEmail(email);
            userService.saveUser(user);
            log.info("email created");
        } else {
            log.info("email taken");
            throw new AccountConflict(email);
        }
    }
}
