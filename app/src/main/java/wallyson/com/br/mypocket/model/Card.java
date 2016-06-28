package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 28/06/16.
 */

import java.util.Date;

public class Card {
    private String flag;
    private double credit;
    private Date maturity;
    private int cardNum;

    public Card(String flag, double credit, Date maturity, int cardNum) {
        this.flag = flag;
        this.credit = credit;
        this.maturity = maturity;
        this.cardNum = cardNum;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getMaturity() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
