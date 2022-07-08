//package com.example.mycli.services;
//
//import com.example.mycli.entity.UserEntity;
//import com.example.mycli.repository.UserEntityRepo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Log
//public class CreateAdminServiceImpl implements CreateAdminService{
//    private final UserEntityRepo userEntityRepo;
//    private final UserServiceImpl userServiceImpl;
//
//    @Override
//    public void createAdmin() {
//        UserEntity userEntity = userEntityRepo.findByEmail("admin");
//        if (userEntity == null) {
//            UserEntity user = new UserEntity();
//            user.setPassword("admin");
//            user.setEmail("adam.inov@gmail.com");
//            userServiceImpl.saveAdmin(user);
//            log.info("email created");
//        } else {
//            log.info("admin is already created");
//        }
//    }
//}
