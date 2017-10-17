package uz.delivery_system.service;

import uz.delivery_system.dto.report.ShortReportDTO;

/**
 * Created by Nodirbek on 04.10.2017.
 */
public interface ReportService {

    ShortReportDTO getDailyReport();

    ShortReportDTO getMonthlyReport();
}
