package com.example.mycli;

import com.example.mycli.repository.UserEntityRepo;
import com.example.mycli.repository.UserInfoRepo;
import com.example.mycli.services.AccountRegistrationService;
//import com.example.mycli.services.CreateAdminService;
import com.example.mycli.services.FilesStorageService;
//import com.example.mycli.services.UserInformationService;
import com.example.mycli.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;

@SpringBootApplication
@RequiredArgsConstructor
public class MyCliApplication implements CommandLineRunner {
    @Resource
    FilesStorageService storageService;
    @Autowired
    private final UserService userService;

    public static void main(String[] args)  {
        SpringApplication.run(MyCliApplication.class);
    }
    @Override
    public void run(String... arg0) {
        userService.initRoles();
    }
}
