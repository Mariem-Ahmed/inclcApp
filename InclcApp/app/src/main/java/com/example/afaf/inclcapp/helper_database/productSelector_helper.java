package com.example.afaf.inclcapp.helper_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by enterprise on 27/04/17.
 */

public class productSelector_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "productSelector.db";
    private static final String table_productSelector = "productSelector";
    private static final String ID = "id";
    private static final String prodkey = "prodkey";
    private static final String prodname = "prodname";
    private static final String prodId = "prodId";


    private static final String[] COLUMNS = {ID, prodkey, prodname, prodId};

    public productSelector_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE productSelector ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "prodkey TEXT," +
                " " + "prodname TEXT, " + "prodId TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_productSelector);
        this.onCreate(db);

    }

    public void createProductSelector(String prodkey_, String prodname_, String prodId_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(prodkey, prodkey_);
        values.put(prodname, prodname_);
        values.put(prodId, prodId_);

        // insert Event
        db.insert(table_productSelector, null, values);

        // close database transaction
        // db.close();
    }

    public List<productSelector_model> getAllProductSelector() {
        List<productSelector_model> eventsM = new LinkedList<productSelector_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_productSelector;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        productSelector_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new productSelector_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setProdkey(cursor.getString(1));
                EM.setProdname(cursor.getString(2));
                EM.setProdId(cursor.getString(3));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }

    public productSelector_model readproductSelector(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_productSelector, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            productSelector_model EM = new productSelector_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setProdkey(cursor.getString(1));
            EM.setProdname(cursor.getString(2));
            EM.setProdId(cursor.getString(3));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }

      // search product
    public List<productSelector_model> getproductSelectorByKey(String SearchKey) {
        List<productSelector_model> eventsM = new LinkedList<productSelector_model>();
        // select Event query
        String query = "SELECT  * FROM "+ table_productSelector +" where prodname LIKE '"+SearchKey+"%' "; ;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        productSelector_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new productSelector_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setProdkey(cursor.getString(1));
                EM.setProdname(cursor.getString(2));
                EM.setProdId(cursor.getString(3));

                eventsM .add(EM);
            } while (cursor.moveToNext());
        }
        // close database transaction
        //  db.close();
        return eventsM ;
    }

    // Deleting single Event
    public void deleteEvent(productSelector_model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_productSelector, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
