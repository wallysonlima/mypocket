package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import wallyson.com.br.mypocket.model.ConfigurationAccount;
import wallyson.com.br.mypocket.model.Database;

/**
 * Created by wally on 08/07/16.
 */
public class ConfigurationAccountDao {
    private Database database;

    public ConfigurationAccountDao(Context c) {
        database = new Database(c);
    }

    public boolean insertConfigurationAccount(String bankName, double balance, String receiptDate) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("bankName", bankName);
        content.put("balance", balance);
        content.put("receiptDate", receiptDate);

        long result = db.insert(Database.TABLE_CONFIGURATION_ACCOUNT, null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public boolean updateBalanceAccount(ConfigurationAccount configAccount) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("bankName", configAccount.getBankName());
        content.put("balance", configAccount.getBalance());
        content.put("receiptDate", configAccount.getReceiptDate() );
        int result = db.update(Database.TABLE_CONFIGURATION_ACCOUNT, content, "bankName = ?", new String[] {configAccount.getBankName()} );

        if ( result > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public Integer deleteAccount(String bankName) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete(Database.TABLE_CONFIGURATION_ACCOUNT, "bankName = ?", new String[] {bankName} );
    }
}
