package uz.delivery_system.entity.base;

import uz.delivery_system.entity.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class UpdatableBaseEntity extends BaseEntity implements Serializable {

    @Column(name = "createUserId")
    private Long createUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createUserId", insertable = false, updatable = false)
    private UserEntity createUser;

    @Column(name = "updateUserId")
    private Long updateUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updateUserId", insertable = false, updatable = false)
    private UserEntity updateUser;

    @Column(name = "createDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "updateDate")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public UserEntity getCreateUser() {
        return createUser;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public UserEntity getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(UserEntity updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
