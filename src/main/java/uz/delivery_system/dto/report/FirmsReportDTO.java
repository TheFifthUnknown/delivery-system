package uz.delivery_system.dto.report;

public class FirmsReportDTO {

    private Long firmId;

    private String firmName;

    private int orderCount;

    private long soldByCash;

    private long soldByCards;

    private long soldByAccount;

    private long totalPrice;

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

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public long getSoldByCash() {
        return soldByCash;
    }

    public void setSoldByCash(long soldByCash) {
        this.soldByCash = soldByCash;
    }

    public long getSoldByCards() {
        return soldByCards;
    }

    public void setSoldByCards(long soldByCards) {
        this.soldByCards = soldByCards;
    }

    public long getSoldByAccount() {
        return soldByAccount;
    }

    public void setSoldByAccount(long soldByAccount) {
        this.soldByAccount = soldByAccount;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void increaseOrderCount() {
        orderCount++;
    }

    public void chargeTotalPrice(long sum) {
        totalPrice += sum;
    }

    public void chargeCash(long sum){
        soldByCash +=sum;
    }

    public void chargeCards(long sum){
        soldByCards +=sum;
    }

    public void chargeAccaunt(long sum){
        soldByAccount +=sum;
    }

}
