package wallyson.com.br.mypocket.presenter;

/**
 * Created by wally on 16/07/16.
 */
public interface InitialInterface {
    String getName();
    String getEmail();
    void registrationError();
    void successfullyInserted();
    void DatabaseInsertError();
}
