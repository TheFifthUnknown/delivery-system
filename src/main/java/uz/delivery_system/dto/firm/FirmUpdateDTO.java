package uz.delivery_system.dto.firm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Nodirbek on 08.07.2017.
 */
public class FirmUpdateDTO {

    @NotNull
    private Long firmId;

    @NotNull
    @Size(min=2, max=255)
    private String firmName;

    private String firmAddress;

    @NotNull
    @Size(min=2, max=50)
    private String firmContact;

    private String firmExtraContact;

    private String firmEmail;

    @NotNull
    private boolean deliveriable;

    @NotNull
    private Long dpxContractNumber;

    // Manager's info
    @NotNull
    @Size(min=2, max=50)
    private String firstname;

    @NotNull
    @Size(min=2, max=50)
    private String lastname;

    private String phone;

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
}
