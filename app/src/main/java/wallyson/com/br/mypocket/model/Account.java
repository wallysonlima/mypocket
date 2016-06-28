package wallyson.com.br.mypocket.model;

import java.util.ArrayList;

/**
 * Created by wally on 28/06/16.
 */

public class Account {
    private String bankName;
    private double balance;
    private int accountNum;
    private ArrayList<Card> cards;

    public Account(String bankName, double balance, int accountNum) {
        this.bankName = bankName;
        this.balance = balance;
        this.accountNum = accountNum;
        cards = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
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
