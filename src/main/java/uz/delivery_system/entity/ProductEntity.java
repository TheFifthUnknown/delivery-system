package uz.delivery_system.entity;

import uz.delivery_system.entity.base.BaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.*;

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

//    private List<String> productImageUrls;

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

}
