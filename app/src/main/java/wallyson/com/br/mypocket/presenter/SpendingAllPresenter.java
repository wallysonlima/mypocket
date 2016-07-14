package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.SpendingDao;
import wallyson.com.br.mypocket.model.Spending;

/**
 * Created by wally on 14/07/16.
 */
public class SpendingAllPresenter {
    private SpendingAllInterface mView;
    private Context c;
    SpendingDao spendingDao;

    public SpendingAllPresenter(SpendingAllInterface view, Context context) {
        mView = view;
        c = context;
        spendingDao = new SpendingDao(c);
    }

    public ArrayList<Spending> getSpendingForMonth() {
        int m = mView.getPositionSpinner();
        String month = null;

        if ( m <= 9 ) {
            month = "0" + String.valueOf(m);
        } else {
            month = String.valueOf(m);
        }

        return spendingDao.selectSpendingMonth(month);
    }
}
