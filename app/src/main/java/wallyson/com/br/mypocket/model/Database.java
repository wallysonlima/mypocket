package wallyson.com.br.mypocket.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by wally on 27/06/16.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myPocket.db";
    public static final String TABLE_USER = "user";
    public static final String TABLE_ACCOUNT = "account";
    public static final String TABLE_CARD = "card";
    public static final String TABLE_SPENDING = "spending";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

        db.execSQL("create table " + TABLE_USER +
                "(codUser integer primary key autoincrement, " +
                "name text not null, " +
                "email text not null);");

        db.execSQL("create table " + TABLE_SPENDING +
                "(spendingCod integer primary key autoincrement," +
                "description text not null," +
                "amount real not null, " +
                "emissionDate text not null, " +
                "category text not null, " +
                "codUser integer not null, " +
                "foreign key(codUser) references " + TABLE_USER + " (spendingCod) on delete cascade);");

        db.execSQL("create table " + TABLE_ACCOUNT +
                "(accountNum integer primary key, " +
                "bankName text not null, " +
                "balance real not null, " +
                "codUser integer not null, " +
                "foreign key(codUser) references " + TABLE_USER + " (accountNum) on delete cascade);"
        );

        db.execSQL("create table " + TABLE_CARD +
                        "(cardNum integer primary key, " +
                        "flag text not null, " +
                        "credit real not null, " +
                        "maturity text not null, " +
                        "accountNum integer not null, " +
                        "foreign key(accountNum) references " + TABLE_ACCOUNT + " (cardNum) on delete cascade);"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_USER );
        db.execSQL("DROP TABLE IF EXIST " + TABLE_SPENDING );
        db.execSQL("DROP TABLE IF EXIST " + TABLE_ACCOUNT );
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CARD );
        onCreate(db);
    }
}
