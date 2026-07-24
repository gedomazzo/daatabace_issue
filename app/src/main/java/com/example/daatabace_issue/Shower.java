package com.example.daatabace_issue;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.daatabace_issue.SQLite.HelperDB;
import com.example.daatabace_issue.SQLite.Percace;
import com.example.daatabace_issue.SQLite.SQLHendler;

import java.util.ArrayList;

public class Shower extends MainActivity implements AdapterView.OnItemClickListener {
    private ListView dshow;
    private ArrayList<String> tbl = new ArrayList<>();
    private ArrayAdapter<String> adp;
    private AlertDialog.Builder allert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        UiMerrage();

        tbl = Adb.Read(null, null, -1, -1);
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tbl);
        dshow.setOnItemClickListener(this);
        dshow.setAdapter(adp);
    }



    public void UiMerrage() {
        dshow = findViewById(R.id.dshow);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        String tble = tbl.get(pos);

        allert = new AlertDialog.Builder(this);
        allert = new AlertDialog.Builder(this);
        allert.setTitle("Are you sure ?");
        allert.setMessage("Are you sure you want to delete " + tble + "?");

        allert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = Integer.parseInt(tble.split(",")[0]);
                Adb.delete(id-1);
                tbl.remove(pos);
                adp.notifyDataSetChanged();
            }
        });


        allert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        allert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean res = super.onCreateOptionsMenu(menu);
        menu.removeItem(2);
        return res;
    }
}