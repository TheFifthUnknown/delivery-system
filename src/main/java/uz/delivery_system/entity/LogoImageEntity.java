package uz.delivery_system.entity;

import uz.delivery_system.entity.base.BaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = TableName.LOGO_URLS)
public class LogoImageEntity extends BaseEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @OneToOne
    private ProductEntity product;

    @Column(name = "uploadDate")
    @Temporal(value = TemporalType.DATE)
    private Date uploadDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

}
