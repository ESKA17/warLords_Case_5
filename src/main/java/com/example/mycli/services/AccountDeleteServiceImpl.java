package com.example.mycli.services;

import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDeleteServiceImpl implements AccountDeleteService{

    private final UserInformationRepository userInformationRepository;
    private final UserEntityRepository userEntityRepository;
    @Override
    public void deleteAccount(Long id) {
        if (userEntityRepository.findById(id).isPresent() ) {
            userEntityRepository.deleteById(id);
        }
        if (userInformationRepository.findById(id).isPresent()) {
            userInformationRepository.deleteById(id);
        }
    }
}
