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
 * Created by enterprise on 11/04/17.
 */

public class appointment_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "Appointments.db";
    private static final String table_Appointments = "Appointments";
    private static final String ID = "id";
    private static final String date = "date";
    private static final String doctor = "doctor";
    private static final String clinic = "clinic";
    private static final String startTime = "startTime";
    private static final String endTime = "endTime";
    private static final String numOfCustomer = "numOfCustomer";
    private static final String numOfServedCustomer = "numOfServedCustomer";
    private static final String appointmentStatus = "appointmentStatus";
    private static final String appointmentId = "appointmentId";


    private static final String[] COLUMNS = {ID, date, doctor, clinic,
            startTime, endTime, numOfCustomer, numOfServedCustomer, appointmentStatus, appointmentId};

    public appointment_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE Appointments ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "date TEXT," +
                " " + "doctor TEXT, " + "clinic TEXT, " + "startTime TEXT, " + "endTime TEXT, " + "numOfCustomer TEXT," +
                " " + "numOfServedCustomer TEXT, " + "appointmentStatus TEXT, " + "appointmentId TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_Appointments);
        this.onCreate(db);

    }

    public void createAppointment(String date_, String doctor_, String clinic_, String startTime_, String endTime_
            , String numOfCustomer_, String numOfServedCustomer_, String appointmentStatus_, String appointmentId_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(date, date_);
        values.put(doctor, doctor_);
        values.put(clinic, clinic_);
        values.put(startTime, startTime_);
        values.put(endTime, endTime_);
        values.put(numOfCustomer, numOfCustomer_);
        values.put(numOfServedCustomer, numOfServedCustomer_);
        values.put(appointmentStatus, appointmentStatus_);
        values.put(appointmentId, appointmentId_);

        // insert Event
        db.insert(table_Appointments, null, values);

        // close database transaction
        // db.close();
    }

    public appointment_Model readAppointment(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_Appointments, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            appointment_Model EM = new appointment_Model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setDate(cursor.getString(1));
            EM.setDoctor(cursor.getString(2));
            EM.setClinic(cursor.getString(3));
            EM.setStartTime(cursor.getString(4));
            EM.setEndTime(cursor.getString(5));
            EM.setNumOfCustomer(cursor.getString(6));
            EM.setNumOfServedCustomer(cursor.getString(7));
            EM.setAppointmentStatus(cursor.getString(8));
            EM.setAppointmentId(cursor.getString(9));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    //-----------------------------------------------------------------------
    public List<appointment_Model> getAllAppointments() {
        List<appointment_Model> eventsM = new LinkedList<appointment_Model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Appointments;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        appointment_Model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new appointment_Model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setDate(cursor.getString(1));
                EM.setDoctor(cursor.getString(2));
                EM.setClinic(cursor.getString(3));
                EM.setStartTime(cursor.getString(4));
                EM.setEndTime(cursor.getString(5));
                EM.setNumOfCustomer(cursor.getString(6));
                EM.setNumOfServedCustomer(cursor.getString(7));
                EM.setAppointmentStatus(cursor.getString(8));
                EM.setAppointmentId(cursor.getString(9));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }


    //-----------------------------get appintment with appointmentStatus----------------------------------------------------------------
    public List<appointment_Model> getAllAppointmentsFuture(String Status) {
        List<appointment_Model> eventsM = new LinkedList<appointment_Model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Appointments + " where appointmentStatus = '" + Status + "' ";

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        appointment_Model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new appointment_Model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setDate(cursor.getString(1));
                EM.setDoctor(cursor.getString(2));
                EM.setClinic(cursor.getString(3));
                EM.setStartTime(cursor.getString(4));
                EM.setEndTime(cursor.getString(5));
                EM.setNumOfCustomer(cursor.getString(6));
                EM.setNumOfServedCustomer(cursor.getString(7));
                EM.setAppointmentStatus(cursor.getString(8));
                EM.setAppointmentId(cursor.getString(9));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }


    //---------------- get chartAppointment
    public List<appointment_Model> getChartAppointment(String month) {
        List<appointment_Model> eventsM = new LinkedList<appointment_Model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Appointments + " where strftime('%m', date) = '" + month + "'";

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        appointment_Model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new appointment_Model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setDate(cursor.getString(1));
                EM.setDoctor(cursor.getString(2));
                EM.setClinic(cursor.getString(3));
                EM.setStartTime(cursor.getString(4));
                EM.setEndTime(cursor.getString(5));
                EM.setNumOfCustomer(cursor.getString(6));
                EM.setNumOfServedCustomer(cursor.getString(7));
                EM.setAppointmentStatus(cursor.getString(8));
                EM.setAppointmentId(cursor.getString(9));


                eventsM.add(EM);
            } while (cursor.moveToNext());
        }
        // close database transaction
        //  db.close();
        return eventsM;
    }

    // appointment with clinic
    public List<appointment_Model> geEventAppointment(String clinic, int appointID) {
        List<appointment_Model> eventsM = new LinkedList<appointment_Model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Appointments + " where clinic = '" + clinic + "' and appointmentStatus = 'FUTURE' and id = '" + appointID + "' ";
        ;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        appointment_Model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new appointment_Model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setDate(cursor.getString(1));
                EM.setDoctor(cursor.getString(2));
                EM.setClinic(cursor.getString(3));
                EM.setStartTime(cursor.getString(4));
                EM.setEndTime(cursor.getString(5));
                EM.setNumOfCustomer(cursor.getString(6));
                EM.setNumOfServedCustomer(cursor.getString(7));
                EM.setAppointmentStatus(cursor.getString(8));
                EM.setAppointmentId(cursor.getString(9));

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
        db.delete(table_Appointments, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }

    // update  event
    public void updateEvent(appointment_Model EM) {

        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(date, EM.getDate());
        contentValues.put(doctor, EM.getDoctor());
        contentValues.put(clinic, EM.getClinic());
        contentValues.put(startTime, EM.getStartTime());
        contentValues.put(endTime, EM.getEndTime());
        contentValues.put(numOfCustomer, EM.getNumOfCustomer());
        contentValues.put(numOfServedCustomer, EM.getNumOfServedCustomer());
        contentValues.put(appointmentStatus, EM.getAppointmentStatus());
        contentValues.put(appointmentId, EM.getAppointmentId());

        db1.update(table_Appointments, contentValues, "ID=?", new String[]{String.valueOf(EM.getId())});
        //      db1.close();
    }

    // insert  event
    public void insertAppointment(appointment_Model EM) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, EM.getId());
        contentValues.put(date, EM.getDate());
        contentValues.put(doctor, EM.getDoctor());
        contentValues.put(clinic, EM.getClinic());
        contentValues.put(startTime, EM.getStartTime());
        contentValues.put(endTime, EM.getEndTime());
        contentValues.put(numOfCustomer, EM.getNumOfCustomer());
        contentValues.put(numOfServedCustomer, EM.getNumOfServedCustomer());
        contentValues.put(appointmentStatus, EM.getAppointmentStatus());
        contentValues.put(appointmentId, EM.getAppointmentId());

        // close database transaction
        // return db.insert(table_Activity, null,contentValues);
        db.insert(table_Appointments, null, contentValues);

    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
