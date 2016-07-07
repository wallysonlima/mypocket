package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.CardDao;
import wallyson.com.br.mypocket.model.Account;
import wallyson.com.br.mypocket.model.Card;

/**
 * Created by wally on 07/07/16.
 */
public class BalanceActivityPresenter {
    BalanceInterface mView;
    Context c;
    CardDao card;
    AccountDao account;

    public BalanceActivityPresenter(BalanceInterface view, Context context) {
        mView = view;
        c = context;
        card = new CardDao(c);
        account = new AccountDao(c);
    }

    public String getBalanceAccount(String bankName) {
        Account acc = this.account.selectOnceAccount(bankName);
        return String.valueOf(acc.getBalance());
    }

    public String getCredit(String cardName) {
        Card ca = this.card.selectOnceCard(cardName);
        return String.valueOf( ca.getCredit() );
    }

    public String[] getAllCardName() {
        ArrayList<Card> ca;
        ca = card.selectCard();
        String[] arrayCard = new String[ca.size()];

        for ( Card a: ca ) {
            int i = 0;
            arrayCard[i] = a.getCardName();
            i++;
        }

        return arrayCard;
    }

    public String[] getAllAccountName() {
        ArrayList<Account> ac;
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
