package com.example.mycli.services;

import com.example.mycli.entity.RoleEntity;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.exceptions.PasswordFailed;
import com.example.mycli.repository.AuthDataRepo;
import com.example.mycli.repository.RoleEntityRepo;
import com.example.mycli.repository.UserEntityRepo;
import com.example.mycli.repository.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserEntityRepo userEntityRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final UserInfoRepo userInfoRepo;
    private final AuthDataRepo authDataRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void initRoles() {
        log.info("roles initialization ...");
        if (roleEntityRepo.count() == 0) {
            RoleEntity roleAdmin = new RoleEntity(0L, "ROLE_ADMIN");
            RoleEntity roleMentor = new RoleEntity(1L, "ROLE_MENTOR");
            RoleEntity roleMentee = new RoleEntity(2L, "ROLE_MENTEE");
            roleEntityRepo.save(roleAdmin);
            roleEntityRepo.save(roleMentor);
            roleEntityRepo.save(roleMentee);
            log.info("roles initialization was successful");
        } else {
            log.info("roles have been already added");
        }

    }

    @Override
    public UserEntity findByAuthDataEmail(String email) {
        log.info("finding UserEntity by AuthData_email ...");
        return userEntityRepo.findByAuthdata_Email(email).orElseThrow(
                () -> new AccountNotFound(email));
    }

    @Override
    public RoleEntity findRoleEntityByName(String name) {
        log.info("finding RoleEntity by name ...");
        return roleEntityRepo.findByName(name);
    }

    @Transactional
    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info("saving user ...");
        if (user.getUserInformation() != null) {
            userInfoRepo.save(user.getUserInformation());
        }
        authDataRepo.save(user.getAuthdata());
        return userEntityRepo.save(user);
    }

    @Override
    public UserEntity findByLoginAndPassword(String email, String password) {
        log.info("finding UserEntity by email and password ...");
        UserEntity userEntity = findByAuthDataEmail(email);
        if (userEntity != null) {
            log.info(userEntity + " - user entity");
            if (passwordEncoder.matches(password, userEntity.getAuthdata().getPassword())) {
                log.info("password was verified");
                return userEntity;
            } else {

                throw new PasswordFailed();
            }
        } else {
            throw new AccountNotFound(email);
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        log.info("finding UserEntity by email ...");
        UserEntity userEntity = findByAuthDataEmail(email);
        if (userEntity != null) {
            log.info("email verification: " + userEntity + " - user entity");
                return userEntity;
        } else {
            log.info("email verification: user not found");
            throw new AccountNotFound(email);
        }
    }

    @Override
    public UserEntity findByVerificationCode(String verificationCode) {
        log.info("finding UserEntity by verification code ...");
        return userEntityRepo.findByVerificationCode(verificationCode).orElseThrow(
                () -> new AccountNotFound(verificationCode));
    }

    @Override
    public UserEntity checkByAuthDataEmail(String email) {
        log.info("checking UserEntity by AuthData_email ...");
        return userEntityRepo.findByAuthdata_Email(email).orElse(null);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        log.info("accessing user database");
        return userEntityRepo.findAll();
    }
}
