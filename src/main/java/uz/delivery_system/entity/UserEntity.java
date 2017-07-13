package uz.delivery_system.entity;

import uz.delivery_system.entity.base.UpdatableBaseEntity;
import uz.delivery_system.utils.TableName;
import uz.delivery_system.enums.UserRole;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Sadullayev Nodirbek.
 */
@Entity
@Table(name = TableName.USERS)
public class UserEntity extends UpdatableBaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "userRole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "imieCode")
    private String imieCode;

    @Column(name = "serialNum")
    private String serialNum;

    @Column(name = "active", columnDefinition = "boolean default TRUE")
    private Boolean active = Boolean.TRUE;

    @Column(name = "lastPasswordChange")
    private Date lastPasswordChange;

    @Column(name = "language", columnDefinition = "varchar(2) default 'uz'")
    private String language = "uz";

    @OneToOne(cascade = CascadeType.ALL)
    private FirmEntity firm;

    @OneToOne(cascade = CascadeType.ALL)
    private ShopEntity shop;

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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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

    public String getImieCode() {
        return imieCode;
    }

    public void setImieCode(String imieCode) {
        this.imieCode = imieCode;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public FirmEntity getFirm() {
        return firm;
    }

    public void setFirm(FirmEntity firm) {
        this.firm = firm;
    }

    public String fullName() {
        return firstname+" "+lastname;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }
}
