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

    @Column(name = "countProducts")
    private int countProducts;

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

    public int getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(int countProducts) {
        this.countProducts = countProducts;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
