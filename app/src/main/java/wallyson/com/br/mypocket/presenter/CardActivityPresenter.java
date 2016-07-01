package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import wallyson.com.br.mypocket.dao.CardDao;

/**
 * Created by wally on 01/07/16.
 */
public class CardActivityPresenter {
    CardInterface mView;
    Context c;

    public CardActivityPresenter(CardInterface view, Context context) {
        mView = view;
        c = context;
    }

    public void cardRegistration() {
        String cardName = mView.getCardName();
        Double credit = mView.getCredit();
        String maturity = mView.getMaturity();
        String bankName = mView.getBankName();
        boolean result;

        if ( bankName.equals(null) && credit == 0.0 && cardName.equals(null) && maturity.equals(null) ) {
            mView.registrationError();
        } else {
            CardDao card = new CardDao(c);
            result = card.insertCard(cardName, credit, maturity, bankName);

            if ( result ) {
                mView.successfullyInserted();
            } else {
                mView.databaseInsertError();
            }
        }

    }
}
