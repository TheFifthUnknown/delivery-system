package uz.delivery_system.entity;

import uz.delivery_system.entity.base.BaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.*;

/**
 * Created by Nodirbek on 02.09.2017.
 */
@Entity
@Table(name = TableName.ORDER_PRODUCTS)
public class OrderProductEntity extends BaseEntity {

    @ManyToOne
    private OrderEntity order;

    @ManyToOne
    private ProductEntity product;

    @Column(name = "countProduct")
    private int countProduct;

    @Column(name = "priceProduct")
    private Long priceProduct;

    @Column(name = "accepted")
    private Boolean accepted = Boolean.TRUE;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Long getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Long priceProduct) {
        this.priceProduct = priceProduct;
    }
}
