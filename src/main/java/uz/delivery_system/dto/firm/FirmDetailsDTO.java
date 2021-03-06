package uz.delivery_system.dto.firm;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class FirmDetailsDTO extends ResourceSupport{

    private Long firmId;

    private String firmName;

    private String firmAddress;

    private String firmContact;

    private String firmExtraContact;

    private String firmEmail;

    private String managerName;

    private Long managerId;

    private Date createDate;

    private Boolean active;

    private boolean deliveriable;

    private Long dpxContractNumber;

    private String firmLogoUrl;

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

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

    public boolean isDeliveriable() {
        return deliveriable;
    }

    public void setDeliveriable(boolean deliveriable) {
        this.deliveriable = deliveriable;
    }

    public Long getDpxContractNumber() {
        return dpxContractNumber;
    }

    public void setDpxContractNumber(Long dpxContractNumber) {
        this.dpxContractNumber = dpxContractNumber;
    }

    public String getFirmLogoUrl() {
        return firmLogoUrl;
    }

    public void setFirmLogoUrl(String firmLogoUrl) {
        this.firmLogoUrl = firmLogoUrl;
    }

}
