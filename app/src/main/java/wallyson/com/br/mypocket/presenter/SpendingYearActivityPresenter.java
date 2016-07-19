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
        String [] month = mView.getMonthYear();
        ArrayList<Spending> spending = spendingDao.selectSpending();
        ArrayList<Float> spendingForMonth = new ArrayList<>();

        for ( Spending sp: spending ) {
            for ( int i = 0; i < month.length; i++ ) {
                String monthYear = sp.getEmissionDate().substring(3, 10);
                int m = Integer.parseInt(monthYear.substring(0, 2));
                int y = Integer.parseInt( monthYear.substring(3, 7) );
                if ( m == (i + 1) && y == Integer.parseInt( sp.getEmissionDate().substring(6, 10) ) ) {
                    spendingForMonth.add(i, spendingForMonth.get(i) + sp.getAmount() );
                }
            }
        }

        return spendingForMonth;
    }
}
