package wallyson.com.br.mypocket.presenter;

/**
 * Created by wally on 02/07/16.
 */
public interface DeleteInterface {

    String getAccountSpinner();
    void successfullyDeleted();
    void databaseInsertError();
    String getCardSpinner();
}
