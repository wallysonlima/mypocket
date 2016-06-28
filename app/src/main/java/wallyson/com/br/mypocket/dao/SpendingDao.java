package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import wallyson.com.br.mypocket.model.Database;

/**
 * Created by wally on 28/06/16.
 */


public class SpendingDao {
    private Database database;

    public SpendingDao(Context context) {
        database = new Database(context);
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
