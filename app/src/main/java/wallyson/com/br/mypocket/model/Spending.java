package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 28/06/16.
 */

import java.util.Date;

public class Spending {
    private int spendingCod;
    private String description, category;
    private double amount;
    private String emissionDate;

    public Spending(int spendingCod, String description, String category, double amount, String emissionDate) {
        this.spendingCod = spendingCod;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.emissionDate = emissionDate;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(String emissionDate) {
        this.emissionDate = emissionDate;
    }
}
