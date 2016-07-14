package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.SpendingDao;
import wallyson.com.br.mypocket.model.Spending;

/**
 * Created by wally on 12/07/16.
 */
public class SpendingYearActivityPresenter {
    SpendingYearInterface mView;
    Context c;
    SpendingDao spendingDao;

    public SpendingYearActivityPresenter(SpendingYearInterface view, Context context) {
        mView = view;
        c = context;
        spendingDao = new SpendingDao(c);
    }

    public ArrayList<Float> AllSpendingForMonth() {
        String [] monthYear = mView.getMonthYear();
        ArrayList<Spending> spending = spendingDao.selectSpending();
        ArrayList<Float> spendingForMonth = new ArrayList<>();

        for ( Spending sp: spending ) {
            for ( int i = 0; i < monthYear.length; i++ ) {
                String month = sp.getEmissionDate().substring(3, 5);
                int m = Integer.parseInt( month );
                if ( m == (i + 1) ) {
                    spendingForMonth.add(i, spendingForMonth.get(i) + sp.getAmount() );
                }
            }
        }

        return spendingForMonth;
    }
}
