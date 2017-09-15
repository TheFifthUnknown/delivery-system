package uz.delivery_system.entity;

import uz.delivery_system.dto.product.SliderDetails;
import uz.delivery_system.entity.base.BaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nodirbek on 15.07.2017.
 */
@Entity
@Table(name = TableName.PRODUCTS)
public class ProductEntity extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private Long productPrice;

    @Column(name = "info")
    private String productInfo;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SliderImageEntity> slides;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LogoImageEntity productLogo;

    @Column(name = "unitOfMeasurement")
    private String unitOfMeasurement;

    @Column(name = "categoryId")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", updatable = false, insertable = false,
    referencedColumnName = "id")
    private CategoryEntity category;

    @Column(name = "firmId")
    private Long firmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmId", updatable = false, insertable = false,
            referencedColumnName = "id")
    private FirmEntity firm;

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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

    public FirmEntity getFirm() {
        return firm;
    }

    public void setFirm(FirmEntity firm) {
        this.firm = firm;
    }

    public List<SliderImageEntity> getSlides() {
        return slides;
    }

    public void setSlides(List<SliderImageEntity> slides) {
        this.slides = slides;
    }

    public List<SliderDetails> getSliderDetails(){
        List<SliderDetails> list = new ArrayList<>();
        for (SliderImageEntity entity :
                slides) {
            list.add(new SliderDetails(entity.getId(),entity.getUrl(), entity.getTitle()));
        }
        return list;
    }

    public String getLogoUrl() {
        return productLogo.getUrl();
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public LogoImageEntity getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(LogoImageEntity productLogo) {
        this.productLogo = productLogo;
    }

}
