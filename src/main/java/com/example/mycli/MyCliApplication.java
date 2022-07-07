package com.example.mycli;

import com.example.mycli.model.ScreeningRequest;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.repository.UserInformationRepository;
import com.example.mycli.services.AccountRegistrationService;
import com.example.mycli.services.CreateAdminService;
import com.example.mycli.services.FilesStorageService;
import com.example.mycli.services.ScreeningFormService;
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
    private final CreateAdminService createAdminService;
    private final AccountRegistrationService accountRegistrationService;
    private final UserEntityRepository userEntityRepository;
    private final UserInformationRepository userInformationRepository;
    private final ScreeningFormService screeningFormService;

    public static void main(String[] args)  {
        SpringApplication.run(MyCliApplication.class);
    }
    @Override
    public void run(String... arg0) {
        storageService.deleteAll();
        storageService.init();
        if (userEntityRepository.count() == 0) {
            createAdminService.createAdmin();
            accountRegistrationService.registerAccount("damir.kozhakhmetov@gmail.com", "123");
            accountRegistrationService.registerAccount("andas.mamin@nu.edu.kz", "123");
            accountRegistrationService.registerAccount("aruzhan.takhminova@mail.ru", "123");
            accountRegistrationService.registerAccount("aizada@gmail.com", "123");
        }
    if (userInformationRepository.count() == 0){
        screeningFormService.fillTemplate(new ScreeningRequest("Adam", "Inov", "Batyrbekuly",
                32, 2), 1L);
        screeningFormService.fillTemplate(new ScreeningRequest("Damir", "Kozhakhmetov", "Alibekovich",
                22, 3), 2L);
        screeningFormService.fillTemplate(new ScreeningRequest("Andas", "Mamin", "Zholabekovich",
                27, 4), 3L);
        screeningFormService.fillTemplate(new ScreeningRequest("Aruzhan", "Tkhminova", "Zhaneldinovna",
                23, 1), 4L);
        screeningFormService.fillTemplate(new ScreeningRequest("Aizada", "Turarova", "Myrzagalikyzy",
                24, 0), 5L);
    }
    }
}
