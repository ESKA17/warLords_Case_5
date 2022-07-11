package com.example.mycli.services;

import com.example.mycli.entity.News;
import com.example.mycli.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {
    public void addNews(String text, Long id);
    public void getAllNews();
    public News getNews(Long id);
    public void acceptNews();
}
