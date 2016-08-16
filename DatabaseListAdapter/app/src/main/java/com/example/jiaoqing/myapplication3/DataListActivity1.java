package com.example.jiaoqing.myapplication3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DataListActivity1 extends AppCompatActivity {
    ListView listView;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListDataAdapter listDataAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_list1);
        listView = (ListView)findViewById(R.id.data_listView);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        cursor = userDbHelper.getInformations(sqLiteDatabase);

        if (cursor.moveToFirst()){
            do{
                DataProvider dataProvider = new DataProvider(cursor.getString(0), cursor.getString(1), cursor.getString(2));

                listDataAdapter.add(dataProvider);
            }while(cursor.moveToNext());
        }

    }
}
