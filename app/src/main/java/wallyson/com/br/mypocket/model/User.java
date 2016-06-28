package wallyson.com.br.mypocket.model;

import java.util.ArrayList;

/**
 * Created by wally on 28/06/16.
 */
public class User {
    private String name, email;
    private int codUser;
    private ArrayList<Account> accounts;
    private ArrayList<Spending> spendings;

    public User(String name, String email, int codUser) {
        this.name = name;
        this.email = email;
        this.codUser = codUser;
        accounts = new ArrayList<>();
        spendings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodUser() {
        return codUser;
    }

    public void setCodUser(int codUser) {
        this.codUser = codUser;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Spending> getSpendings() {
        return spendings;
    }

}
