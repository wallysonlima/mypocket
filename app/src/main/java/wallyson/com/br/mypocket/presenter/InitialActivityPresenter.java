package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import wallyson.com.br.mypocket.dao.UserDao;

/**
 * Created by wally on 16/07/16.
 */
public class InitialActivityPresenter {
    private InitialInterface mView;
    Context c;
    UserDao user;

    public InitialActivityPresenter(InitialInterface v, Context context) {
        mView = v;
        c = context;
    }

    public boolean initialRegistration() {
        String name = mView.getName();
        String email = mView.getEmail();
        boolean result;

        if ( name.equals("") || email.equals("") ) {
            mView.registrationError();
            return false;
        } else {
            user = new UserDao(c);
            result = user.insertUser(name, email);

            if ( result == true ) {
                mView.successfullyInserted();
                return true;
            } else {
                mView.DatabaseInsertError();
                return false;
            }
        }
    }
}