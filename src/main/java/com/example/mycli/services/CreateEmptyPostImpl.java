package com.example.mycli.services;

import com.example.mycli.entity.Connection;
import com.example.mycli.entity.News;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.ConnectionRepo;
import com.example.mycli.repository.NewsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log
public class CreateEmptyPostImpl implements CreateEmptyPost{
    private final NewsRepo newsRepo;
    @Override
    public void createPost() {
        News addedNews = News.builder()
                .accepted(false)
                .news("Someone help me with Programming assignment pls pls pls <3")
                .date(LocalDate.now())
                .userID(9L)
                .finished(false)
                .build();
        newsRepo.save(addedNews);

        News addedNews2 = News.builder()
                .accepted(false)
                .news("Could you please explain me alghorithms?")
                .date(LocalDate.now())
                .userID(7L)
                .finished(false)
                .build();
        newsRepo.save(addedNews2);

        News addedNews3 = News.builder()
                .accepted(false)
                .news("Продам Камри 55 3.5 литра")
                .date(LocalDate.now())
                .userID(7L)
                .finished(false)
                .build();
        newsRepo.save(addedNews3);
    }
}
