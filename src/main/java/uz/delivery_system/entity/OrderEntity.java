package uz.delivery_system.entity;

import org.springframework.format.annotation.DateTimeFormat;
import uz.delivery_system.entity.base.BaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = TableName.ORDERS)
public class OrderEntity extends BaseEntity{

    @OneToOne
    private FirmEntity firmEntity;

    @OneToOne
    private ShopEntity shopEntity;

    @Column(name = "registerNumber", nullable = false)
    private Long registerNumber;

    @Column(name = "deliverDate")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deliverDate;

    @Column(name = "orderedDate")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date orderedDate;

    @Column(name = "status", nullable = false)
    private short status = 0;

    @Column(name = "paymentType")
    private Integer paymentType = 0;

    @Column(name = "orderedProductsCount")
    private Integer orderedProductsCount;

    @Column(name = "orderedProductsCost")
    private Long orderedProductsCost;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderProductEntity> orderProducts;

    public Long getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Long registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public void setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }

    public FirmEntity getFirmEntity() {
        return firmEntity;
    }

    public void setFirmEntity(FirmEntity firmEntity) {
        this.firmEntity = firmEntity;
    }

    public List<OrderProductEntity> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductEntity> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Integer getOrderedProductsCount() {
        return orderedProductsCount;
    }

    public void setOrderedProductsCount(Integer orderedProductsCount) {
        this.orderedProductsCount = orderedProductsCount;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public Long getOrderedProductsCost() {
        return orderedProductsCost;
    }

    public void setOrderedProductsCost(Long orderedProductsCost) {
        this.orderedProductsCost = orderedProductsCost;
    }
}
