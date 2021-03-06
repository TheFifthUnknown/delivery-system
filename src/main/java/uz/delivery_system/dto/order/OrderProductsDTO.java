package uz.delivery_system.dto.order;


public class OrderProductsDTO {

    private Long productId;

    private String productName;

    private int productCount;

    private Boolean productAccepted;

    private Long productPrice;

    private int productAmountInStore;

    private Integer productCode;

    private String unitOfMeasurement;

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

    public Boolean getProductAccepted() {
        return productAccepted;
    }

    public void setProductAccepted(Boolean productAccepted) {
        this.productAccepted = productAccepted;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAmountInStore() {
        return productAmountInStore;
    }

    public void setProductAmountInStore(int productAmountInStore) {
        this.productAmountInStore = productAmountInStore;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }
}