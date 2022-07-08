package com.example.mycli.services;

import com.example.mycli.entity.RoleEntity;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.RoleEntityRepo;
import com.example.mycli.repository.UserEntityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepo userEntityRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserEntity userEntity) {
        RoleEntity userRoleEntity = roleEntityRepo.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRoleEntity);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntityRepo.save(userEntity);
    }
    public void saveAdmin(UserEntity userEntity) {
        RoleEntity userRoleEntity = roleEntityRepo.findByName("ROLE_ADMIN");
        userEntity.setRoleEntity(userRoleEntity);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntityRepo.save(userEntity);
    }

    public UserEntity findByEmail(String login) {
        return userEntityRepo.findByEmail(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = userEntityRepo.findByEmail(login);
        System.out.println(userEntity + " - user entity");
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
