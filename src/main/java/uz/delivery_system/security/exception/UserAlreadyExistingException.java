package uz.delivery_system.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistingException extends AuthenticationException {
    public UserAlreadyExistingException(String msg) {
        super(msg);
    }
}
