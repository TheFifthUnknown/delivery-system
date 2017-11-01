package uz.delivery_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.delivery_system.dto.report.*;
import uz.delivery_system.service.ReportService;

import java.util.List;

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
    public ResponseEntity<?> mothlyReport() {
        ShortReportDTO dto = reportService.getMonthlyReport();
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> productsReport(@RequestBody @Validated PeriodOfTimeDTO dto){
        List<ProductsReportDTO> list = reportService.getReportByProducts(dto);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shop", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> shopsReport(@RequestBody @Validated PeriodOfTimeDTO dto){
        List<ShopsReportDTO> list = reportService.getReportByShops(dto);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/firm", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> firmsReport(@RequestBody @Validated PeriodOfTimeDTO dto){
        List<FirmsReportDTO> list = reportService.getReportByFirms(dto);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/firm/{firmId}/product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> firmsReport(@RequestBody @Validated PeriodOfTimeDTO dto, @PathVariable Long firmId){
        List<ProductsReportDTO> list = reportService.getReportByFirmProducts(firmId, dto);
        return ResponseEntity.ok(list);
    }

}
