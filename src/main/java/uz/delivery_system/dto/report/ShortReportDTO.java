package uz.delivery_system.dto.report;

/**
 * Created by Nodirbek on 04.10.2017.
 */
public class ShortReportDTO {

    private PaymentType delivered;

    private PaymentType waiting;

    private PaymentType accepted;

    public ShortReportDTO() {
        delivered = new PaymentType();
        waiting = new PaymentType();
        accepted = new PaymentType();
    }

    public PaymentType getDelivered() {
        return delivered;
    }

    public void setDelivered(PaymentType delivered) {
        this.delivered = delivered;
    }

    public PaymentType getWaiting() {
        return waiting;
    }

    public void setWaiting(PaymentType waiting) {
        this.waiting = waiting;
    }

    public PaymentType getAccepted() {
        return accepted;
    }

    public void setAccepted(PaymentType accepted) {
        this.accepted = accepted;
    }
}
