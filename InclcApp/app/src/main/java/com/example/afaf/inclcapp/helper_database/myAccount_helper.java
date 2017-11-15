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
 * Created by enterprise on 21/05/17.
 */

public class myAccount_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "MyAccountData.db";
    private static final String table_MyAccountData = "MyAccountData";
    private static final String ID = "id";
    private static final String balance = "balance";
    private static final String totalDebit = "totalDebit";
    private static final String totalCredit = "totalCredit";


    private static final String[] COLUMNS = {ID, balance, totalDebit, totalCredit};

    public myAccount_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE MyAccountData ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "balance TEXT," +
                " " + "totalDebit TEXT, " + "totalCredit TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_MyAccountData);
        this.onCreate(db);

    }

    public void createMyAccount(String balance_, String totalDebit_, String totalCredit_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(balance, balance_);
        values.put(totalDebit, totalDebit_);
        values.put(totalCredit, totalCredit_);

        // insert Event
        db.insert(table_MyAccountData, null, values);

        // close database transaction
        // db.close();
    }

    public List<myAccount_model> getAllMyAccountData() {
        List<myAccount_model> eventsM = new LinkedList<myAccount_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_MyAccountData;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results

        if (cursor.moveToFirst()) {
            do {

                myAccount_model EM = new myAccount_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setBalance(cursor.getString(1));
                EM.setTotalDebit(cursor.getString(2));
                EM.setTotalCredit(cursor.getString(3));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }

    public myAccount_model readmyAccount(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_MyAccountData, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            myAccount_model EM = new myAccount_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setBalance(cursor.getString(1));
            EM.setTotalDebit(cursor.getString(2));
            EM.setTotalCredit(cursor.getString(3));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    // Deleting single Event
    public void deleteEvent(myAccount_model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_MyAccountData, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
