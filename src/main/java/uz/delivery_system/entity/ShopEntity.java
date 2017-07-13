package uz.delivery_system.entity;

import uz.delivery_system.entity.base.UpdatableBaseEntity;
import uz.delivery_system.utils.TableName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Nodirbek on 11.07.2017.
 */
@Entity
@Table(name = TableName.SHOPS)
public class ShopEntity extends UpdatableBaseEntity {

    @Column(name = "shopName", nullable = false)
    private String shopName;

    @Column(name = "shopAddress")
    private String shopAddress;

    @Column(name = "shopContact", nullable = false)
    private String shopContact;

    @Column(name = "shopExtraContact")
    private String shopExtraContact;

    @Column(name = "shopEmail")
    private String shopEmail;

    @Column(name = "blocked", columnDefinition = "boolean default FALSE")
    private Boolean blocked = Boolean.FALSE;

    @Column(name = "shopRegionId")
    private Long shopRegionId;

    @OneToOne
    private UserEntity maneger;

    @OneToOne
    private RegionEntity region;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopContact() {
        return shopContact;
    }

    public void setShopContact(String shopContact) {
        this.shopContact = shopContact;
    }

    public String getShopExtraContact() {
        return shopExtraContact;
    }

    public void setShopExtraContact(String shopExtraContact) {
        this.shopExtraContact = shopExtraContact;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Long getShopRegionId() {
        return shopRegionId;
    }

    public void setShopRegionId(Long shopRegionId) {
        this.shopRegionId = shopRegionId;
    }

    public UserEntity getManeger() {
        return maneger;
    }

    public void setManeger(UserEntity maneger) {
        this.maneger = maneger;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }
}
