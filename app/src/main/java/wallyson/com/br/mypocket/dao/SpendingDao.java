package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import wallyson.com.br.mypocket.model.Database;
import wallyson.com.br.mypocket.model.Spending;

/**
 * Created by wally on 28/06/16.
 */


public class SpendingDao {
    private Database database;

    public SpendingDao(Context context) {
        database = new Database(context);
    }

    public ArrayList<Spending> selectSpending() {
        ArrayList<Spending> spending = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_SPENDING + ";";
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            Spending sp = new Spending( result.getInt(0), result.getString(1),  result.getFloat(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6) );
            spending.add(sp);
        }

        result.close();
        db.close();

        return spending;
    }

    public ArrayList<Spending> selectSpendingMonthYear(String monthYear) {
        ArrayList<Spending> spending = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_SPENDING + " where emissionDate LIKE '%" + monthYear + "';";
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            Spending sp = new Spending( result.getInt(0), result.getString(1),  result.getFloat(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getString(6) );
            spending.add(sp);
        }

        result.close();
        db.close();
        return spending;
    }

    public boolean insertSpending(String description, Float amount, String emissionDate,
                                  String category, String bankName, String cardName, int codUser) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("description", description);
        content.put("amount", amount);
        content.put("emissionDate", emissionDate);
        content.put("category", category);
        content.put("bankName", bankName);
        content.put("cardName", cardName);
        content.put("codUser", codUser);

        long result = db.insert(Database.TABLE_SPENDING, null, content);

        db.close();

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public Integer deleteSpending(int spendingCod) {
        SQLiteDatabase db = database.getReadableDatabase();
        int result = db.delete(Database.TABLE_SPENDING, "spendingCod = ?", new String[] {Integer.toString(spendingCod)} );
        db.close();

        return result;
    }

}
