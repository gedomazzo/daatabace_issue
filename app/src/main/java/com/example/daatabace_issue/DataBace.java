package com.example.daatabace_issue;

import android.content.Context;

import java.util.ArrayList;

public interface DataBace {

    void Write(String des, String mon, int cat, String date, int id);
    ArrayList<String> Read(String des, String category, int down, int upper);
    void delete(int id);



}
