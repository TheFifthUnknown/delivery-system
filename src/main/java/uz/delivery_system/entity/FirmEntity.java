package uz.delivery_system.entity;

import uz.delivery_system.entity.base.UpdatableBaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nodirbek on 07.07.2017.
 */
@Entity
@Table(name = TableName.FIRMS)
public class FirmEntity extends UpdatableBaseEntity{

    @Column(name = "firmName", nullable = false)
    private String firmName;

    @Column(name = "firmAddress")
    private String firmAddress;

    @Column(name = "firmContact", nullable = false)
    private String firmContact;

    @Column(name = "firmExtraContact")
    private String firmExtraContact;

    @Column(name = "firmEmail")
    private String firmEmail;

    @Column(name = "firmLogoUrl")
    private String firmLogoUrl;

    @Column(name = "blocked", columnDefinition = "boolean default FALSE")
    private Boolean blocked = Boolean.FALSE;

    @Column(name = "deliveriable", columnDefinition = "boolean default TRUE")
    private boolean deliveriable = Boolean.TRUE;

    @Column(name = "shopContractNumber")
    private Long shopContractNumber = 0l;

    @Column(name = "dpxContractNumber")
    private Long dpxContractNumber = 0l;

    @OneToOne
    private UserEntity maneger;

    @OneToMany(mappedBy = "firm", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }

    public String getFirmContact() {
        return firmContact;
    }

    public void setFirmContact(String firmContact) {
        this.firmContact = firmContact;
    }

    public String getFirmExtraContact() {
        return firmExtraContact;
    }

    public void setFirmExtraContact(String firmExtraContact) {
        this.firmExtraContact = firmExtraContact;
    }

    public String getFirmEmail() {
        return firmEmail;
    }

    public void setFirmEmail(String firmEmail) {
        this.firmEmail = firmEmail;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public UserEntity getManeger() {
        return maneger;
    }

    public void setManeger(UserEntity maneger) {
        this.maneger = maneger;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public String getFirmLogoUrl() {
        return firmLogoUrl;
    }

    public void setFirmLogoUrl(String firmLogoUrl) {
        this.firmLogoUrl = firmLogoUrl;
    }

    public Long getShopContractNumber() {
        return shopContractNumber;
    }

    public void setShopContractNumber(Long shopContractNumber) {
        this.shopContractNumber = shopContractNumber;
    }

    public Long getDpxContractNumber() {
        return dpxContractNumber;
    }

    public void setDpxContractNumber(Long dpxContractNumber) {
        this.dpxContractNumber = dpxContractNumber;
    }

    public boolean isDeliveriable() {
        return deliveriable;
    }

    public void setDeliveriable(boolean deliveriable) {
        this.deliveriable = deliveriable;
    }
}
