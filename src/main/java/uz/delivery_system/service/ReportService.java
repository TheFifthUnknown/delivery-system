package uz.delivery_system.service;

import uz.delivery_system.dto.report.PeriodOfTimeDTO;
import uz.delivery_system.dto.report.ProductsReportDTO;
import uz.delivery_system.dto.report.ShortReportDTO;

import java.util.List;

/**
 * Created by Nodirbek on 04.10.2017.
 */
public interface ReportService {

    ShortReportDTO getDailyReport();

    ShortReportDTO getMonthlyReport();

    List<ProductsReportDTO> getReportByProduct(PeriodOfTimeDTO dto);
}
