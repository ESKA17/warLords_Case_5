package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.UserEntityRepo;
import com.example.mycli.web.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserEntityRepo userEntityRepo;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepo.findByEmail(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
}
