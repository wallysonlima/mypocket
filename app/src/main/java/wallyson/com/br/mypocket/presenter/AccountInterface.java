package wallyson.com.br.mypocket.presenter;

/**
 * Created by wally on 30/06/16.
 */
public interface AccountInterface {
    String getBankName();
    String getReceiptDate();
    Double getBalance();
    void registrationError();
    void successfullyInserted();
    void databaseInsertError();
}
