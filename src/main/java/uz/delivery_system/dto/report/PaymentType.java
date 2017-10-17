package uz.delivery_system.dto.report;

/**
 * Created by Nodirbek on 04.10.2017.
 */
public class PaymentType {

    private int cash;

    private int cards;

    private int account;

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCards() {
        return cards;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void chargeCash(int x){
        cash += x;
    }

    public void chargeCards(int x){
        cards += x;
    }

    public void chargeAccount(int x){
        account += x;
    }
}
