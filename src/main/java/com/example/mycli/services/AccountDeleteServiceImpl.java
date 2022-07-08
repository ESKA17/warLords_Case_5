//package com.example.mycli.services;
//
//import com.example.mycli.repository.UserEntityRepo;
//import com.example.mycli.repository.UserInfoRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AccountDeleteServiceImpl implements AccountDeleteService{
//
//    private final UserInfoRepo userInfoRepo;
//    private final UserEntityRepo userEntityRepo;
//    @Override
//    public void deleteAccount(Long id) {
//        if (userEntityRepo.findById(id).isPresent() ) {
//            userEntityRepo.deleteById(id);
//        }
//        if (userInfoRepo.findById(id).isPresent()) {
//            userInfoRepo.deleteById(id);
//        }
//    }
//}
