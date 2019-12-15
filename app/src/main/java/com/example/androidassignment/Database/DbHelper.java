package com.example.androidassignment.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.androidassignment.model.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Parsania Hardik on 11/01/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "log_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LOG = "logs";
    private static final String TABLE_PROFILE = "profiles";

    private static final String KEY_ID = "id";
    private static final String KEY_DAY = "day";
    private static final String KEY_DAY_NAME = "day_name";
    private static final String KEY_SERIAL = "serial";
    private static final String KEY_PILOT = "pilot";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_CONTRACT = "contract";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DATE = "date";
    private static final String KEY_PASSWORD = "password";

    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_LOG = "CREATE TABLE "
            + TABLE_LOG + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_DAY + " INTEGER ,"
            + KEY_LATITUDE + " TEXT ,"
            + KEY_LONGITUDE + " TEXT ,"
            + KEY_DAY_NAME + " TEXT ,"
            + KEY_SERIAL + " INTEGER ,"
            + KEY_PILOT + " TEXT ,"
            + KEY_CATEGORY + " TEXT ,"
            + KEY_CONTRACT + " TEXT ,"
            + KEY_DATE + " DATETIME   DEFAULT CURRENT_TIMESTAMP "+");";

    private static final String CREATE_TABLE_USER_PROFILE = "CREATE TABLE "
            + TABLE_PROFILE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " INTEGER,"+  KEY_PASSWORD + " TEXT );";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

//        Log.d("table", CREATE_TABLE_LOG);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        deleteLogTable();


        db.execSQL(CREATE_TABLE_LOG);
        db.execSQL(CREATE_TABLE_USER_PROFILE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_LOG + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_PROFILE + "'");

        onCreate(db);
    }
    public void deleteLogTable(){
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_PROFILE + "'");
        db.execSQL("delete from "+ TABLE_LOG);
//        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_LOG + "'");
//        db.execSQL(CREATE_TABLE_LOG);

    }

    public void updateUserProfile(){


    }
    public void addLog(Log l){
//        deleteLogTable();



        SQLiteDatabase db = this.getWritableDatabase();

        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_DAY , l.getDay());
        values.put(KEY_DAY_NAME, l.getDayName());

        values.put(KEY_SERIAL , l.getSerial());
        values.put(KEY_PILOT , l.getPilot());
        values.put(KEY_CONTRACT , l.getContract());
        values.put(KEY_CATEGORY , l.getCategory());
        values.put(KEY_DATE ,getDateTime());
        values.put(KEY_LATITUDE ,l.getLatString());
        values.put(KEY_LONGITUDE ,l.getLongString());
        long id = db.insertWithOnConflict(TABLE_LOG, null, values, SQLiteDatabase.CONFLICT_IGNORE);

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public String getLogsToEmail(){
        String returnLog = "";
        String selectQuery = "SELECT  * FROM " + TABLE_LOG + " ORDER BY date DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String serial = c.getString(c.getColumnIndex(KEY_SERIAL));
//                DateFormat defaultForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date strSqliteDate = defaultForm.parse(c.getString(c.getColumnIndex(KEY_DATE)));;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                String dateString = format.format(strSqliteDate);
//                String dateString = format.format(c.getString(c.getColumnIndex(KEY_DATE)));
                String dateString = c.getString(c.getColumnIndex(KEY_DATE));
                String type = c.getString(c.getColumnIndex(KEY_CATEGORY));
                String pilot = c.getString(c.getColumnIndex(KEY_PILOT));
                String contract = c.getString(c.getColumnIndex(KEY_CONTRACT));
                String longitudeString = c.getString(c.getColumnIndex(KEY_LONGITUDE));
                String latitudeString = c.getString(c.getColumnIndex(KEY_LATITUDE));
                String temp= "";
                temp +=" " + serial;
                temp +=" " + type;
                temp +=" " + dateString;
                temp +=" " + longitudeString;
                temp +="-" + latitudeString;
                temp +=" " + pilot;
                temp +=" " + contract;
                returnLog += temp + "\n";
            } while (c.moveToNext());
        }
//        return userModelArrayList;
        deleteLogTable();
        return returnLog;

    }
    public ArrayList<String> getLogsString(int day){
        ArrayList<String> stringList = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOG + " WHERE day = "+ day  + " ORDER BY date DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String serial = c.getString(c.getColumnIndex(KEY_SERIAL));
//                DateFormat defaultForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date strSqliteDate = defaultForm.parse(c.getString(c.getColumnIndex(KEY_DATE)));;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                String dateString = format.format(strSqliteDate);
//                String dateString = format.format(c.getString(c.getColumnIndex(KEY_DATE)));
                String dateString = c.getString(c.getColumnIndex(KEY_DATE));
                String type = c.getString(c.getColumnIndex(KEY_CATEGORY));
                String pilot = c.getString(c.getColumnIndex(KEY_PILOT));
                String contract = c.getString(c.getColumnIndex(KEY_CONTRACT));
                String longitudeString = c.getString(c.getColumnIndex(KEY_LONGITUDE));
                String latitudeString = c.getString(c.getColumnIndex(KEY_LATITUDE));
                String temp= "";
                temp +=" " + serial;
                temp +=" " + type;
                temp +=" " + dateString;
                temp +=" " + longitudeString;
                temp +="-" + latitudeString;
                temp +=" " + pilot;
                temp +=" " + contract;
                stringList.add(temp);
            } while (c.moveToNext());
        }
//        return userModelArrayList;
        return stringList;
    }
    public ArrayList<Log> getAllLogs(int day) {
        ArrayList<Log> userModelArrayList = new ArrayList<Log>();

        String selectQuery = "SELECT  * FROM " + TABLE_LOG + " WHERE day = "+ day +"ORDER BY date DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Log logModel = new Log();
                logModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                logModel.setDay(c.getInt(c.getColumnIndex(KEY_DAY)));
                logModel.setSerial(c.getInt(c.getColumnIndex(KEY_SERIAL)));
                logModel.setPilot(c.getString(c.getColumnIndex(KEY_PILOT)));
                logModel.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
                // adding to Students list
                userModelArrayList.add(logModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }


}