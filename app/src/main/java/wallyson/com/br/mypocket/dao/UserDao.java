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

    public UserDao(Context context) {
        database = new Database(context);
    }

    public User selectUser() {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_USER + ";";
        Cursor result =  db.rawQuery(sql, null);

        if ( result.moveToFirst() ) {
            User user = new User( result. getString(1), result.getString(2), result.getInt(0) );
            result.close();
            db.close();
            return user;
        }

        result.close();
        db.close();
        return null;
    }

    public boolean insertUser(String name, String email) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("email", email);

        long result = db.insert(Database.TABLE_USER, null, content);

        db.close();

        if ( result == -1 )
            return false;
        else
            return true;
    }

    /*
    public boolean updateUser(int codUser, String name, String email) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("email", email);
        int result = db.update(Database.TABLE_USER, content, "codUser = ?", new String[]{Integer.toString(codUser)});

        if ( result > 0 )
            return true;
        else
            return false;
    }

    public Integer deleteUser(int codUser) {
        SQLiteDatabase db = database.getReadableDatabase();
        return db.delete(Database.TABLE_USER, "codUser = ?", new String[] {Integer.toString(codUser)} );
    }*/
}
