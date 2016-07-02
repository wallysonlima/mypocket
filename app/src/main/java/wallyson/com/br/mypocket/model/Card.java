package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 28/06/16.
 */

import java.util.Date;

public class Card {
    private double credit;
    private String maturity;
    private String cardName;
    private String bankName;

    public Card(String cardName, double credit, String maturity, String bankName) {
        this.cardName = cardName;
        this.credit = credit;
        this.maturity = maturity;
        this.bankName = bankName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
