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
 * Created by enterprise on 25/04/17.
 */

public class custhist_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "CustHist.db";
    private static final String table_CustHist = "CustHist";
    private static final String ID = "id";
    private static final String custhistId = "custhistId";
    private static final String servicename = "servicename";
    private static final String serviceId = "serviceId";
    private static final String sessionNo = "sessionNo";
    private static final String date = "date";
    private static final String customerId = "customerId";
    private static final String photo = "photo";
    private static final String notes = "notes";



    private static final String[] COLUMNS = {ID, custhistId, servicename, serviceId, sessionNo, date, customerId, photo, notes};

    public custhist_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE CustHist ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "custhistId TEXT," +
                " " + "servicename TEXT, " + "serviceId TEXT," +
                " " + "sessionNo TEXT, " + " date TEXT, " + " customerId TEXT, " + "photo TEXT, " + "notes TEXT )";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_CustHist);
        this.onCreate(db);

    }

    public void createCustHist(String custhistId_, String servicename_,
                               String serviceId_, String sessionNo_,
                               String date_, String customerId_, String photo_, String notes_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(custhistId, custhistId_);
        values.put(servicename, servicename_);
        values.put(serviceId, serviceId_);
        values.put(sessionNo, sessionNo_);
        values.put(date, date_);
        values.put(customerId, customerId_);
        values.put(photo, photo_);
        values.put(notes, notes_);

        // insert Event
        db.insert(table_CustHist, null, values);

        // close database transaction
        // db.close();
    }

    public custhist_model readCustHist(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_CustHist, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            custhist_model EM = new custhist_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setCusthistId(cursor.getString(1));
            EM.setServicename(cursor.getString(2));
            EM.setServiceId(cursor.getString(3));
            EM.setSessionNo(cursor.getString(4));
            EM.setDate(cursor.getString(5));
            EM.setCustomerId(cursor.getString(6));
            EM.setPhoto(cursor.getString(7));
            EM.setNotes(cursor.getString(8));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    //-----------------------------------------------------------------------
    public List<custhist_model> getAllcusthist() {
        List<custhist_model> eventsM = new LinkedList<custhist_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_CustHist;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        custhist_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new custhist_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setCusthistId(cursor.getString(1));
                EM.setServicename(cursor.getString(2));
                EM.setServiceId(cursor.getString(3));
                EM.setSessionNo(cursor.getString(4));
                EM.setDate(cursor.getString(5));
                EM.setCustomerId(cursor.getString(6));
                EM.setPhoto(cursor.getString(7));
                EM.setNotes(cursor.getString(8));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }


//    // get patient with id
//
//    public List<product_model> getPatientWithId(String id) {
//        List<product_model> eventsM = new LinkedList<>();
//
//        // select Event query
//        String query = "SELECT  * FROM "+table_Patients +" where patientId = '"+id+"' ";
//
//        // get reference of the EventDB database
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        // parse all results
//        patient_model EM = null;
//        if (cursor.moveToFirst()) {
//            do {
//
//                EM = new patient_model();
//                EM.setId(Integer.parseInt(cursor.getString(0)));
//                EM.setPatientName(cursor.getString(1));
//                EM.setServiceName(cursor.getString(2));
//                EM.setSqe(cursor.getString(3));
//                EM.setPatientId(cursor.getString(4));
//                EM.setServiceID(cursor.getString(5));
//
//
//                eventsM .add(EM);
//            } while (cursor.moveToNext());
//        }
//
//        return eventsM ;
//    }

    // Deleting single Event
    public void deleteEvent(custhist_model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_CustHist, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}


