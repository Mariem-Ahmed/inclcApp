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
 * Created by enterprise on 26/04/17.
 */

public class sercat_helper extends SQLiteOpenHelper {
    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "ServiceCat.db";
    private static final String table_ServiceCat = "ServiceCat";
    private static final String ID = "id";
    private static final String key = "key";
    private static final String name = "name";
    private static final String sercatId = "sercatId";


    private static final String[] COLUMNS = {ID, key, name, sercatId};

    public sercat_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE ServiceCat ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "key TEXT," +
                " " + "name TEXT, " + "sercatId TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_ServiceCat);
        this.onCreate(db);

    }

    public void createServiceCat(String key_, String name_, String sercatId_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(key, key_);
        values.put(name, name_);
        values.put(sercatId, sercatId_);

        // insert Event
        db.insert(table_ServiceCat, null, values);

        // close database transaction
        // db.close();
    }

    public List<sercat_model> getAllServiceCat() {
        List<sercat_model> eventsM = new LinkedList<sercat_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_ServiceCat;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        sercat_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new sercat_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setKey(cursor.getString(1));
                EM.setName(cursor.getString(2));
                EM.setSercatId(cursor.getString(3));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }

    public sercat_model readServiceCat(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_ServiceCat, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            sercat_model EM = new sercat_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setKey(cursor.getString(1));
            EM.setName(cursor.getString(2));
            EM.setSercatId(cursor.getString(3));

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
        db.delete(table_ServiceCat, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
