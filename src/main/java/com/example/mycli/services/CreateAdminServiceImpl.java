package com.example.mycli.services;

import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class CreateAdminServiceImpl implements CreateAdminService{
    private final UserEntityRepository userEntityRepository;
    private final UserService userService;

    @Override
    public void createAdmin() {
        UserEntity userEntity = userEntityRepository.findByEmail("admin");
        if (userEntity == null) {
            UserEntity user = new UserEntity();
            user.setPassword("admin");
            user.setEmail("adam.inov@gmail.com");
            userService.saveAdmin(user);
            log.info("email created");
        } else {
            log.info("admin is already created");
        }
    }
}
