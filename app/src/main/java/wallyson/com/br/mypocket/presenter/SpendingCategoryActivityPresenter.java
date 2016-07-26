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

    public Float[] spendingForCategory(String monthYear) {
        ArrayList<String> category = mView.getCategory();
        ArrayList<Spending> spending = spendingDao.selectSpendingMonthYear(monthYear);
        Float[] spendingForCategory = new Float[ category.size() ];

        for( int i = 0; i < spendingForCategory.length; i++ )
            spendingForCategory[i] = 0.0f;

        for ( Spending sp: spending ) {
            for ( int i = 0; i < category.size(); i++ ) {
                if ( sp.getCategory().equalsIgnoreCase( category.get(i) ) ) {
                    spendingForCategory[i] += sp.getAmount();
                }
            }
        }

        return spendingForCategory;
    }
}
