package com.example.mycli.services;

import com.example.mycli.entity.Report;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ReportsService {

    void reportPerson(Long id, String reason, HttpServletRequest httpServletRequest);

    @Transactional
    @Async
    void sendingNotificationReport() throws MessagingException, UnsupportedEncodingException;

    void reportIgnore(Long reportId, HttpServletRequest httpServletRequest);

    Report getReportById(Long reportId);

    List<Long> getReportsAll();
}
