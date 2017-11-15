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
 * Created by enterprise on 23/04/17.
 */

public class patient_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "Patients.db";
    private static final String table_Patients = "Patients";
    private static final String ID = "id";
    private static final String patientName = "patientName";
    private static final String serviceName = "serviceName";
    private static final String sqe = "sqe";
    private static final String patientId = "patientId";
    private static final String serviceID = "serviceID";
    private static final String custID = "custID";
    private static final String remain = "remain";


    private static final String[] COLUMNS = {ID, patientName, serviceName, sqe, patientId, serviceID, custID, remain};

    public patient_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE Patients ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "patientName TEXT," +
                " " + "serviceName TEXT, " + "sqe TEXT, " + "patientId TEXT, " + " serviceID TEXT, " + " custID TEXT, " + " remain TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_Patients);
        this.onCreate(db);

    }

    public void createPatient(String patientName_, String serviceName_, String sqe_, String patientId_,
                              String serviceID_, String custID_, String remain_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(patientName, patientName_);
        values.put(serviceName, serviceName_);
        values.put(sqe, sqe_);
        values.put(patientId, patientId_);
        values.put(serviceID, serviceID_);
        values.put(custID, custID_);
        values.put(remain, remain_);

        // insert Event
        db.insert(table_Patients, null, values);

        // close database transaction
        // db.close();
    }

    public patient_model readPatient(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_Patients, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            patient_model EM = new patient_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setPatientName(cursor.getString(1));
            EM.setServiceName(cursor.getString(2));
            EM.setSqe(cursor.getString(3));
            EM.setPatientId(cursor.getString(4));
            EM.setServiceID(cursor.getString(5));
            EM.setCustID(cursor.getString(6));
            EM.setRemain(cursor.getString(7));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    //-----------------------------------------------------------------------
    public List<patient_model> getAllPatients() {
        List<patient_model> eventsM = new LinkedList<patient_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Patients;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        patient_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new patient_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setPatientName(cursor.getString(1));
                EM.setServiceName(cursor.getString(2));
                EM.setSqe(cursor.getString(3));
                EM.setPatientId(cursor.getString(4));
                EM.setServiceID(cursor.getString(5));
                EM.setCustID(cursor.getString(6));
                EM.setRemain(cursor.getString(7));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }


    // get patient with id

    public List<patient_model> getPatientWithId(String id) {
        List<patient_model> eventsM = new LinkedList<>();

        // select Event query
        String query = "SELECT  * FROM " + table_Patients + " where patientId = '" + id + "' ";

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        patient_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new patient_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setPatientName(cursor.getString(1));
                EM.setServiceName(cursor.getString(2));
                EM.setSqe(cursor.getString(3));
                EM.setPatientId(cursor.getString(4));
                EM.setServiceID(cursor.getString(5));
                EM.setCustID(cursor.getString(6));
                EM.setRemain(cursor.getString(7));


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
        db.delete(table_Patients, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
