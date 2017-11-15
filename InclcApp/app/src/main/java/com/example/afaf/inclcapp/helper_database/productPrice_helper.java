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
 * Created by enterprise on 11/05/17.
 */

public class productPrice_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "ProductPrice.db";
    private static final String table_ProductPrice = "ProductPrice";
    private static final String ID = "id";
    private static final String product = "product";
    private static final String price = "price";
    private static final String productpriceId = "productpriceId";


    private static final String[] COLUMNS = {ID, product, price, productpriceId};

    public productPrice_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE ProductPrice ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "product TEXT," +
                " " + "price TEXT, " + "productpriceId TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_ProductPrice);
        this.onCreate(db);

    }

    public void createproductprice(String product_, String price_, String productpriceId_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(product, product_);
        values.put(price, price_);
        values.put(productpriceId, productpriceId_);

        // insert Event
        db.insert(table_ProductPrice, null, values);

        // close database transaction
        // db.close();
    }

    public List<productPrice_model> getAllproductprice() {
        List<productPrice_model> eventsM = new LinkedList<productPrice_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_ProductPrice;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        productPrice_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new productPrice_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setProduct(cursor.getString(1));
                EM.setPrice(cursor.getString(2));
                EM.setProductpriceId(cursor.getString(3));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }

    public productPrice_model readproductPrice(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_ProductPrice, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            productPrice_model EM = new productPrice_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setProduct(cursor.getString(1));
            EM.setPrice(cursor.getString(2));
            EM.setProductpriceId(cursor.getString(3));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    // Deleting single Event
    public void deleteEvent(clinic_model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_ProductPrice, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
