package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.CardDao;
import wallyson.com.br.mypocket.dao.ConfigurationCardDao;
import wallyson.com.br.mypocket.model.Account;

/**
 * Created by wally on 01/07/16.
 */
public class CardActivityPresenter {
    private CardInterface mView;
    private Context c;
    private boolean result;

    public CardActivityPresenter(CardInterface view, Context context) {
        mView = view;
        c = context;
    }

    public void cardRegistration() {
        String cardName = mView.getCardName();
        Double credit = mView.getCredit();
        String bankName = mView.getBankName();


        if ( bankName.equals(null) && credit == 0.0 && cardName.equals(null) ) {
            mView.registrationError();
        } else {
            CardDao card = new CardDao(c);
            result = card.insertCard(cardName, credit, bankName);

            configurationCardRegistration();

            if ( result ) {
                mView.successfullyInserted();
            } else {
                mView.databaseInsertError();
            }
        }

    }

    public void configurationCardRegistration() {
        String cardName = mView.getCardName();
        Double credit = mView.getCredit();
        String receiptDate = mView.getReceiptDate();

        if (receiptDate.equals(null)) {
            mView.registrationError();
        } else {
            ConfigurationCardDao config = new ConfigurationCardDao(c);
            result = config.insertConfigurationCard(cardName, credit, receiptDate);

            if (!result) {
                mView.databaseInsertError();
            }
        }
    }

    public String[] getAllAccountName() {
        ArrayList<Account> ac;
        AccountDao account;
        account = new AccountDao(c);
        ac = account.selectAccount();
        String[] arrayAccount = new String[ac.size()];

        for ( Account a: ac ) {
            int i = 0;
            arrayAccount[i] = a.getBankName();
            i++;
        }

        return arrayAccount;
    }
}
