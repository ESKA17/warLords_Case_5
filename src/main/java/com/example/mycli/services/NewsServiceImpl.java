package com.example.mycli.services;

import com.example.mycli.entity.News;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.repository.NewsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService{
    private final UserService userService;
    private final NewsRepo newsRepo;
    @Override
    @Transactional
    public void addNews(String news, HttpServletRequest httpServletRequest) {
        log.info("adding new news ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity userEntity = userService.findByAuthDataEmail(email);
        News addedNews = News.builder()
                .accepted(false)
                .news(news)
                .dateTime(LocalDateTime.now())
                .userEntity(userEntity)
                .finished(false)
                .build();
        newsRepo.save(addedNews);
        log.info("news were saved in repository");
    }

    @Override
    public List<Long> getAllUnacceptedNews() {
        log.info("accessing database for all unaccepted news ...");
        List<News> allNews = newsRepo.findAllByAcceptedIsFalse();
        List<Long> listByID = new ArrayList<>();
        for (News news: allNews) {
            listByID.add(news.getId());
        }
        log.info("news successfully retrieved");
        return listByID;
    }

    @Override
    public List<Long> getUnacceptedNewsByOP(HttpServletRequest httpServletRequest) {
        log.info("retrieving unaccepted news by OP ... ");
        String email = userService.getEmailFromToken(httpServletRequest);
        List<News> allNews = newsRepo.findAllByAcceptedIsFalseAndUserEntity_Authdata_Email(email);
        List<Long> listByID = new ArrayList<>();
        for (News news: allNews) {
            listByID.add(news.getId());
        }
        log.info("news successfully retrieved");
        return listByID;
    }

    @Override
    public List<Long> getAcceptedNewsByOP(HttpServletRequest httpServletRequest) {
        log.info("retrieving accepted news by OP ... ");
        String email = userService.getEmailFromToken(httpServletRequest);
        List<News> allNews = newsRepo.findAllByAcceptedIsTrueAndFinishedIsFalseAndUserEntity_Authdata_Email(email);
        List<Long> listByID = new ArrayList<>();
        for (News news: allNews) {
            listByID.add(news.getId());
        }
        log.info("news successfully retrieved");
        return listByID;
    }

    @Override
    public News getNewsByID(Long id) {
        log.info("getting news by id ...");
        News news = newsRepo.findById(id).orElseThrow(() -> new AccountNotFound("news with id: " + id));
        log.info("successfully retrieved by id");
        return news;
    }
    @Transactional
    @Override
    public void markAccepted(Long newsID, HttpServletRequest httpServletRequest) {
     log.info("marking news as accepted ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity user = userService.findByEmail(email);

        News news = getNewsByID(newsID);
        news.setAccepted(true);
        news.setAccepter_id(user.getId());

        UserEntity poster = news.getUserEntity();

        List<UserEntity> posterConnections = poster.getConnections();
        List<UserEntity> userConnections = user.getConnections();
        userConnections.add(poster);
        posterConnections.add(user);
        poster.setConnections(posterConnections);
        user.setConnections(userConnections);
        userService.saveUser(poster);
        userService.saveUser(user);
        newsRepo.save(news);
     log.info("successfully marked as accepted");
    }
    @Transactional
    @Override
    public void markFinished(Long newsID, HttpServletRequest httpServletRequest) {
        log.info("marking news as finished ...");
        News news = getNewsByID(newsID);
        news.setFinished(true);
        newsRepo.save(news);
        log.info("successfully marked as finished");
    }
    @Transactional
    @Override
    public void editNews(Long newsID, String news) {
        log.info("editing news ...");
        News newsEdited = newsRepo.findById(newsID).orElseThrow(
                () -> new AccountNotFound("news with id: " + newsID));
        newsEdited.setNews(news);
        newsRepo.save(newsEdited);
        log.info("successfully edited");
    }


}
