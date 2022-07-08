package com.example.mycli.services;

import com.example.mycli.entity.AuthData;
import com.example.mycli.entity.RoleEntity;
import com.example.mycli.exceptions.AccountConflict;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.AlumniRepo;
import com.example.mycli.repository.AuthDataRepo;
import com.example.mycli.repository.UserEntityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log
public class AccountRegistrationServiceImpl implements AccountRegistrationService{

    private final UserEntityRepo userEntityRepo;
    private final AuthDataRepo authDataRepo;
    private final AlumniRepo alumniRepo;
    private final UserService userService;

    @Override
    public void registerAccount(String email, String password, int role) {
        AuthData authData = authDataRepo.findByEmail(email);
        if (authData == null) {

            UserEntity user = new UserEntity();
            authData = AuthData.builder()
                    .email(email)
                    .password(password)
                    .build();
            userService.saveUser(user, role);
            log.info("email created");
        } else {
            log.info("email taken");
            throw new AccountConflict(email);
        }
    }
}
