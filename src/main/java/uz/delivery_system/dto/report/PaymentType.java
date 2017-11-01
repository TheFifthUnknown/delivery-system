package uz.delivery_system.dto.report;

/**
 * Created by Nodirbek on 04.10.2017.
 */
public class PaymentType {

    private long cash;

    private long cards;

    private long account;

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public long getCards() {
        return cards;
    }

    public void setCards(long cards) {
        this.cards = cards;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public void chargeCash(long x){
        cash += x;
    }

    public void chargeCards(long x){
        cards += x;
    }

    public void chargeAccount(long x){
        account += x;
    }
}
