package com.example.mycli.services;

import com.example.mycli.entity.AuthData;
import com.example.mycli.entity.RoleEntity;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.RoleEntityRepo;
import com.example.mycli.repository.UserEntityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class CreateAdminServiceImpl implements CreateAdminService{
    private final UserEntityRepo userEntityRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void createAdmin() {
        log.info("creating admin ...");
        UserEntity userEntity = userEntityRepo.findByAuthdata_Email("admin").orElse(null);
        if (userEntity == null) {
            RoleEntity userRoleEntity = roleEntityRepo.findByName("ROLE_ADMIN");
            AuthData authData = AuthData.builder()
                    .email("sakenovramazan@gmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .roleEntity(userRoleEntity)
                    .firstTime(false)
                    .build();
            UserEntity user = UserEntity.builder()
                    .authdata(authData)
                    .fullName("admin")
                    .build();
            UserEntity admin = userService.saveUser(user);
            log.info("admin created - sakenovramazan@gmail.com:admin");
        } else {
            log.info("admin is already created - sakenovramazan@gmail.com:admin");
        }
    }
}
