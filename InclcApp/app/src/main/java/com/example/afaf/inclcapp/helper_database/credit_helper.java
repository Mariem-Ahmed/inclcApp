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
 * Created by enterprise on 22/05/17.
 */

public class credit_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "Credits.db";
    private static final String table_Credits = "Credits";
    private static final String ID = "id";
    private static final String cDate = "cDate";
    private static final String cAmount = "cAmount";
    private static final String cAppointmentID = "cAppointmentID";
    private static final String cAppointmentName = "cAppointmentName";
    private static final String cProductID = "cProductID";
    private static final String cProductName = "cProductName";
    private static final String cNetPrice = "cNetPrice";
    private static final String cUnitNo = "cUnitNo";
    private static final String cId = "cId";

    private static final String[] COLUMNS = {ID, cDate, cAmount, cAppointmentID,
            cAppointmentName, cProductID, cProductName, cNetPrice, cUnitNo, cId};

    public credit_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE Credits ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "cDate TEXT," +
                " " + "cAmount TEXT, " + "cAppointmentID TEXT, " + "cAppointmentName TEXT, " + "cProductID TEXT, " + "cProductName TEXT," +
                " " + "cNetPrice TEXT, " + "cUnitNo TEXT, " + "cId TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_Credits);
        this.onCreate(db);

    }

    public void createCredit(String cDate_, String cAmount_, String cAppointmentID_, String cAppointmentName_, String cProductID_
            , String cProductName_, String cNetPrice_, String cUnitNo_, String cId_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(cDate, cDate_);
        values.put(cAmount, cAmount_);
        values.put(cAppointmentID, cAppointmentID_);
        values.put(cAppointmentName, cAppointmentName_);
        values.put(cProductID, cProductID_);
        values.put(cProductName, cProductName_);
        values.put(cNetPrice, cNetPrice_);
        values.put(cUnitNo, cUnitNo_);
        values.put(cId, cId_);

        // insert Event
        db.insert(table_Credits, null, values);

        // close database transaction
        // db.close();
    }

    public credit_model readCredit(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_Credits, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            credit_model EM = new credit_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setcDate(cursor.getString(1));
            EM.setcAmount(cursor.getString(2));
            EM.setcAppointmentID(cursor.getString(3));
            EM.setcAppointmentName(cursor.getString(4));
            EM.setcProductID(cursor.getString(5));
            EM.setcProductName(cursor.getString(6));
            EM.setcNetPrice(cursor.getString(7));
            EM.setcUnitNo(cursor.getString(8));
            EM.setcId(cursor.getString(9));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    //-----------------------------------------------------------------------
    public List<credit_model> getAllCredits() {
        List<credit_model> eventsM = new LinkedList<credit_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Credits;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        credit_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new credit_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setcDate(cursor.getString(1));
                EM.setcAmount(cursor.getString(2));
                EM.setcAppointmentID(cursor.getString(3));
                EM.setcAppointmentName(cursor.getString(4));
                EM.setcProductID(cursor.getString(5));
                EM.setcProductName(cursor.getString(6));
                EM.setcNetPrice(cursor.getString(7));
                EM.setcUnitNo(cursor.getString(8));
                EM.setcId(cursor.getString(9));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }



    // Deleting single Event
    public void deleteEvent(appointment_Model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_Credits, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}


