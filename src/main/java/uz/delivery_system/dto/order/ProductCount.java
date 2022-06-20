package uz.delivery_system.dto.order;

import javax.validation.constraints.NotNull;


public class ProductCount {

    @NotNull
    private Long productId;

    @NotNull
    private int count;

    @NotNull
    private Long price;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
