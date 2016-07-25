package wallyson.com.br.mypocket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import wallyson.com.br.mypocket.model.ConfigurationCard;
import wallyson.com.br.mypocket.model.Database;

/**
 * Created by wally on 08/07/16.
 */
public class ConfigurationCardDao {
    private Database database;

    public ConfigurationCardDao(Context c) {
        database = new Database(c);
    }

    public ConfigurationCard selectOnceConfigurationCard(String cardName) {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_CONFIGURATION_CARD + " where cardName = '" + cardName + "';";
        Cursor result = db.rawQuery(sql, null);
        ConfigurationCard configCard = null;

        if ( result.moveToFirst() ) {
            configCard = new ConfigurationCard( result.getString(0), result.getString(2), result.getDouble(1) );
        }

        result.close();
        db.close();

        return configCard;
    }

    public boolean insertConfigurationCard(String cardName, double credit, String receiptDate) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("cardName", cardName);
        content.put("credit", credit);
        content.put("receiptDate", receiptDate);

        long result = db.insert(Database.TABLE_CONFIGURATION_CARD, null, content);

        db.close();

        if ( result == -1 )
            return false;
        else
            return true;
    }

    public boolean updateConfigurationCard(ConfigurationCard configCard) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("cardName", configCard.getCardName());
        content.put("credit", configCard.getCredit());
        content.put("receiptDate", configCard.getReceiptDate() );
        int result = db.update(Database.TABLE_CONFIGURATION_CARD, content, "cardName = ?", new String[] {configCard.getCardName()} );

        db.close();

        if ( result > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    /*
    public Integer deleteConfigurationCard(String cardName) {
        SQLiteDatabase db = database.getReadableDatabase();
        return db.delete(Database.TABLE_CONFIGURATION_CARD, "cardName = ?", new String[] {cardName} );
    }*/
}
