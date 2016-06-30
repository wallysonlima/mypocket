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

    public AccountDao(Context context) {
        database = new Database(context);
    }

    public boolean insertAccount(String bankName, double balance, int codUser) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("bankName", bankName);
        content.put("balance", balance);
        content.put("codeUser", codUser);

        long result = db.insert("account", null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public Integer deleteAccount(int bankName) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete("account", "bankName = ?", new String[] {Integer.toString(bankName)} );
    }


}
