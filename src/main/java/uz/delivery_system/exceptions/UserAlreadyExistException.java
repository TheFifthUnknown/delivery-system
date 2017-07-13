package uz.delivery_system.exceptions;

import org.jetbrains.annotations.NonNls;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Nodirbek on 08.07.2017.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends RuntimeException{

    private Integer code;
    private String message;

    public UserAlreadyExistException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
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