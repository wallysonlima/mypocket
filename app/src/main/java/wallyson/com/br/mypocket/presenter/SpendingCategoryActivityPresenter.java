package wallyson.com.br.mypocket.presenter;

import android.content.Context;

import java.util.ArrayList;

import wallyson.com.br.mypocket.dao.SpendingDao;
import wallyson.com.br.mypocket.model.Spending;

/**
 * Created by wally on 11/07/16.
 */
public class SpendingCategoryActivityPresenter {
    SpendingCategoryInterface mView;
    Context c;
    SpendingDao spendingDao;

    public SpendingCategoryActivityPresenter(SpendingCategoryInterface view, Context context) {
        mView = view;
        c = context;
        spendingDao = new SpendingDao(c);
    }

    public ArrayList<Float> spendingForCategory(String monthYear) {
        ArrayList<String> category = mView.getCategory();
        ArrayList<Spending> spending = spendingDao.selectSpendingMonthYear(monthYear);
        ArrayList<Float> spendingForCategory = new ArrayList<>();

        for ( Spending sp: spending ) {
            for ( int i = 0; i < category.size(); i++ ) {
                if ( sp.getCategory().equalsIgnoreCase( category.get(i) ) ) {
                    spendingForCategory.add(i, spendingForCategory.get(i) + sp.getAmount() );
                }
            }
        }

        return spendingForCategory;
    }
}
