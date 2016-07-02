package wallyson.com.br.mypocket.model;

import java.util.ArrayList;

/**
 * Created by wally on 28/06/16.
 */

public class Account {
    private String bankName;
    private double balance;
    private ArrayList<Card> cards;
    private int codUser;

    public Account(String bankName, double balance, int codUser) {
        this.bankName = bankName;
        this.balance = balance;
        cards = new ArrayList<>();
        this.codUser = codUser;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCodUser() {
        return codUser;
    }

    public void setCodUser(int codUser) {
        this.codUser = codUser;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
