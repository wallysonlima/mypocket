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

    public ArrayList<String> getAllCardName() {
        ArrayList<Card> ca = card.selectCard();
        ArrayList<String> arrayCard = new ArrayList<>();

        for ( Card a: ca ) {
            arrayCard.add(a.getCardName());
        }

        return arrayCard;
    }

    public ArrayList<String> getAllAccountName() {
        ArrayList<Account> ac = account.selectAccount();
        ArrayList<String> arrayAccount = new ArrayList<>();

        for ( Account a: ac ) {
            arrayAccount.add( a.getBankName() );
        }

        return arrayAccount;
    }

}
