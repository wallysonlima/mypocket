package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import wallyson.com.br.mypocket.model.Account;
import wallyson.com.br.mypocket.model.Card;
import wallyson.com.br.mypocket.model.Database;

/**
 * Created by wally on 28/06/16.
 */
public class CardDao {
    private Database database;

    public CardDao(Context context) {
        database = new Database(context);
    }

    public ArrayList<Card> selectCard() {
        ArrayList<Card> card = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_CARD + ";";
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            Card ca = new Card( result.getString(0), result.getDouble(1), result.getString(2) );
            card.add(ca);
        }

        result.close();
        db.close();
        return card;
    }

    public Card selectOnceCard(String cardName) {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_CARD + " where cardName = " + cardName + ";";
        Cursor result = db.rawQuery(sql, null);
        result.moveToFirst();
        Card card = new Card( result.getString(0), result.getDouble(1), result.getString(2) );
        result.close();
        db.close();

        return card;
    }

    public boolean updateCreditCard(Card card) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("cardName", card.getCardName() );
        content.put("credit", card.getCredit() );
        content.put("bankName", card.getBankName() );

        int result = db.update(Database.TABLE_CARD, content, "cardName = ?", new String[]{card.getCardName()});

        db.close();

        if ( result > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertCard(String cardName, double credit, String bankName) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("cardName", cardName);
        content.put("credit", credit);
        content.put("bankName", bankName);

        long result = db.insert(Database.TABLE_CARD, null, content);

        db.close();

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public Integer deleteCard(String cardName) {
        SQLiteDatabase db = database.getReadableDatabase();
        int result = db.delete(Database.TABLE_CARD, "cardName = ?", new String[] {cardName} );
        db.close();

        return result;
    }
}
