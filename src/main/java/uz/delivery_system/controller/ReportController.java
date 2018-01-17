package uz.delivery_system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "Hisobotlar")
@RestController
@RequestMapping(value = "reports/")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ApiOperation(value = "kunlik hisobot", notes = "Firmani kunlik hisoboti, qisqa ko'rinishda")
    @RequestMapping(method = RequestMethod.GET, value = "/daily", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ShortReportDTO> dailyReport(){
        ShortReportDTO dto = reportService.getDailyReport();
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "oylik hisobot", notes = "Firmani oylik hisoboti, qisqa ko'rinishda")
    @RequestMapping(method = RequestMethod.GET, value = "/monthly", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ShortReportDTO> mothlyReport() {
        ShortReportDTO dto = reportService.getMonthlyReport();
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "maxsulotlar bo'yicha hisobot", notes = "Firma maxsulotlari bo'yicha berilgan vaqt oralig'idagi hisobot" +
            " vaqt oraligi yyyy-MM-dd formatda berilishi kerak")
    @RequestMapping(method = RequestMethod.POST, value = "/product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductsReportDTO>> productsReport(@RequestBody @Validated PeriodOfTimeDTO dto){
        List<ProductsReportDTO> list = reportService.getReportByProducts(dto);
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Do'kon bo'yicha hisobot", notes = "Do'konlar bo'yicha berilgan vaqt oralig'idagi hisobot" +
            " vaqt oraligi yyyy-MM-dd formatda berilishi kerak")
    @RequestMapping(method = RequestMethod.POST, value = "/shop", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ShopsReportDTO>> shopsReport(@RequestBody @Validated PeriodOfTimeDTO dto){
        List<ShopsReportDTO> list = reportService.getReportByShops(dto);
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Firmalar bo'yicha hisobot", notes = "Firmalar bo'yicha berilgan vaqt oralig'idagi hisobot" +
            " vaqt oraligi yyyy-MM-dd formatda berilishi kerak")
    @RequestMapping(method = RequestMethod.POST, value = "/firm", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<FirmsReportDTO>> firmsReport(@RequestBody @Validated PeriodOfTimeDTO dto){
        List<FirmsReportDTO> list = reportService.getReportByFirms(dto);
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Firma maxsulotlari bo'yicha hisobot", notes = "Firma maxsulotlari bo'yicha berilgan vaqt oralig'idagi hisobot" +
            " vaqt oraligi yyyy-MM-dd formatda berilishi kerak")
    @RequestMapping(method = RequestMethod.POST, value = "/firm/{firmId}/product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductsReportDTO>> firmsReport(@RequestBody @Validated PeriodOfTimeDTO dto, @PathVariable Long firmId){
        List<ProductsReportDTO> list = reportService.getReportByFirmProducts(firmId, dto);
        return ResponseEntity.ok(list);
    }

}
