package uz.delivery_system.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Nodirbek on 08.07.2017.
 */
@ResponseStatus()
public class NotFoundException extends RuntimeException {

    private Integer code;
    private String message;

    public NotFoundException(Integer code, String message) {
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
