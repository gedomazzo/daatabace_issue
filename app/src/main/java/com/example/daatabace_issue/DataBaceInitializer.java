package com.example.daatabace_issue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.daatabace_issue.SQLite.HelperDB;
import com.example.daatabace_issue.SQLite.SQLHendler;

public class DataBaceInitializer {


    public DataBace InitializeSQLiteDatabace(Context context){
        HelperDB hlp = new HelperDB(context);
        SQLiteDatabase db = hlp.getReadableDatabase();
        return new SQLHendler(db, hlp, context);
    }


}
