package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        SQLiteDatabase db = database.getWritableDatabase();
        String sql = "select * from spending;";
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            Spending sp = new Spending( result.getInt(0), result.getString(1), result.getString(2), result.getDouble(3),
                    result.getString(4) );
            spending.add(sp);
        }

        return spending;
    }

    public boolean insertSpending(String description, Double amount, Date emissionDate, String category) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("description", description);
        content.put("amount", amount);
        Format fo = new SimpleDateFormat("dd/MM/yyyy");
        content.put("emissionDate", fo.format(emissionDate));
        content.put("category", category);

        long result = db.insert("spending", null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public Integer deleteSpending(int spendingCod) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete("spending", "spendingCod = ?", new String[] {Integer.toString(spendingCod)} );
    }

}
