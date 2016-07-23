package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.CardDao;
import wallyson.com.br.mypocket.dao.UserDao;

/**
 * Created by wally on 23/07/16.
 */
public class MainActivityPresenter {
    MainInterface mView;
    Context c;


    public MainActivityPresenter(MainInterface view, Context context) {
        mView = view;
        c = context;
    }

    public boolean existAccount() {
        AccountDao accountDao = new AccountDao(c);

        if ( accountDao.selectAccount().size() > 0 )
            return true;
        else
            return false;
    }

    public boolean existCard() {
        CardDao cardDao = new CardDao(c);

        if ( cardDao.selectCard().size() > 0 )
            return true;
        else
            return false;
    }

    public boolean existUser() {
        UserDao userDao = new UserDao(c);

        if( userDao.selectUser() != null )
            return true;
        else
            return false;
    }
}
