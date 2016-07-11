package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.CardDao;
import wallyson.com.br.mypocket.dao.SpendingDao;
import wallyson.com.br.mypocket.dao.UserDao;
import wallyson.com.br.mypocket.model.Account;
import wallyson.com.br.mypocket.model.Card;
import wallyson.com.br.mypocket.model.User;

/**
 * Created by wally on 04/07/16.
 */
public class SpendingActivityPresenter {
    SpendingInterface mView;
    Context c;
    AccountDao accountDao;
    CardDao cardDao;



    public SpendingActivityPresenter(SpendingInterface view, Context context) {
        this.mView = view;
        this.c = context;
        accountDao = new AccountDao(c);
        cardDao = new CardDao(c);
    }

    public String[] getAllAccountName() {
        ArrayList<Account> ac;
        ac = accountDao.selectAccount();
        String[] arrayAccount = new String[ac.size()];

        for ( Account a: ac ) {
            int i = 0;
            arrayAccount[i] = a.getBankName();
            i++;
        }

        return arrayAccount;
    }

    public String[] getAllCardName() {
        ArrayList<Card> ca;
        ca = cardDao.selectCard();

        String[] arrayCard = new String[ca.size()];

        for ( Card a: ca ) {
            int i = 0;
            arrayCard[i] = a.getCardName();
            i++;
        }

        return arrayCard;
    }

    public void SpendingRegistration() {
        SpendingDao spending = new SpendingDao(c);
        UserDao userDao = new UserDao(c);
        User user = userDao.selectUser();

        String description = mView.getDescription();
        Float amount = mView.getAmount();
        String category = mView.getCategory();
        String emissionDate = mView.getEmissionDate();
        String account = mView.getAccount();
        String card = mView.getCard();

        if ( spending.insertSpending( description, amount, emissionDate, category, account, card, user.getCodUser() ) ) {
           mView.successfullyInserted();
        } else {
            mView.databaseInsertError();
        }
    }

    // Responsible for debiting the account or credit card
    public void Debiting() {
        Account account;
        Card card;

        Float amount = mView.getAmount();
        String bankName = mView.getAccount();
        String cardName = mView.getCard();
        String choose = mView.getChoose();

        if ( choose.equalsIgnoreCase("Debit") ) {
            account = accountDao.selectOnceAccount(bankName);
            account.setBalance( account.getBalance() - amount );

            if ( !accountDao.updateBalanceAccount( account ) )
                mView.databaseInsertError();
        } else {
            card = cardDao.selectOnceCard(cardName);
            card.setCredit( card.getCredit() - amount );

            if ( !cardDao.updateCreditCard(card) )
                mView.databaseInsertError();
        }
    }

}
