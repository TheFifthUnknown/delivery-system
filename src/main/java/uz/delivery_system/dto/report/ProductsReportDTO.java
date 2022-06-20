package uz.delivery_system.dto.report;

public class ProductsReportDTO {

    private Long productId;

    private String productName;

    private Integer productCode;

    private String unitOfMeasurement;

    private String productBrandName;

    private int productCount;

    private long soldByCash;

    private long soldByCards;

    private long soldByAccount;

    private long totalPrice;

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

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public long getSoldByCash() {
        return soldByCash;
    }

    public void setSoldByCash(long soldByCash) {
        this.soldByCash = soldByCash;
    }

    public long getSoldByCards() {
        return soldByCards;
    }

    public void setSoldByCards(long soldByCards) {
        this.soldByCards = soldByCards;
    }

    public long getSoldByAccount() {
        return soldByAccount;
    }

    public void setSoldByAccount(long soldByAccount) {
        this.soldByAccount = soldByAccount;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void chargeCash(long sum){
        soldByCash +=sum;
    }

    public void chargeCards(long sum){
        soldByCards +=sum;
    }

    public void chargeAccaunt(long sum){
        soldByAccount +=sum;
    }

    public void chargeTotalPrice(long sum){
        totalPrice += sum;
    }

    public void chargeProductCount(int countProduct) {
        productCount += countProduct;
    }
}
