package com.example.jiaoqing.myapplication3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

public class DataListActivity1 extends AppCompatActivity implements PopupMenuCallBack{
    ListView listView;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    int selectedPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_list1);
        listView = (ListView)findViewById(R.id.data_listView);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                showPopUp(view);
            }
        });

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getWritableDatabase();

        reloadData();
    }

    public void reloadData(){
        cursor = userDbHelper.getInformations(sqLiteDatabase);

        listDataAdapter.clear();
        if (cursor.moveToFirst()){
            do{
                DataProvider dataProvider = new DataProvider(cursor.getString(0), cursor.getString(1), cursor.getString(2));

                listDataAdapter.add(dataProvider);
            }while(cursor.moveToNext());
        }
        listDataAdapter.notifyDataSetChanged();
    }

    public void deleteContact(){
        DataProvider dataProvider = (DataProvider) listDataAdapter.getItem(selectedPosition);
        userDbHelper.deleteInformation(dataProvider.getName(), sqLiteDatabase);
        reloadData();
    }

    public void showPopUp(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.my_contextual_menu, popupMenu.getMenu());
        PopupMenuEventHandler popupMenuEventHandler = new PopupMenuEventHandler(getApplicationContext(), this);
        popupMenu.setOnMenuItemClickListener(popupMenuEventHandler);
        popupMenu.show();
    }

    public void popupMenuDidClickDelete(){
        deleteContact();
    }

    public void popupMenuDidClickUpdate(){
        Log.e("DATABASE OPERATIONS", "popupMenuDidClickDelete update");
    }
}
