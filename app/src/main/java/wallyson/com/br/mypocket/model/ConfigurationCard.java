package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 08/07/16.
 */
public class ConfigurationCard {
    private String cardName, receiptDate;
    private double credit;

    public ConfigurationCard(String cardName, double credit, String receiptDate ) {
        this.cardName = cardName;
        this.credit = credit;
        this.receiptDate = receiptDate;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
