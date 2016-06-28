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

    public boolean insertCard(int cardNum, String flag, double credit, Date maturity) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("cardNum", cardNum);
        content.put("flag", flag);
        content.put("credit", credit);
        Format fo = new SimpleDateFormat("dd/MM/yyyy");
        content.put("maturity", fo.format(maturity));

        long result = db.insert("card", null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public Integer deleteCard(int cardNum) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete("card", "cardNum = ?", new String[] {Integer.toString(cardNum)} );
    }

}
