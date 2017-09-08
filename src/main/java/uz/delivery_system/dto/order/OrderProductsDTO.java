package uz.delivery_system.dto.order;

/**
 * Created by Nodirbek on 02.09.2017.
 */
public class OrderProductsDTO {

    private Long productId;

    private String productName;

    private int productCount;

    private String productSaleType;

    private Boolean productAccepted;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getProductSaleType() {
        return productSaleType;
    }

    public void setProductSaleType(String productSaleType) {
        this.productSaleType = productSaleType;
    }

    public Boolean getProductAccepted() {
        return productAccepted;
    }

    public void setProductAccepted(Boolean productAccepted) {
        this.productAccepted = productAccepted;
    }
}