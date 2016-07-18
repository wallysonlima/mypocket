package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.AccountDao;
import wallyson.com.br.mypocket.dao.CardDao;
import wallyson.com.br.mypocket.dao.ConfigurationAccountDao;
import wallyson.com.br.mypocket.dao.ConfigurationCardDao;
import wallyson.com.br.mypocket.model.Account;
import wallyson.com.br.mypocket.model.Card;
import wallyson.com.br.mypocket.model.ConfigurationAccount;
import wallyson.com.br.mypocket.model.ConfigurationCard;

/**
 * Created by wally on 18/07/16.
 */
public class ConfigurationActivityPresenter {
    ConfigurationInterface mView;
    Context c;

    public ConfigurationActivityPresenter(ConfigurationInterface view, Context context) {
        mView = view;
        c = context;
    }

    public ConfigurationCard getConfCard(String cardName) {
        ConfigurationCardDao confCard = new ConfigurationCardDao(c);
        return confCard.selectOnceConfigurationCard(cardName);
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

    public ConfigurationAccount getConfAccount(String bankName) {
        ConfigurationAccountDao confAccount = new ConfigurationAccountDao(c);
        return confAccount.selectOnceConfigurationAccount(bankName);
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

    public void registrationConfigurationAccount() {
        ConfigurationAccountDao confAccount = new ConfigurationAccountDao(c);
        String account = mView.getAccountSpinner();
        String renewalBalance = mView.getRenewalAccount();
        String balance = mView.getBalance();

        boolean result = confAccount.updateConfigurationAccount( new ConfigurationAccount(account, renewalBalance, Double.parseDouble(balance)));

        if ( result ) {
            mView.updatedSuccessfully();
        } else {
            mView.databaseInsertError();
        }
    }

    public void registrationConfigurationCard() {
        ConfigurationCardDao confCard = new ConfigurationCardDao(c);
        String card = mView.getCardSpinner();
        String renewalCredit = mView.getRenewalCredit();
        String credit = mView.getCredit();

        boolean result = confCard.updateConfigurationCard( new ConfigurationCard(card, renewalCredit, Double.parseDouble(credit) ) );

        if ( result ) {
            mView.updatedSuccessfully();
        } else {
            mView.databaseInsertError();
        }
    }
}
