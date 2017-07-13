package uz.delivery_system.enums;

/**
 * @author Dilshod Tadjiev.
 */
public enum UserRole {
    ADMIN,
    SHOP_MANAGER,
    FIRM_ADMIN,
    FIRM_DELIVER;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
