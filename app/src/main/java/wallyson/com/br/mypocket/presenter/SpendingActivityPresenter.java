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

    public ArrayList<String> getAllAccountName() {
        ArrayList<Account> ac = accountDao.selectAccount();
        ArrayList<String> arrayAccount = new ArrayList<>();

        for ( Account a: ac ) {
            arrayAccount.add(a.getBankName());
        }

        return arrayAccount;
    }

    public ArrayList<String> getAllCardName() {
        ArrayList<Card> ca = cardDao.selectCard();
        ArrayList<String> arrayCard = new ArrayList<>();

        for ( Card a: ca ) {
            arrayCard.add( a.getCardName() );
        }
        return arrayCard;
    }

    public boolean spendingRegistration() {
        SpendingDao spending = new SpendingDao(c);
        UserDao userDao = new UserDao(c);
        User user = userDao.selectUser();

        String description = mView.getDescription();
        String amount = mView.getAmount();
        String category = mView.getCategory();
        String emissionDate = mView.getEmissionDate();
        String account = mView.getAccount();
        String card = mView.getCard();

        if ( !description.equals("") && !amount.equals("") && !category.equals("") &&
                !emissionDate.equals("") && !account.equals("") && !card.equals("")  ) {

            if ( spending.insertSpending( description, Float.parseFloat(amount), emissionDate, category, account, card, user.getCodUser() ) ) {
                debiting();
                mView.successfullyInserted();
                return true;
            } else {
                mView.databaseInsertError();
                return false;
            }
        } else {
            mView.registrationError();
            return false;
        }
    }

    // Responsible for debiting the account or credit card
    public void debiting() {
        Account account;
        Card card;

        String amount = mView.getAmount();
        String bankName = mView.getAccount();
        String cardName = mView.getCard();
        String choose = mView.getChoose();

        if ( choose.equalsIgnoreCase("Debit") ) {
            account = accountDao.selectOnceAccount(bankName);
            account.setBalance( account.getBalance() - Float.parseFloat(amount) );
            accountDao.updateBalanceAccount(account);
        } else {
            card = cardDao.selectOnceCard(cardName);
            card.setCredit( card.getCredit() - Float.parseFloat(amount) );
            cardDao.updateCreditCard(card);
        }
    }

}
