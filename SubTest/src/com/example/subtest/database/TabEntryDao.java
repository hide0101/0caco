package com.example.subtest.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TabEntryDao extends AbstractBaseDao<TabEntry> {

    @Override
    public long insert(SQLiteDatabase db, TabEntry target) {
        
        ContentValues values = new ContentValues();
        
        values.put(TabEntry.COLUMN_ADDRESS_TABID,target.getTabId());
        values.put(TabEntry.COLUMN_ADDRESS_IDNAME,target.getIdName());
        
        return db.insertOrThrow(TabEntry.TABLE_NAME, null, values);
    }

    @Override
    public void update(SQLiteDatabase db, TabEntry target) {
        ContentValues values = new ContentValues();
        
        values.put(TabEntry.COLUMN_ADDRESS_TABID,target.getTabId());
        values.put(TabEntry.COLUMN_ADDRESS_IDNAME,target.getIdName());
        
        db.update(TabEntry.TABLE_NAME, values, TabEntry.COLUMN_ADDRESS_TABID + "=?", new String[] { Integer.toString(target.getTabId())});
        
    }

    @Override
    public void delete(SQLiteDatabase db, TabEntry target) {
        delete(db, target.getTabId());
        
    }
    public void delete(SQLiteDatabase db,Integer id){
      db.delete(TabEntry.TABLE_NAME, TabEntry.COLUMN_ADDRESS_TABID + "=?", new String[]{ Integer.toString(id) });
        
    }

    @Override
    public TabEntry load(SQLiteDatabase db, Integer id) {
        Cursor cursor =db.query(TabEntry.TABLE_NAME, null, TabEntry.COLUMN_ADDRESS_TABID + "=?", new String[] {Integer.toString(id)},
       null,null,null);
        boolean isRowExist = cursor.moveToFirst();
        TabEntry entry = null;
        
        if(isRowExist){
             entry = new TabEntry(cursor);
        }
        cursor.close();
                return entry;
    }

    @Override
    public List<TabEntry> list(SQLiteDatabase db, String orderBy,
            int offset,int limit) {
       
        List<TabEntry> list = new ArrayList<TabEntry>();
        Cursor cursor = db.query(TabEntry.TABLE_NAME, null, null, null, null, null, orderBy, offset+","+ limit);
        boolean isRowExist = cursor.moveToFirst();
        //しおり
        
        if(isRowExist) {
            while(!cursor.isAfterLast()) {
                list.add(new TabEntry(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public List<TabEntry> list(SQLiteDatabase db, String orderBy) {
        List<TabEntry> list = new ArrayList<TabEntry>();
        Cursor cursor = db.query(TabEntry.TABLE_NAME, null, null, null, null, null, orderBy);
        boolean isRowExist = cursor.moveToFirst();
        //しおり
        
        if(isRowExist) {
            while(!cursor.isAfterLast()) {
                list.add(new TabEntry(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
        // TODO 自動生成されたメソッド・スタブ
     
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS '"+TabEntry.TABLE_NAME+"' (" +
    "'"+TabEntry.COLUMN_ADDRESS_TABID    +"' INTEGER PRIMARY KEY AUTOINCREMENT," +
    "'"+TabEntry.COLUMN_ADDRESS_IDNAME   +"' TEXT"+
    ");");
        
        // TODO 自動生成されたメソッド・スタブ
        
    }

}
