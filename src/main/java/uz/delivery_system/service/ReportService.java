package uz.delivery_system.service;

import uz.delivery_system.dto.report.*;

import java.util.List;

/**
 * Created by Nodirbek on 04.10.2017.
 */
public interface ReportService {

    ShortReportDTO getDailyReport();

    ShortReportDTO getMonthlyReport();

    List<ProductsReportDTO> getReportByProducts(PeriodOfTimeDTO dto);

    List<ShopsReportDTO> getReportByShops(PeriodOfTimeDTO dto);

    List<FirmsReportDTO> getReportByFirms(PeriodOfTimeDTO dto);

    List<ProductsReportDTO> getReportByFirmProducts(Long firmId, PeriodOfTimeDTO dto);
}
