package com.example.daatabace_issue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener{

    public DataBace Adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaceInitializer dBI = new DataBaceInitializer();
        Adb = dBI.InitializeSQLiteDatabace(this);



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(1, 1, 50, "Add");
        menu.add(1 , 2, 100, "Show");
        menu.add(1, 3, 200, "Filter");
        menu.add(1, 4, 300, "Credit");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        Intent intent = WhereToGo(item.getTitle().toString());
        finish();
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    public Intent WhereToGo(String item){
        Intent intent = new Intent(this, MainActivity.class);

        if (item.equals("Show")) {
            intent = new Intent(this, Shower.class);
        } else if (item.equals("Filter")) {
            //intent = new Intent(this, Filter.class);
        } else if (item.equals("Add")) {
            intent = new Intent(this, Adder.class);
        } else {
            //intent = new Intent(this, Credits.class);}
        }
        finish();
        startActivity(intent);

        return intent;
    }

}