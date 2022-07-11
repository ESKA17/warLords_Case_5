package com.example.mycli.services;

import com.example.mycli.entity.News;
import com.example.mycli.model.NewsResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NewsService {
    void addNews(String news, HttpServletRequest httpServletRequest);

    List<Long> getAllUnacceptedNews();

    List<Long> getUnacceptedNewsByOP(HttpServletRequest httpServletRequest);

    List<Long> getAcceptedNewsByOP(HttpServletRequest httpServletRequest);

    News getNewsByID(Long id);

    NewsResponse getNewsResponseByID(Long id);

    void markAccepted(Long newsID, HttpServletRequest httpServletRequest);

    void markFinished(Long newsID, HttpServletRequest httpServletRequest);

    void editNews(Long newsID, String news);
}
