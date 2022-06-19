package uz.delivery_system.service;

import uz.delivery_system.dto.category.CategoryDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Nodirbek on 13.07.2017.
 */
public interface CategoryService {

    void create(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void delete(Long id);

    List<CategoryDTO> listCategories();

    /**
     * Created by Nodirbek on 08.07.2017.
     */
    class ShopUpdateDTO {

        @NotNull
        private Long shopId;

        @NotNull
        @Size(min=2, max=255)
        private String shopName;

        private String shopAddress;

        private String shopOrienter;

        @NotNull
        @Size(min=2, max=50)
        private String shopContact;

        private String shopExtraContact;

        private String shopEmail;

        @NotNull
        private String shopINN;

        @NotNull
        private String shopOwnerPassport;

        @NotNull
        private Long shopRegionId;

        private String shopAccountNumber;

        private String shopMFO;

        private String shopActivity;

        private Integer shopType;


        // Manager's info
        @NotNull
        @Size(min=2, max=50)
        private String firstname;

        @NotNull
        @Size(min=2, max=50)
        private String lastname;

        private String phone;

        @NotNull
        private Long dpxContractNumber;

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

        public Long getShopRegionId() {
            return shopRegionId;
        }

        public void setShopRegionId(Long shopRegionId) {
            this.shopRegionId = shopRegionId;
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
}
