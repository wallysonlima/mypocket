package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 28/06/16.
 */

import java.util.Date;

public class Card {
    private double credit;
    private Date maturity;
    private String cardName;

    public Card(String cardName, double credit, Date maturity) {
        this.cardName = cardName;
        this.credit = credit;
        this.maturity = maturity;
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

}
