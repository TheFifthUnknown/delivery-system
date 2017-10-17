package uz.delivery_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.delivery_system.dto.report.ShortReportDTO;
import uz.delivery_system.service.ReportService;

/**
 * Created by Nodirbek on 04.10.2017.
 */
@RestController
@RequestMapping(value = "reports/")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET, value = "/daily", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> dailyReport(){
        ShortReportDTO dto = reportService.getDailyReport();
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/monthly", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> mothlyReport(){
        ShortReportDTO dto = reportService.getMonthlyReport();
        return ResponseEntity.ok(dto);
    }




}
