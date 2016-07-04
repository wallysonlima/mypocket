package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.CardDao;
import wallyson.com.br.mypocket.model.Account;
import wallyson.com.br.mypocket.model.Card;

/**
 * Created by wally on 04/07/16.
 */
public class SpendingActivityPresenter {
    SpendingInterface mView;
    Context c;

    public SpendingActivityPresenter(SpendingInterface view, Context context) {
        this.mView = view;
        this.c = context;
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

}
