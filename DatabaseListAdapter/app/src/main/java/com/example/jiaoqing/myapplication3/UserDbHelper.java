package com.example.jiaoqing.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jiaoqing on 15/8/16.
 */
public class UserDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Userinfo.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY = "CREATE TABLE " + UserContact.NewUserInfo.table_name
            + "(" + UserContact.NewUserInfo.user_name + " TEXT,"
            + UserContact.NewUserInfo.user_mob + " TEXT,"
            + UserContact.NewUserInfo.user_email + " TEXT)";

    public UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "DATAVASE CREATED");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "onCreate");
    }

    public Cursor getInformations(SQLiteDatabase db){
        String[] projections = {UserContact.NewUserInfo.user_name, UserContact.NewUserInfo.user_email, UserContact.NewUserInfo.user_email};
        Cursor cursor = db.query(UserContact.NewUserInfo.table_name, projections, null, null, null, null, null);
        return cursor;
    }

    public Cursor getContact(String userName, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserContact.NewUserInfo.user_name, UserContact.NewUserInfo.user_mob, UserContact.NewUserInfo.user_email};
        String selection = UserContact.NewUserInfo.user_name + " LIKE ?";
        String[] selection_args = {"%" + userName + "%"};
        Cursor cursor = sqLiteDatabase.query(UserContact.NewUserInfo.table_name, projections, selection, selection_args, null, null, null);
        return cursor;
    }

    public void addInfommations(String name, String mob, String email, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContact.NewUserInfo.user_name, name);
        contentValues.put(UserContact.NewUserInfo.user_mob, mob);
        contentValues.put(UserContact.NewUserInfo.user_email, email);
        db.insert(UserContact.NewUserInfo.table_name, null, contentValues);

        Log.e("DATABASE OPERATIONS", "addInfommations");
    }

    public void deleteInformation(String userName, SQLiteDatabase db){
        String selection = UserContact.NewUserInfo.user_name + " LIKE ?";
        String[] selection_args = {"%" + userName + "%"};
        db.delete(UserContact.NewUserInfo.table_name,selection, selection_args);
    }

    public void updateInfommations(String oldName, String name, String mob, String email, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContact.NewUserInfo.user_name, name);
        contentValues.put(UserContact.NewUserInfo.user_mob, mob);
        contentValues.put(UserContact.NewUserInfo.user_email, email);

        String selection = UserContact.NewUserInfo.user_name + " LIKE ?";
        String[] selection_args = {"%" + oldName + "%"};
        db.update(UserContact.NewUserInfo.table_name, contentValues, selection, selection_args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
