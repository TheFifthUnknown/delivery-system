package uz.delivery_system.dto.shop;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
public class ShopDetailsDTO extends ResourceSupport{

    private Long shopId;
    private String shopName;
    private String shopAddress;
    private String shopContact;
    private String shopExtraContact;
    private String shopEmail;
    private String managerName;
    private Long managerId;
    private Date createDate;
    private Boolean active;
    private Long shopRegionId;
    private String shopRegionName;
    private String shopOrienter;
    private String shopINN;
    private String shopOwnerPassport;
    private Long dpxContractNumber;
    private String shopAccountNumber;
    private String shopMFO;
    private String shopActivity;
    private Integer shopType;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getShopRegionId() {
        return shopRegionId;
    }

    public void setShopRegionId(Long shopRegionId) {
        this.shopRegionId = shopRegionId;
    }

    public String getShopRegionName() {
        return shopRegionName;
    }

    public void setShopRegionName(String shopRegionName) {
        this.shopRegionName = shopRegionName;
    }

    public String getShopOrienter() {
        return shopOrienter;
    }

    public void setShopOrienter(String shopOrienter) {
        this.shopOrienter = shopOrienter;
    }

    public String getShopINN() {
        return shopINN;
    }

    public void setShopINN(String shopINN) {
        this.shopINN = shopINN;
    }

    public String getShopOwnerPassport() {
        return shopOwnerPassport;
    }

    public void setShopOwnerPassport(String shopOwnerPassport) {
        this.shopOwnerPassport = shopOwnerPassport;
    }

    public Long getDpxContractNumber() {
        return dpxContractNumber;
    }

    public void setDpxContractNumber(Long dpxContractNumber) {
        this.dpxContractNumber = dpxContractNumber;
    }

    public String getShopAccountNumber() {
        return shopAccountNumber;
    }

    public void setShopAccountNumber(String shopAccountNumber) {
        this.shopAccountNumber = shopAccountNumber;
    }

    public String getShopMFO() {
        return shopMFO;
    }

    public void setShopMFO(String shopMFO) {
        this.shopMFO = shopMFO;
    }

    public String getShopActivity() {
        return shopActivity;
    }

    public void setShopActivity(String shopActivity) {
        this.shopActivity = shopActivity;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }
}
