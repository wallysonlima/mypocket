package wallyson.com.br.mypocket.presenter;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.CardDao;
import wallyson.com.br.mypocket.dao.SpendingDao;
import wallyson.com.br.mypocket.model.Account;
import wallyson.com.br.mypocket.model.Card;
import wallyson.com.br.mypocket.model.Spending;

/**
 * Created by wally on 02/07/16.
 */
public class DeleteActivityPresenter {
    DeleteInterface mView;
    Context c;

    public DeleteActivityPresenter(DeleteInterface view, Context context) {
        mView = view;
        c = context;
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

    public String[] getAllCardName() {
        ArrayList<Card> ca;
        CardDao card;
        card = new CardDao(c);
        ca = card.selectCard();

        String[] arrayCard = new String[ca.size()];

        for ( Card a: ca ) {
            int i = 0;
            arrayCard[i] = a.getCardName();
            i++;
        }

        return arrayCard;
    }

    /*public String[] getAllSpendingName() {
        ArrayList<Spending> sp;
        SpendingDao spending;
        spending = new SpendingDao(c);
        sp = spending.selectSpending();

        String[] arraySpending = new String[sp.size()];

        for ( Spending s: sp ) {
            int i = 0;
            arraySpending[i] = s.getDescription();
            i++;
        }

        return arraySpending;
    }*/

    public void deleteAccount() {
        AccountDao account = new AccountDao(c);
        String bankName = mView.getAccountSpinner();
        int result = account.deleteAccount( bankName );

        if ( result > 0 )
            mView.successfullyDeleted();
        else
            mView.databaseInsertError();
    }

    public void deleteCard() {
        CardDao card = new CardDao(c);
        String cardName = mView.getCardSpinner();
        int result = card.deleteCard(cardName);

        if ( result > 0 )
            mView.successfullyDeleted();
        else
            mView.databaseInsertError();
    }

}
