package com.example.mycli.services;

import com.example.mycli.entity.*;
import com.example.mycli.model.SubjectType;
import com.example.mycli.repository.RoleEntityRepo;
import com.example.mycli.repository.UserEntityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class CreateEmptyMenteesServiceImpl implements CreateEmptyMenteesService {
    private final UserEntityRepo userEntityRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void createEmptyMentees() {
        log.info("creating mentees ...");
            RoleEntity userRoleEntity = roleEntityRepo.findByName("ROLE_MENTEE");
            AuthData SabitData = AuthData.builder()
                    .email("sabit@gmail.com")
                    .password(passwordEncoder.encode("string"))
                    .roleEntity(userRoleEntity)
                    .firstTime(false)
                    .build();
            List<SubjectType> sabitMajors = new ArrayList<>();
            sabitMajors.add(SubjectType.BIOLOGY);
            sabitMajors.add(SubjectType.INFORMATICS);
            Ranking sabitRanking = Ranking.builder()
                    .overallRanking(5)
//                    .id(1L)
                    .build();

            UserEntity userSabit = UserEntity.builder()
                    .authdata(SabitData)
                    .fullName("Sabit Kurmashev")
                    .active(true)
//                    .id(1L)
                    .ranking(sabitRanking)
                    .subjectTypeList(sabitMajors)
                    .build();
            UserInformation SabitInfo = UserInformation.builder()
                    .city("Los-Angeles")
                    .phoneNumber("877777777")
                    .school("NIS Astana")
                    .university("Wisconsin")
//                    .id(1L)
                    .IIN("01012312839")
                    .build();
            userSabit.setUserInformation(SabitInfo);
            UserEntity Sabit = userService.saveUser(userSabit);

//
        AuthData NauryzbayData = AuthData.builder()
                .email("nauryzbay@gmail.com")
                .password(passwordEncoder.encode("string"))
                .roleEntity(userRoleEntity)
                .firstTime(false)
                .build();
        List<SubjectType> nauryzbayMajors = new ArrayList<>();
        nauryzbayMajors.add(SubjectType.PHYSICS);
        nauryzbayMajors.add(SubjectType.INFORMATICS);
        Ranking nauryzbayRanking = Ranking.builder()
                .overallRanking(5)
//                .id(2L)
                .build();
        UserEntity userNauryzbay = UserEntity.builder()
                .authdata(NauryzbayData)
                .fullName("Nauryzbay Manap")
                .active(true)
//                .id(2L)
                .ranking(nauryzbayRanking)
                .subjectTypeList(nauryzbayMajors)
                .build();
        UserInformation NauryzbayInfo = UserInformation.builder()
                .city("Astana")
                .phoneNumber("88881231123")
                .school("NIS Aqtau")
//                .id(2L)
                .university("ENU")
                .IIN("000121212")
                .build();
        userNauryzbay.setUserInformation(NauryzbayInfo);
        UserEntity Ramazan = userService.saveUser(userNauryzbay);


        AuthData BaglanData = AuthData.builder()
                .email("baglan@gmail.com")
                .password(passwordEncoder.encode("string"))
                .roleEntity(userRoleEntity)
                .firstTime(false)
                .build();
        List<SubjectType> baglanMajors = new ArrayList<>();
        baglanMajors.add(SubjectType.MATH);
        baglanMajors.add(SubjectType.INFORMATICS);
        Ranking baglanRanking = Ranking.builder()
                .overallRanking(5)
//                .id(3L)
                .build();
        UserEntity userBaglan = UserEntity.builder()
                .authdata(BaglanData)
                .fullName("Baglan Issak")
                .active(true)
//                .id(3L)
                .ranking(baglanRanking)
                .subjectTypeList(baglanMajors)
                .build();
        UserInformation BaglanInfo = UserInformation.builder()
                .city("Alamty")
                .phoneNumber("8123123123")
                .school("NIS Almaty")
//                .id(3L)
                .university("Satbayev")
                .IIN("000121212")
                .build();
        userBaglan.setUserInformation(BaglanInfo);
        UserEntity Baglan = userService.saveUser(userBaglan);


        AuthData DanaData = AuthData.builder()
                .email("dana@gmail.com")
                .password(passwordEncoder.encode("string"))
                .roleEntity(userRoleEntity)
                .firstTime(false)
                .build();
        List<SubjectType> danaMajors = new ArrayList<>();
        danaMajors.add(SubjectType.HISTORY);
        danaMajors.add(SubjectType.INFORMATICS);
        Ranking danaRanking = Ranking.builder()
                .overallRanking(5)
//                .id(4L)
                .build();
        UserEntity userDana = UserEntity.builder()
                .authdata(DanaData)
                .fullName("Dana Makarova")
                .active(true)
//                .id(4L)
                .ranking(danaRanking)
                .subjectTypeList(danaMajors)
                .build();
        UserInformation danaInfo = UserInformation.builder()
                .city("Astana")
                .phoneNumber("8123123123")
                .school("NIS Astana")
//                .id(4L)
                .university("NU")
                .IIN("123123123")
                .build();
        userDana.setUserInformation(danaInfo);
        UserEntity Dana = userService.saveUser(userDana);
    }
}
