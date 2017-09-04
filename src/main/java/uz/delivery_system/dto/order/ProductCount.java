package uz.delivery_system.dto.order;

import javax.validation.constraints.NotNull;

/**
 * Created by Nodirbek on 02.09.2017.
 */
public class ProductCount {

    @NotNull
    private Long productId;

    @NotNull
    private int count;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
