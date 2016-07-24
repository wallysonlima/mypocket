package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.ConfigurationAccountDao;
import wallyson.com.br.mypocket.dao.UserDao;
import wallyson.com.br.mypocket.model.User;

/**
 * Created by wally on 30/06/16.
 */
public class AccountActivityPresenter {
    AccountInterface mView;
    Context c;
    UserDao userDao;
    AccountDao account;
    User user;
    boolean result1, result2;

    public AccountActivityPresenter(AccountInterface view, Context context) {
        mView = view;
        c = context;
    }

    public boolean accountRegistration() {
        String bankName = mView.getBankName();
        String balance = mView.getBalance();

        if ( bankName.equals("") || balance.equals("") ) {
            mView.registrationError();
            return false;
        } else {
            account = new AccountDao(c);
            userDao = new UserDao(c);
            user = userDao.selectUser();
            result1 = account.insertAccount(bankName, Double.parseDouble(balance), user.getCodUser() );

            if( result1 )
                configurationAccountRegistration();

            if ( result1 && result2 ) {
                mView.successfullyInserted();
                return true;
            } else {
                mView.databaseInsertError();
                return false;
            }
        }
    }

    public void configurationAccountRegistration() {
        String bankName = mView.getBankName();
        String balance = mView.getBalance();
        String receiptDate = mView.getReceiptDate();

        if (receiptDate.equals("")) {
            mView.registrationError();
        } else {
            ConfigurationAccountDao config = new ConfigurationAccountDao(c);
            result2 = config.insertConfigurationAccount(bankName, Double.parseDouble(balance), receiptDate);
        }
    }
}
