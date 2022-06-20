package uz.delivery_system.entity;

import uz.delivery_system.entity.base.BaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = TableName.SLIDERS)
public class SliderImageEntity extends BaseEntity{

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
