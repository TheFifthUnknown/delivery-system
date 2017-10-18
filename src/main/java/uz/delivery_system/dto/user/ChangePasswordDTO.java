package uz.delivery_system.dto.user;

/**
 * Created by Nodirbek on 18.10.2017.
 */
public class ChangePasswordDTO {

    private String password;
    private String confirm;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
