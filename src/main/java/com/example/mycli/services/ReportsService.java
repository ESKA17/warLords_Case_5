package com.example.mycli.services;

import com.example.mycli.entity.Report;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReportsService {

    void reportPerson(Long id, String reason, HttpServletRequest httpServletRequest);

    void reportIgnore(Long reportId, HttpServletRequest httpServletRequest);

    Report getReportById(Long reportId);

    List<Long> getReportsAll();
}
