package uz.delivery_system.exceptions;

import org.jetbrains.annotations.NonNls;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ConfirmPasswordException extends RuntimeException {

    private Integer code;
    private String message;

    public ConfirmPasswordException(Integer code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
