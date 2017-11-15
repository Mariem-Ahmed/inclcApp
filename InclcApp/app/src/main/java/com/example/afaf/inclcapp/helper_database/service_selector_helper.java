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
 * Created by enterprise on 14/05/17.
 */

public class service_selector_helper extends SQLiteOpenHelper {
    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "Service_Selector.db";
    private static final String table_Service_Selector = "Service_Selector";
    private static final String ID = "id";
    private static final String servicename = "servicename";
    private static final String serID = "serID";


    private static final String[] COLUMNS = {ID, servicename, serID};

    public service_selector_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE Service_Selector ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "servicename TEXT," +
                " " + "serID TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_Service_Selector);
        this.onCreate(db);

    }

    public void createServiceSelector(String servicename_, String serID_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(servicename, servicename_);
        values.put(serID, serID_);

        // insert Event
        db.insert(table_Service_Selector, null, values);

        // close database transaction
        // db.close();
    }

    public List<service_selecor_model> getAllServiceSel() {
        List<service_selecor_model> eventsM = new LinkedList<service_selecor_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Service_Selector;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        service_selecor_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new service_selecor_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setServicename(cursor.getString(1));
                EM.setSerID(cursor.getString(2));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }

    public service_selecor_model readServiceSel(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_Service_Selector, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            service_selecor_model EM = new service_selecor_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setServicename(cursor.getString(1));
            EM.setSerID(cursor.getString(2));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }

    // search service
    public List<service_selecor_model> getservice_selecorByKey(String SearchKey) {
        List<service_selecor_model> eventsM = new LinkedList<service_selecor_model>();
        // select Event query
        String query = "SELECT  * FROM "+ table_Service_Selector +" where servicename LIKE '"+SearchKey+"%' "; ;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        service_selecor_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new service_selecor_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setServicename(cursor.getString(1));
                EM.setSerID(cursor.getString(2));


                eventsM .add(EM);
            } while (cursor.moveToNext());
        }
        // close database transaction
        //  db.close();
        return eventsM ;
    }
    // Deleting single Event
    public void deleteEvent(clinic_model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_Service_Selector, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
