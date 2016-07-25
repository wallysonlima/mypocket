package wallyson.com.br.mypocket.presenter;

/**
 * Created by wally on 04/07/16.
 */
public interface SpendingInterface {
    String getDescription();
    String getAmount();
    String getEmissionDate();
    String getCategory();
    String getAccount();
    String getCard();
    String getChoose();
    void successfullyInserted();
    void databaseInsertError();
    void registrationError();
}
