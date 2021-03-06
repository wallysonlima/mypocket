package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.SpendingDao;
import wallyson.com.br.mypocket.model.Spending;

/**
 * Created by wally on 14/07/16.
 */
public class SpendingMonthActivityPresenter {
    private SpendingMonthInterface mView;
    private Context c;
    SpendingDao spendingDao;

    public SpendingMonthActivityPresenter(SpendingMonthInterface view, Context context) {
        mView = view;
        c = context;
        spendingDao = new SpendingDao(c);
    }

    public ArrayList<Spending> getSpendingForMonthYear() {
        String monthYear = mView.getMonthYear();

        return spendingDao.selectSpendingMonthYear(monthYear);
    }
}
