package wallyson.com.br.mypocket.presenter;

/**
 * Created by wally on 16/07/16.
 */
public interface InitialInterface {
    String getName();
    String getEmail();
    String getBankName();
    Double getBalance();
    void registrationError();
    void successfullyInserted();
    void DatabaseInsertError();
}
