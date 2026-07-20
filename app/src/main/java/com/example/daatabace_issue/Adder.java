package com.example.daatabace_issue;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.daatabace_issue.SQLite.HelperDB;
import com.example.daatabace_issue.SQLite.SQLHendler;

public class Adder extends MainActivity implements AdapterView.OnItemSelectedListener{

    private EditText des, mon, date;
    private Spinner cat;
    private Button add;

    private int id = 0;
    private String Sdes, Smon, Sdat;
    private int Scat;
    private final String[] categories = {"select category:", "food", "clothes", "electronics", "drugs", "other"};
    DataBace Adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UiMerrage();
        InitializeSQLiteDatabace();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
        };
        cat.setOnItemSelectedListener(this);
        cat.setAdapter(adapter);
    }


    private void InitializeSQLiteDatabace(){
        HelperDB hlp = new HelperDB(this);
        SQLiteDatabase db = hlp.getReadableDatabase();
        Adb = new SQLHendler(db, hlp, this);
    }


    public void Push(View view) {
        Sdes = des.getText().toString();
        Smon = mon.getText().toString();
        Sdat = date.getText().toString();
        id ++;
        if (Sdes.equals("") || Smon.equals("") || Sdat.equals("") || Scat == 0 || CheckDate(Sdat)){
            AlertDialog.Builder err = new AlertDialog.Builder(this);

            err.setTitle("Oops, something is wrong");
            err.setMessage("Please check your input");
            err.setPositiveButton("Ok", (dialogInterface, i) -> {
                dialogInterface.cancel();
            });
            err.show();

            return;
        }

        Adb.Write(Sdes, Smon, Scat, Sdat, id);
    }


    public void UiMerrage(){
        des = findViewById(R.id.des2);
        mon = findViewById(R.id.mon);
        cat = findViewById(R.id.cat);
        date = findViewById(R.id.date);
        add = findViewById(R.id.add);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        boolean res = super.onCreateOptionsMenu(menu);
        menu.removeItem(1);
        return res;
    }
    @Override
    public Intent WhereToGo(String item){

        Intent intent = new Intent(this, MainActivity.class);

        if (item.equals("Show")) {
            intent = new Intent(this, Shower.class);
        } else if (item.equals("Filter")) {
            //intent = new Intent(this, Filter.class);
        } else {
            //intent = new Intent(this, Credits.class);
        }
        startActivity(intent);

        return intent;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Scat = pos;
        Log.i("spinner", categories[pos] + " selected");
    }
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("spinner", "nothing selected");
    }
    public static boolean CheckDate(@NonNull String date){
        String[] split = date.split("/");

        if ((Integer.parseInt(split[1]) > 12) || (Integer.parseInt(split[1]) == 0)){
            return true;
        }

        if (split.length == 3) {
            int day = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int year = Integer.parseInt(split[2]);

            if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                if (day > 30) {
                    return true;
                }
            } else if (month == 2) {
                if (year % 4 == 0) {
                    if (day > 29) {
                        return true;
                    }
                } else {
                    if (day > 28) {
                        return true;
                    }
                }
            } else {
                if (day > 31) {
                    return true;
                }
            }
        } else return true;

        return false;
    }

}