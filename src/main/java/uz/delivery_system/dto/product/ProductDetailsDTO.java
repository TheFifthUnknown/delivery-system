package uz.delivery_system.dto.product;

import java.util.List;

/**
 * Created by Nodirbek on 16.07.2017.
 */
public class ProductDetailsDTO {

    private Long id;

    private String productName;

    private Long productPrice;

    private String productInfo;

    private String productCategory;

    private String productFirm;

    private String productLogoUrl;

    private List<SliderDetails> sliders;

    private String unitOfMeasurement;

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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductFirm() {
        return productFirm;
    }

    public void setProductFirm(String productFirm) {
        this.productFirm = productFirm;
    }

    public String getProductLogoUrl() {
        return productLogoUrl;
    }

    public void setProductLogoUrl(String productLogoUrl) {
        this.productLogoUrl = productLogoUrl;
    }

    public List<SliderDetails> getSliders() {
        return sliders;
    }

    public void setSliders(List<SliderDetails> sliders) {
        this.sliders = sliders;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
