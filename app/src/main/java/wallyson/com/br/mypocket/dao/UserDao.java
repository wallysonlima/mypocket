package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import wallyson.com.br.mypocket.model.Database;
import wallyson.com.br.mypocket.model.User;

/**
 * Created by wally on 28/06/16.
 */
public class UserDao {
    private Database database;

    public void UserDao(Context context) {
        database = new Database(context);
    }

    public User selectUser() {
        SQLiteDatabase db = database.getWritableDatabase();
        String sql = "select * from user;";
        Cursor result =  db.rawQuery(sql, null);

        if ( result.getCount() != 0 ) {
            return (new User( result.getString(1), result.getString(2), result.getInt(0) ) );
        }

        return null;
    }

    public boolean insertUser(String name, String email) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("email", email);

        long result = db.insert("user", null, content);

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public boolean updateUser(int codUser, String name, String email) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("email", email);
        int result = db.update("user", content, "codUser = ?", new String[]{Integer.toString(codUser)});

        if ( result > 0 )
            return true;
        else
            return false;
    }

    public Integer deleteUser(int codUser) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete("user", "codUser = ?", new String[] {Integer.toString(codUser)} );
    }
}
