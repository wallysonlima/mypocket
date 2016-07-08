package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 08/07/16.
 */
public class ConfigurationCard {
    private String cardName, receiptDate;
    private Double credit;

    public ConfigurationCard(String cardName, String receiptDate, Double credit) {
        this.cardName = cardName;
        this.receiptDate = receiptDate;
        this.credit = credit;
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

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
