package uz.delivery_system.dto.shop;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Nodirbek on 08.07.2017.
 */
public class ShopRegistrationDTO {

    @NotNull
    @Size(min=2, max=255)
    private String shopName;

    @NotNull
    @Size(min=2, max=255)
    private String shopAddress;

    private String shopOrienter;

    @NotNull
    @Size(min=2, max=50)
    private String shopContact;

    private String shopExtraContact;

    private String shopEmail;

    @NotNull
    private Long shopRegionId;

    @NotNull
    private String shopINN;

    @NotNull
    private String shopOwnerPassport;

    private Long dpxContractNumber = 0l;

    // Shop manager informations

    @NotNull
    @Size(min=2, max=50)
    private String username;

    @NotNull
    @Size(min=5, max=50)
    private String password;

    private String confirmPassword;

    @NotNull
    @Size(min=2, max=50)
    private String firstname;

    private String lastname;

    private String phone;

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

    public Long getShopRegionId() {
        return shopRegionId;
    }

    public void setShopRegionId(Long shopRegionId) {
        this.shopRegionId = shopRegionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getShopOrienter() {
        return shopOrienter;
    }

    public void setShopOrienter(String shopOrienter) {
        this.shopOrienter = shopOrienter;
    }

    public Long getDpxContractNumber() {
        return dpxContractNumber;
    }

    public void setDpxContractNumber(Long dpxContractNumber) {
        this.dpxContractNumber = dpxContractNumber;
    }
}
