package com.example.mycli.services;

import com.example.mycli.entity.News;
import com.example.mycli.entity.Report;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.repository.ReportsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
@Slf4j
@RequiredArgsConstructor
public class ReportsServiceImpl implements ReportsService{
    private final UserService userService;
    private final ReportsRepo reportsRepo;
    @Override
    public void reportPerson(Long harasserId, String reason, HttpServletRequest httpServletRequest) {
        log.info("reporting person ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity reporter = userService.findByEmail(email);
        UserEntity harasser = userService.findUserByID(harasserId);
        Report newReport = Report.builder()
                .ignore(false)
                .reason(reason)
                .reporter(reporter)
                .harasser(harasser)
                .build();
        reportsRepo.save(newReport);
        log.info("report is saved ...");
    }

    @Override
    public void reportIgnore(Long reportId, HttpServletRequest httpServletRequest){
        log.info("ignoring report ...");
        String email = userService.getEmailFromToken(httpServletRequest);
        UserEntity harasser = userService.findByEmail(email);

        Report report = getReportById(reportId);

    }

    @Override
    public Report getReportById(Long reportId){
        log.info("getting report by id ...");
        Report report = reportsRepo.findById(reportId).orElseThrow(() -> new AccountNotFound("report with id: " + reportId));
        log.info("report successfully retrieved by id");
        return report;
    }
}
