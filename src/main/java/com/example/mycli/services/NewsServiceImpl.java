package com.example.mycli.services;

import com.example.mycli.entity.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log
public class NewsServiceImpl implements NewsService{

    @Override
    public void addNews(String text, Long id) {

    }

    @Override
    public void getAllNews() {

    }

    @Override
    public News getNews(Long id) {

        return null;
    }

    @Override
    public void acceptNews() {

    }
}
