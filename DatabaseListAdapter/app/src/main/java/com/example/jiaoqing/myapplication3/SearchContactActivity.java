package com.example.jiaoqing.myapplication3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class SearchContactActivity extends AppCompatActivity {
    ListView listView;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        Log.e("Enter search", "Enter search");

        editText = (EditText)findViewById(R.id.search_edit_text);
        listView = (ListView)findViewById(R.id.data_listView);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getWritableDatabase();

    }


    public void searchContactButtonClicked(View view){
        String keyW = editText.getText().toString();
        cursor = userDbHelper.getContact(keyW, sqLiteDatabase);

        listDataAdapter.clear();
        int count = 0;
        if (cursor.moveToFirst()){
            do{
                DataProvider dataProvider = new DataProvider(cursor.getString(0), cursor.getString(1), cursor.getString(2));

                listDataAdapter.add(dataProvider);
            }while(cursor.moveToNext());
        }
        listDataAdapter.notifyDataSetChanged();
    }
}
