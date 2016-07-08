package wallyson.com.br.mypocket.presenter;

/**
 * Created by wally on 01/07/16.
 */
public interface CardInterface {
    String getBankName();
    void registrationError();
    void successfullyInserted();
    void databaseInsertError();
    String getCardName();
    Double getCredit();
}
