package uz.delivery_system.dto.product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Nodirbek on 15.07.2017.
 */
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String productName;

    @NotNull
    private Long productPrice;

    private String productInfo;

    @NotNull
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
