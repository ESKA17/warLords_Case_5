package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;

public interface UserService {

    void initRoles();

    UserEntity findByLoginAndPassword(String login, String password);
}
