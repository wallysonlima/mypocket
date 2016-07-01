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
public class CardDao {
    private Database database;

    public CardDao(Context context) {
        database = new Database(context);
    }

    public boolean insertCard(String cardName, double credit, String maturity, String bankName) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("cardName", cardName);
        content.put("credit", credit);
        content.put("maturity", maturity);
        content.put("bankName", bankName);

        long result = db.insert("card", null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public Integer deleteCard(int cardName) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete("card", "cardName = ?", new String[] {Integer.toString(cardName)} );
    }

}
