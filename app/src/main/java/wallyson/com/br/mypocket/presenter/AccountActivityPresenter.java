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
    boolean result;

    public AccountActivityPresenter(AccountInterface view, Context context) {
        mView = view;
        c = context;
    }

    public void accountRegistration() {
        String bankName = mView.getBankName();
        Double balance = mView.getBalance();

        if ( bankName.equals(null) && balance == 0.0 ) {
            mView.registrationError();
        } else {
            account = new AccountDao(c);
            userDao = new UserDao(c);
            user = userDao.selectUser();
            result = account.insertAccount(bankName, balance, user.getCodUser() );

            configurationAccountRegistration();

            if ( result ) {
                mView.successfullyInserted();
            } else {
                mView.databaseInsertError();
            }
        }
    }

    public void configurationAccountRegistration() {
        String bankName = mView.getBankName();
        Double balance = mView.getBalance();
        String receiptDate = mView.getReceiptDate();

        if (receiptDate.equals(null)) {
            mView.registrationError();
        } else {
            ConfigurationAccountDao config = new ConfigurationAccountDao(c);
            result = config.insertConfigurationAccount(bankName, balance, receiptDate);

            if (!result) {
                mView.databaseInsertError();
            }
        }
    }
}
