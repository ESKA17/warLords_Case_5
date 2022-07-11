package com.example.mycli.controllers;

import com.example.mycli.entity.News;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.model.NewsRequest;
import com.example.mycli.repository.NewsRepo;
import com.example.mycli.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private NewsService newsService;

    @PostMapping("/postNews")
    public void postNews(@RequestBody NewsRequest newsRequest){
        String text = newsRequest.getText();
        Long id = newsRequest.getId();
        newsService.addNews(text, id);
    }
    @GetMapping("/getNews")
    public News getNewsById(@RequestParam Long Id){
        return newsService.getNews(Id);
    }
}
