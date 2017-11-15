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
 * Created by enterprise on 24/04/17.
 */

public class product_helper extends SQLiteOpenHelper {


    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "Products.db";
    private static final String table_Products = "Products";
    private static final String ID = "id";
    private static final String productName = "productName";
    private static final String sessionno = "sessionno";
    private static final String netunitprice = "netunitprice";
    private static final String unitno = "unitno";
    private static final String productID = "productID";
    private static final String warehouseRuleType = "warehouseRuleType";
    private static final String service = "service";
    private static final String serviceProductId = "serviceProductId";
    private static final String productHisID = "productHisID";

    private static final String[] COLUMNS = {ID, productName, sessionno, netunitprice,
            unitno, productID, warehouseRuleType, service, serviceProductId, productHisID};

    public product_helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Event table
        String CREATE_Event_TABLE = "CREATE TABLE Products ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "productName TEXT," +
                " " + "sessionno TEXT, " + "netunitprice TEXT, " + "unitno TEXT, " + " productID TEXT," +
                " " + "warehouseRuleType TEXT, " + "service TEXT, " + "serviceProductId TEXT," +"productHisID TEXT)";
        db.execSQL(CREATE_Event_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_Products);
        this.onCreate(db);

    }

    public void createProduct(String productName_, String sessionno_, String netunitprice_,
                              String unitno_, String productID_, String warehouseRuleType_, String service_, String serviceProductId_, String productHisID_) {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(productName, productName_);
        values.put(sessionno, sessionno_);
        values.put(netunitprice, netunitprice_);
        values.put(unitno, unitno_);
        values.put(productID, productID_);
        values.put(warehouseRuleType, warehouseRuleType_);
        values.put(service, service_);
        values.put(serviceProductId,serviceProductId_);
        values.put(productHisID,productHisID_);

        // insert Event
        db.insert(table_Products, null, values);

        // close database transaction
        // db.close();
    }

    public product_model readProduct(int id) throws JSONException {
        // get reference of the EventDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get Event query
        Cursor cursor = db.query(table_Products, // a. table
                COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
//        JSONArray arr =   convertCursorToJSON(cursor);
        // if results !=null, parse the first one
        try {
            if (cursor != null)
                cursor.moveToFirst();

            product_model EM = new product_model();
            EM.setId(Integer.parseInt(cursor.getString(0)));
            EM.setProductName(cursor.getString(1));
            EM.setSessionno(cursor.getString(2));
            EM.setNetunitprice(cursor.getString(3));
            EM.setUnitno(cursor.getString(4));
            EM.setProductID(cursor.getString(5));
            EM.setWarehouseRuleType(cursor.getString(6));
            EM.setService(cursor.getString(7));
            EM.setServiceProductId(cursor.getString(8));
            EM.setProductHistoryID(cursor.getString(9));

            return EM;
        } catch (Exception ex) {

        }
        return null;
        // close database transaction
        //    db.close();

    }


    //-----------------------------------------------------------------------
    public List<product_model> getAllProducts() {
        List<product_model> eventsM = new LinkedList<product_model>();

        // select Event query
        String query = "SELECT  * FROM " + table_Products;

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        product_model EM = null;
        if (cursor.moveToFirst()) {
            do {

                EM = new product_model();
                EM.setId(Integer.parseInt(cursor.getString(0)));
                EM.setProductName(cursor.getString(1));
                EM.setSessionno(cursor.getString(2));
                EM.setNetunitprice(cursor.getString(3));
                EM.setUnitno(cursor.getString(4));
                EM.setProductID(cursor.getString(5));
                EM.setWarehouseRuleType(cursor.getString(6));
                EM.setService(cursor.getString(7));
                EM.setServiceProductId(cursor.getString(8));
                EM.setProductHistoryID(cursor.getString(9));

                eventsM.add(EM);
            } while (cursor.moveToNext());
        }

        return eventsM;
    }


    // insert  event
    public void insertProduct(product_model EM) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, EM.getId());
        contentValues.put(productName, EM.getProductName());
        contentValues.put(sessionno, EM.getSessionno());
        contentValues.put(netunitprice, EM.getNetunitprice());
        contentValues.put(unitno, EM.getUnitno());
        contentValues.put(productID, EM.getProductID());
        contentValues.put(warehouseRuleType, EM.getWarehouseRuleType());
        contentValues.put(service, EM.getService());
        contentValues.put(serviceProductId, EM.getServiceProductId());
        contentValues.put(productHisID, EM.getProductHistoryID());

        // close database transaction
        // return db.insert(table_Activity, null,contentValues);
        db.insert(table_Products, null, contentValues);

    }

    // Deleting single Event
    public void deleteEvent(product_model EM) {

        // get reference of the EventDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete Event
        db.delete(table_Products, ID + " = ?", new String[]{String.valueOf(EM.getId())});
        db.close();
    }


    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
