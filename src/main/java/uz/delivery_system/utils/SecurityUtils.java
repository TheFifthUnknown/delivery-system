package uz.delivery_system.utils;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.delivery_system.enums.UserRole;
import uz.delivery_system.security.JwtUser;

/**
 * @author Dilshod Tadjiev.
 */
public class SecurityUtils {

    public static Long getUserId() {
        JwtUser user = getUserDetails();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    public static JwtUser getUserDetails() {
        AbstractAuthenticationToken authentication = (AbstractAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof JwtUser) {
            return (JwtUser) authentication.getPrincipal();
        }
        return null;
    }

    public static UserRole getUserRole() {
        AbstractAuthenticationToken authentication = (AbstractAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof JwtUser) {
            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            String authRole = jwtUser.getAuthorities().iterator().next().getAuthority();
            return UserRole.valueOf(authRole.substring(5));
        }
        return null;
    }

}
