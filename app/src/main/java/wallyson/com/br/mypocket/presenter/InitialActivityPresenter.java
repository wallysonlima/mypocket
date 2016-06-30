package wallyson.com.br.mypocket.presenter;

import android.content.Context;
import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.UserDao;


/**
 * Created by wally on 30/06/16.
 */
public class InitialActivityPresenter {
    private InitialView mView;
    Context c;
    UserDao user;
    AccountDao account;

    public InitialActivityPresenter(InitialView v, Context context) {
        mView = v;
        c = context;
    }

    public void initialRegistration() {
        String name = mView.getName();
        String email = mView.getEmail();
        String bankName = mView.getBankName();
        Double balance = mView.getBalance();
        boolean result1, result2;

        if ( name.equals(null) && email.equals(null) && bankName.equals(null) && balance == 0 ) {
            mView.registrationError();
        } else {
            user = new UserDao(c);
            account = new AccountDao(c);

            result1 = user.insertUser(name, email);
            result2 = account.insertAccount(bankName, balance);

            if ( result1 == true && result2 == true ) {
                mView.successfullyInserted();
            } else {
                mView.DatabaseInsertError();
            }
        }
    }
}
