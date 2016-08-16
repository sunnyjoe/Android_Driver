package com.example.jiaoqing.myapplication3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class NewContactActivity extends AppCompatActivity {
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;

    EditText nameEditText;
    EditText mobEditText;
    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_layout);

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
}
