package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 08/07/16.
 */
public class ConfigurationAccount {
    private String bankName, receiptDate;
    private Double balance;

    public ConfigurationAccount(String bankName, String receiptDate, Double balance) {
        this.bankName = bankName;
        this.receiptDate = receiptDate;
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }
}
