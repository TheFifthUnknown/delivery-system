package uz.delivery_system.dto.order;

import java.util.Date;

/**
 * Created by Nodirbek on 02.09.2017.
 */
public class OrderDTO {

    private Long orderId;

    private Long registerNumber;

    private short status;

    private int count;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Long registerNumber) {
        this.registerNumber = registerNumber;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
