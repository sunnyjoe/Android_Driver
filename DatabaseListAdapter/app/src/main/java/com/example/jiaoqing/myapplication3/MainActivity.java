package com.example.jiaoqing.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewContact(View view){
        Intent intent = new Intent(this, DataListActivity1.class);
        startActivity(intent);
    }

    public void addContact(View view){
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivity(intent);
    }

    public void searchContact(View view){
        Intent intent = new Intent(this, SearchContactActivity.class);
        startActivity(intent);
    }
}
