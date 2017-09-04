package uz.delivery_system.dto.order;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Nodirbek on 02.09.2017.
 */
public class CreateOrderDTO {

    @NotNull
    private Long firmId;

    @NotEmpty
    private List<ProductCount> productCounts;

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

    public List<ProductCount> getProductCounts() {
        return productCounts;
    }

    public void setProductCounts(List<ProductCount> productCounts) {
        this.productCounts = productCounts;
    }
}
