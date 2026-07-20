package com.example.daatabace_issue.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.daatabace_issue.DataBace;

import java.util.ArrayList;

public class SQLHendler implements DataBace {

    private SQLiteDatabase db;
    private HelperDB hlp;
    private Context context;



    public SQLHendler(SQLiteDatabase db, HelperDB hlp, Context context) {
        this.db = db;
        this.hlp = hlp;
        this.context = context;
    }

    @Override
    public void Write(String des, String mon, int cat, String date, int id) {
        hlp = new HelperDB(this.context);
        db = hlp.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Percace.DESCRIPTION, des);
        values.put(Percace.AMOUNT, mon);
        values.put(Percace.CATEGORY, cat);
        values.put(Percace.DATE, date);
        values.put(Percace.KEY_ID, id);

        db.insert(Percace.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public ArrayList<String> Read(String description, String category, int down, int upper) {
        ArrayList<String> tbl = new ArrayList<>();

        hlp = new HelperDB(this.context);
        db = hlp.getReadableDatabase();
        Cursor crsr = db.query(Percace.TABLE_NAME, null, null, null, null, null, null);

        int col0 = crsr.getColumnIndex(Percace.KEY_ID);
        int col1 = crsr.getColumnIndex(Percace.DESCRIPTION);
        int col2 = crsr.getColumnIndex(Percace.AMOUNT);
        int col3 = crsr.getColumnIndex(Percace.CATEGORY);
        int col4 = crsr.getColumnIndex(Percace.DATE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col0);
            String des = crsr.getString(col1);
            int mon = crsr.getInt(col2);
            String cat = crsr.getString(col3);
            String dat = crsr.getString(col4);

            String tmp = "" + key + ", " + des + ", " + mon + ", " + cat + ", " + dat;
            if (Add_or_not_to_add(description, category, down, upper)) {
                tbl.add(tmp);
            }
            crsr.moveToNext();
        }
        crsr.close();
        db.close();


        return tbl;
    }


    private boolean Add_or_not_to_add(String des, String ca, int dow, int up){
        return true;
    }


    @Override
    public void delete(int pos) {
        db = hlp.getWritableDatabase();
        db.delete(Percace.TABLE_NAME, Percace.KEY_ID+"=?", new String[]{Integer.toString(pos + 1)});

        db.close();
    }
}
