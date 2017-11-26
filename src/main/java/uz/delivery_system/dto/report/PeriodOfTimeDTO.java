package uz.delivery_system.dto.report;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;

/**
 * Created by Nodirbek on 25.10.2017.
 */
public class PeriodOfTimeDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar lowerBound;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar upperBound;

    public Calendar getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Calendar lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Calendar getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Calendar upperBound) {
        this.upperBound = upperBound;
    }
}
