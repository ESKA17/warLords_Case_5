package com.example.mycli.controllers;

import com.example.mycli.entity.News;
import com.example.mycli.entity.Report;
import com.example.mycli.model.NewsRequest;
import com.example.mycli.model.ReportRequest;
import com.example.mycli.services.NewsService;
import com.example.mycli.services.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reports")
public class ReportsController {
    private final ReportsService reportsService;

    @PostMapping("/report_by_id")
    public ResponseEntity<String> report(@RequestBody ReportRequest reportRequest, HttpServletRequest httpServletRequest){
        reportsService.reportPerson(reportRequest.getReportedPersonId(), reportRequest.getReason(), httpServletRequest);
        return ResponseEntity.ok(reportRequest.getReportedPerson() +" is reported");
    }

    @PostMapping("/mark_ignore")
    public ResponseEntity<String> ignoreReport(@RequestParam Long reportId, HttpServletRequest httpServletRequest){
        reportsService.reportIgnore(reportId, httpServletRequest);
        return ResponseEntity.ok(reportId + " is ignored");
    }
    @GetMapping("/by_id")
    public Report getNewsByID(Long reportId){
        return reportsService.getReportById(reportId);
   }



//    @GetMapping("/available")
//    public List<Long> getAllUnacceptedNews(){
//        return newsService.getAllUnacceptedNews();
//    }
//
//    @GetMapping("/by_op_unaccepted")
//    public List<Long> getUnacceptedNewsByOP(HttpServletRequest httpServletRequest){
//        return newsService.getUnacceptedNewsByOP(httpServletRequest);
//    }
//
//    @GetMapping("/by_op_accepted")
//    public List<Long> getAcceptedNewsByOP(HttpServletRequest httpServletRequest){
//        return newsService.getAcceptedNewsByOP(httpServletRequest);
//    }
//

//
//    @PostMapping("/mark_accepted")
//    public ResponseEntity<String> markAccepted(@RequestParam Long newsID, HttpServletRequest httpServletRequest) {
//        newsService.markAccepted(newsID, httpServletRequest);
//        return ResponseEntity.ok("marked as accepted");
//    }
//
//    @PostMapping("/mark_finished")
//    public ResponseEntity<String> markFinished(@RequestParam Long newsID, HttpServletRequest httpServletRequest) {
//        newsService.markFinished(newsID, httpServletRequest);
//        return ResponseEntity.ok("marked as finished");
//    }
//    @PostMapping("/edit_news")
//    public ResponseEntity<String> editNews(@RequestParam Long newsID, @RequestBody NewsRequest newsRequest) {
//        newsService.editNews(newsID, newsRequest.getNews());
//        return ResponseEntity.ok("news were edited");
//    }
}
