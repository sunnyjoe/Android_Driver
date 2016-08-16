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
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;

    EditText nameEditText;
    EditText mobEditText;
    EditText emailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText)findViewById(R.id.contactName);
        mobEditText = (EditText)findViewById(R.id.mobileNumber);
        emailEditText = (EditText)findViewById(R.id.emailAddress);
    }

    public void saveNewContact(View view){
        String name = nameEditText.getText().toString();
        String mob = mobEditText.getText().toString();
        String email = emailEditText.getText().toString();

        userDbHelper = new UserDbHelper(context);
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        userDbHelper.addInfommations(name, mob, email, sqLiteDatabase);
    }

    public void viewContact(View view){
        Intent intent = new Intent(this, DataListActivity1.class);
        startActivity(intent);
    }

}
