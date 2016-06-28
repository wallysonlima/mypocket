package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import wallyson.com.br.mypocket.model.Database;

/**
 * Created by wally on 28/06/16.
 */
public class AccountDao {
    private Database database;

    public void AccountDao(Context context) {
        database = new Database(context);
    }

    public boolean insertAccount(int accountNum, String bankName, double balance) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("accountNum", accountNum);
        content.put("bankName", bankName);
        content.put("balance", balance);

        long result = db.insert("account", null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public Integer deleteAccount(int accountNum) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete("account", "accountNum = ?", new String[] {Integer.toString(accountNum)} );
    }


}
