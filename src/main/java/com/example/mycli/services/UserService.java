package com.example.mycli.services;

import com.example.mycli.entity.AuthData;
import com.example.mycli.entity.UserEntity;

public interface UserService {

    void initRoles();
    UserEntity saveUser(UserEntity userEntity);

    UserEntity findByLoginAndPassword(String login, String password);
}
