package uz.delivery_system.dto.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductAmountDTO {

    @NotNull
    private Long productId;

    @NotNull
    @Min(0)
    private Integer productAmount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

}
