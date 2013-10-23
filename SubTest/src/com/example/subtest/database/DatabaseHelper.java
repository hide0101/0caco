package com.example.subtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    
    private static final int DB_VERSION = 1;
    private static final String DATABASE_NAME = "ocaco";
    
    public DatabaseHelper(Context context)
             {
        super(context, DATABASE_NAME, null, DB_VERSION);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
        // テーブルの作成
        
        AddressEntryDao addressEntryDao = new AddressEntryDao();
        addressEntryDao.createTable(db);
        
  
        TabEntryDao tabEntryDao = new TabEntryDao();
        tabEntryDao.createTable(db);
        
        // ダミーデータの挿入
        
        TabEntry tabEntry = new TabEntry();
        tabEntry.setIdName("大学");
        tabEntryDao.insert(db, tabEntry);
        
        tabEntry = new TabEntry();
        tabEntry.setIdName("バイト先");
        tabEntryDao.insert(db, tabEntry);
        
        tabEntry = new TabEntry();
        tabEntry.setIdName("サークル");
        tabEntryDao.insert(db, tabEntry);
        
        tabEntry = new TabEntry();
        tabEntry.setIdName("その他");
        tabEntryDao.insert(db, tabEntry);
        
        AddressEntry addressEntry = new AddressEntry();
        addressEntry.setName("aaaaaa");
        addressEntry.setTab(1);
        addressEntry.setFacebook("aaaafb");
        addressEntry.setLine("aaline");
        addressEntry.setSkype("aaaskype");
        addressEntryDao.insert(db, addressEntry);
        
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 自動生成されたメソッド・スタブ
    }

}
