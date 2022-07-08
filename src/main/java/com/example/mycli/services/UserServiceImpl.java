package com.example.mycli.services;

import com.example.mycli.entity.AuthData;
import com.example.mycli.entity.RoleEntity;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.exceptions.PasswordFailed;
import com.example.mycli.exceptions.RoleNotFound;
import com.example.mycli.repository.RoleEntityRepo;
import com.example.mycli.repository.UserEntityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserEntityRepo userEntityRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void initRoles() {
        RoleEntity roleAdmin = new RoleEntity(0L, "ROLE_ADMIN");
        RoleEntity roleMentor = new RoleEntity(1L, "ROLE_MENTOR");
        RoleEntity roleMentee = new RoleEntity(2L, "ROLE_MENTEE");
        roleEntityRepo.save(roleAdmin);
        roleEntityRepo.save(roleMentor);
        roleEntityRepo.save(roleMentee);
    }

    @Override
    public UserEntity findByLoginAndPassword(String email, String password) {
        UserEntity userEntity = userEntityRepo.findByAuthdata_Email(email);
        if (userEntity != null) {
            log.info(userEntity + " - user entity");
            if (passwordEncoder.matches(password, userEntity.getAuthdata().getPassword())) {
                log.info("password was verified");
                return userEntity;
            } else {
                log.info("bad request: password");
                throw new PasswordFailed();
            }
        } else {
            throw new AccountNotFound(email);
        }
    }
}
