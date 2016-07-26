package wallyson.com.br.mypocket.presenter;

/**
 * Created by wally on 18/07/16.
 */
public interface ConfigurationInterface {
    String getAccountSpinner();
    String getBalance();
    String getRenewalBalance();
    String getCardSpinner();
    String getCredit();
    String getRenewalCredit();
    void updatedSuccessfully();
    void databaseInsertError();
    void registrationError();

}
