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
public class CreateEmptyMentorsServiceImpl implements CreateEmptyMentorsService {
    private final UserEntityRepo userEntityRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void createEmptyMentors() {
        log.info("creating mentors ...");
            RoleEntity userRoleEntity = roleEntityRepo.findByName("ROLE_MENTOR");
            AuthData DiasData = AuthData.builder()
                    .email("dias@gmail.com")
                    .password(passwordEncoder.encode("string"))
                    .roleEntity(userRoleEntity)
                    .firstTime(false)
                    .build();
            List<SubjectType> diasMajors = new ArrayList<>();
            diasMajors.add(SubjectType.BIOLOGY);
            diasMajors.add(SubjectType.CHEMISTRY);
            Ranking diasRanking = Ranking.builder()
                    .overallRanking(1)
//                    .id(1L)
                    .build();

            UserEntity userDias = UserEntity.builder()
                    .authdata(DiasData)
                    .fullName("Dias Bagzat")
                    .active(true)
//                    .id(1L)
                    .ranking(diasRanking)
                    .subjectTypeList(diasMajors)
                    .build();
            UserInformation DiasInfo = UserInformation.builder()
                    .city("Almaty")
                    .phoneNumber("877777777")
                    .school("NIS Almaty")
                    .university("KBTU")
//                    .id(1L)
                    .IIN("01012312839")
                    .build();
            userDias.setUserInformation(DiasInfo);
            UserEntity Dias = userService.saveUser(userDias);

//
        AuthData RamazanData = AuthData.builder()
                .email("roma@gmail.com")
                .password(passwordEncoder.encode("string"))
                .roleEntity(userRoleEntity)
                .firstTime(false)
                .build();
        List<SubjectType> ramazanMajors = new ArrayList<>();
        ramazanMajors.add(SubjectType.PHYSICS);
        ramazanMajors.add(SubjectType.INFORMATICS);
        Ranking ramazanRanking = Ranking.builder()
                .overallRanking(5)
//                .id(2L)
                .build();
        UserEntity userRamazan = UserEntity.builder()
                .authdata(RamazanData)
                .fullName("Ramazan Ualdinuly")
                .active(true)
//                .id(2L)
                .ranking(ramazanRanking)
                .subjectTypeList(ramazanMajors)
                .build();
        UserInformation RamazanInfo = UserInformation.builder()
                .city("Almaty")
                .phoneNumber("88881231123")
                .school("NIS Nur-Sultan")
//                .id(2L)
                .university("NU")
                .IIN("000121212")
                .build();
        userRamazan.setUserInformation(RamazanInfo);
        UserEntity Ramazan = userService.saveUser(userRamazan);


        AuthData RuslanData = AuthData.builder()
                .email("ruslan@gmail.com")
                .password(passwordEncoder.encode("string"))
                .roleEntity(userRoleEntity)
                .firstTime(false)
                .build();
        List<SubjectType> ruslanMajors = new ArrayList<>();
        ruslanMajors.add(SubjectType.MATH);
        ruslanMajors.add(SubjectType.INFORMATICS);
        Ranking ruslanRanking = Ranking.builder()
                .overallRanking(5)
//                .id(3L)
                .build();
        UserEntity userRuslan = UserEntity.builder()
                .authdata(RuslanData)
                .fullName("Ruslan Gammasov")
                .active(true)
//                .id(3L)
                .ranking(ruslanRanking)
                .subjectTypeList(ruslanMajors)
                .build();
        UserInformation RuslanInfo = UserInformation.builder()
                .city("Semey")
                .phoneNumber("8123123123")
                .school("NIS Semey")
//                .id(3L)
                .university("NU")
                .IIN("000121212")
                .build();
        userRuslan.setUserInformation(RuslanInfo);
        UserEntity Ruslan = userService.saveUser(userRuslan);


        AuthData AigerimData = AuthData.builder()
                .email("aigerim@gmail.com")
                .password(passwordEncoder.encode("string"))
                .roleEntity(userRoleEntity)
                .firstTime(false)
                .build();
        List<SubjectType> aigerimMajors = new ArrayList<>();
        aigerimMajors.add(SubjectType.MATH);
        aigerimMajors.add(SubjectType.INFORMATICS);
        Ranking aigerimRanking = Ranking.builder()
                .overallRanking(5)
//                .id(4L)
                .build();
        UserEntity userAigerim = UserEntity.builder()
                .authdata(AigerimData)
                .fullName("Aigerim Abdurakhmanova")
                .active(true)
//                .id(4L)
                .ranking(aigerimRanking)
                .subjectTypeList(aigerimMajors)
                .build();
        UserInformation aigerimInfo = UserInformation.builder()
                .city("Almaty")
                .phoneNumber("8123123123")
                .school("NIS Almaty")
//                .id(4L)
                .university("NU")
                .IIN("123123123")
                .build();
        userAigerim.setUserInformation(aigerimInfo);
        UserEntity Aigerim = userService.saveUser(userAigerim);
    }
}
