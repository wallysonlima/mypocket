package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 28/06/16.
 */

import java.util.Date;

public class Spending {
    private int spendingCod;
    private String description, category;
    private float amount;
    private String emissionDate, bankName, cardName;

    public Spending(int spendingCod, String description, float amount, String emissionDate, String category,
                    String bankName, String cardName) {
        this.spendingCod = spendingCod;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.emissionDate = emissionDate;
        this.bankName = bankName;
        this.cardName = cardName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getSpendingCod() {
        return spendingCod;
    }

    public void setSpendingCod(int spendingCod) {
        this.spendingCod = spendingCod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(String emissionDate) {
        this.emissionDate = emissionDate;
    }
}
