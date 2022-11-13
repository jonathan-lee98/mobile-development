package com.assignment.temperatureapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SqlDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBName.db";
    public static final String TEMP_TABLE_NAME = "temperature";
    public static final String BOOKS_COLUMN_ID = "id";
    public static final String COLUMN_DEGREE = "degree";
    public static final String COLUMN_DATE = "date";

    private HashMap hp;

    public SqlDb(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+TEMP_TABLE_NAME+
                        "(id integer primary key AUTOINCREMENT, degree text,date text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS temperature");
        onCreate(db);
    }

    public boolean insertTemp (String deg, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DEGREE, deg);
        contentValues.put(COLUMN_DATE, date);

        db.insert(TEMP_TABLE_NAME, null, contentValues);
        return true;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TEMP_TABLE_NAME,null,null);
        db.close();
    }



    public ArrayList<Tempe> getAllHistory() {
        ArrayList<Tempe> array_list = new ArrayList<Tempe>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from "+TEMP_TABLE_NAME, null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){

            int id= Integer.parseInt(cursor.getString(0));
            String name= String.valueOf(cursor.getString(1));
            String desc= String.valueOf(cursor.getString(2));

            array_list.add(new Tempe(id,name,desc));
            cursor.moveToNext();
        }
        return array_list;
    }
}
