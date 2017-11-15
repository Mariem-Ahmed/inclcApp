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

public class services_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "Services.db";
    private static final String table_Services = "Services";
    private static final String ID = "id";
    private static final String serviceId = "serviceId";
    private static final String serviceName = "serviceName";
    private static final String addServiceId = "addServiceId";
    private static final String cost = "cost";
    private static final String price = "price";
    private static final String serviceCategory = "serviceCategory";
    private static final String serviceCategoryId = "serviceCategoryId";
    private static final String unitnum = "unitnum";


    private static final String[] COLUMNS = {ID, serviceId, serviceName, addServiceId,
            cost, price, serviceCategory, serviceCategoryId, unitnum};

    public services_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE Services ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "serviceId TEXT," +
                " " + "serviceName TEXT, " + "addServiceId TEXT," +
                " " + "cost TEXT, " + " price TEXT, " + " serviceCategory TEXT, " + " serviceCategoryId TEXT, " + " unitnum TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_Services);
        this.onCreate(db);

    }

    public void createService(String serviceId_, String serviceName_, String addServiceId_, String cost_
            , String price_, String serviceCategory_, String serviceCategoryId_, String unitnum_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(serviceId, serviceId_);
        values.put(serviceName, serviceName_);
        values.put(addServiceId, addServiceId_);
        values.put(cost, cost_);
        values.put(price, price_);
        values.put(serviceCategory, serviceCategory_);
        values.put(serviceCategoryId, serviceCategoryId_);
        values.put(unitnum, unitnum_);

        // insert Event
        db.insert(table_Services, null, values);

        // close database transaction
        // db.close();
    }

    public services_model readService(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_Services, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            services_model EM = new services_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setServiceId(cursor.getString(1));
            EM.setServiceName(cursor.getString(2));
            EM.setAddServiceId(cursor.getString(3));
            EM.setCost(cursor.getString(4));
            EM.setPrice(cursor.getString(5));
            EM.setServiceCategory(cursor.getString(6));
            EM.setServiceCategoryId(cursor.getString(7));
            EM.setUnitnum(cursor.getString(8));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    //-----------------------------------------------------------------------
    public List<services_model> getAllServices() {
        List<services_model> eventsM = new LinkedList<services_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Services;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        services_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new services_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setServiceId(cursor.getString(1));
                EM.setServiceName(cursor.getString(2));
                EM.setAddServiceId(cursor.getString(3));
                EM.setCost(cursor.getString(4));
                EM.setPrice(cursor.getString(5));
                EM.setServiceCategory(cursor.getString(6));
                EM.setServiceCategoryId(cursor.getString(7));
                EM.setUnitnum(cursor.getString(8));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }


//    // get service with id

    public List<services_model> getServiceWithId(String id) {
        List<services_model> eventsM = new LinkedList<>();

        // select Event query
        String query = "SELECT  * FROM " + table_Services + " where serviceId = '" + id + "' ";

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        services_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new services_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setServiceId(cursor.getString(1));
                EM.setServiceName(cursor.getString(2));
                EM.setAddServiceId(cursor.getString(3));
                EM.setCost(cursor.getString(4));
                EM.setPrice(cursor.getString(5));
                EM.setServiceCategory(cursor.getString(6));
                EM.setServiceCategoryId(cursor.getString(7));
                EM.setUnitnum(cursor.getString(8));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }

    // insert  event
    public void insertServices(services_model EM) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, EM.getId());
        contentValues.put(serviceName, EM.getServiceName());
        contentValues.put(serviceId, EM.getServiceId());
        contentValues.put(addServiceId, EM.getAddServiceId());
        contentValues.put(cost, EM.getCost());
        contentValues.put(price, EM.getPrice());
        contentValues.put(serviceCategory, EM.getServiceCategory());
        contentValues.put(serviceCategoryId, EM.getServiceCategoryId());
        contentValues.put(unitnum, EM.getUnitnum());

        // close database transaction
        // return db.insert(table_Activity, null,contentValues);
        db.insert(table_Services, null, contentValues);

    }

    // Deleting single Event
    public void deleteEvent(services_model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_Services, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}

