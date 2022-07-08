package com.example.mycli.services;

import com.example.mycli.entity.AuthData;
import com.example.mycli.entity.RoleEntity;
import com.example.mycli.exceptions.AccountConflict;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.RoleNotFound;
import com.example.mycli.repository.AlumniRepo;
import com.example.mycli.repository.AuthDataRepo;
import com.example.mycli.repository.RoleEntityRepo;
import com.example.mycli.repository.UserEntityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log
public class AccountRegistrationServiceImpl implements AccountRegistrationService{

    private final UserEntityRepo userEntityRepo;
    private final AuthDataRepo authDataRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerAccount(String email, String password, int role) {
        AuthData authData = authDataRepo.findByEmail(email);
        if (authData != null) {
            log.info("email taken");
            throw new AccountConflict(email);
        }
        String roleName;
        if (role == 1) {
            log.info("role mentor");
            roleName = "ROLE_MENTOR";
        } else if (role == 2) {
            log.info("role mentee");
            roleName = "ROLE_MENTEE";
        } else {
            throw new RoleNotFound(role);
        }
        RoleEntity userRoleEntity = roleEntityRepo.findByName(roleName);
        UserEntity user = new UserEntity();
        authData = AuthData.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roleEntity(userRoleEntity)
                .build();
        user.setAuthdata(authData);
        authDataRepo.save(authData);
        UserEntity userEntity = userEntityRepo.save(user);
        log.info("user created: " + userEntity);
    }
}
