package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import wallyson.com.br.mypocket.model.Account;
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

        long result = db.insert(Database.TABLE_ACCOUNT, null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public ArrayList<Account> selectAccount() {
        ArrayList<Account> account = new ArrayList<>();
        SQLiteDatabase db = database.getWritableDatabase();
        String sql = "select * from " + Database.TABLE_ACCOUNT;
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            Account ac = new Account( result.getString(0), result.getDouble(1), result.getInt(2) );
            account.add(ac);
        }

        return account;
    }

    public Account selectOnceAccount(String bankName) {
        SQLiteDatabase db = database.getWritableDatabase();
        String sql = "select * from " + Database.TABLE_ACCOUNT + "where bankName = " + bankName + ";";
        Cursor result = db.rawQuery(sql, null);
        return (new Account( result.getString(0), result.getDouble(1), result.getInt(2) ));
    }

    public boolean updateBalanceAccount(Account account) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("bankName", account.getBankName() );
        content.put("balance", account.getBalance() );
        content.put("codUser", account.getCodUser() );
        int result = db.update(Database.TABLE_ACCOUNT, content, "bankName = ?", new String[] {account.getBankName()} );

        if ( result > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public Integer deleteAccount(String bankName) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete(Database.TABLE_ACCOUNT, "bankName = ?", new String[] {bankName} );
    }

}
