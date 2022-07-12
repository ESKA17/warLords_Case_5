package com.example.mycli.services;

import com.example.mycli.entity.UserEntity;
import com.example.mycli.model.RankingForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final UserService userService;
    @Override
    public void fillRankingForm(RankingForm rankingForm, HttpServletRequest httpServletRequest) {
        String email =  userService.getEmailFromToken(httpServletRequest);
        UserEntity userEntity = userService.findByAuthDataEmail(email);
    }
}
