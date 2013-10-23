package com.example.subtest.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AddressEntryDao extends AbstractBaseDao<AddressEntry> {
    @Override
    public long insert(SQLiteDatabase db, AddressEntry target) {
       ContentValues values = new ContentValues();
       
       values.put(AddressEntry.COLUMN_ADDRESS_ID,target.getId());
       values.put(AddressEntry.COLUMN_ADDRESS_NAME, target.getName());
       values.put(AddressEntry.COLUMN_ADDRESS_FACEBOOK, target.getFacebook());
       values.put(AddressEntry.COLUMN_ADDRESS_TWITTER, target.getTwitter());
       values.put(AddressEntry.COLUMN_ADDRESS_LINE, target.getLine());
       values.put(AddressEntry.COLUMN_ADDRESS_SKYPE, target.getSkype());
       values.put(AddressEntry.COLUM_ADDRESS_TAB,target.getTab());
       
       
    return db.insertOrThrow(AddressEntry.TABLE_NAME, null, values);
       
    }

    @Override
    public void update(SQLiteDatabase db, AddressEntry target) {
        
        ContentValues values = new ContentValues();
        
        values.put(AddressEntry.COLUMN_ADDRESS_ID,target.getId());
        values.put(AddressEntry.COLUMN_ADDRESS_NAME, target.getName());
        values.put(AddressEntry.COLUMN_ADDRESS_FACEBOOK, target.getFacebook());
        values.put(AddressEntry.COLUMN_ADDRESS_TWITTER, target.getTwitter());
        values.put(AddressEntry.COLUMN_ADDRESS_LINE, target.getLine());
        values.put(AddressEntry.COLUMN_ADDRESS_SKYPE, target.getSkype());
        values.put(AddressEntry.COLUM_ADDRESS_TAB,target.getTab());
        
        db.update(AddressEntry.TABLE_NAME, values, AddressEntry.COLUMN_ADDRESS_ID + "=?", new String[] { Integer.toString(target.getId()) });
    }

    @Override
    public void delete(SQLiteDatabase db, AddressEntry target) {
        delete(db, target.getId());
    }
    
    public void delete(SQLiteDatabase db,Integer id){
        db.delete(AddressEntry.TABLE_NAME, AddressEntry.COLUMN_ADDRESS_ID + "=?", new String[]{ Integer.toString(id) });
    }

    
    @Override
    public AddressEntry load(SQLiteDatabase db, Integer id) {
        Cursor cursor =db.query(AddressEntry.TABLE_NAME, null, AddressEntry.COLUMN_ADDRESS_ID+ "=?", new String[] {Integer.toString(id)},
                null,null, null);
        boolean isRowExist =cursor.moveToFirst();
        AddressEntry entry = null;
        
        if(isRowExist){
            entry =new AddressEntry(cursor);
        }
        cursor.close();
        return entry;
    }

    @Override
    public List<AddressEntry> list(SQLiteDatabase db, String orderBy,
            int offset, int limit) {
        
        List<AddressEntry> list = new ArrayList<AddressEntry>();
        Cursor cursor = db.query(AddressEntry.TABLE_NAME, null, null, null, null,null, orderBy, offset +","+ limit); 
        boolean isRowExist = cursor.moveToFirst();
        
        if(isRowExist){
            while (!cursor.isAfterLast()){
                list.add(new AddressEntry(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public List<AddressEntry> list(SQLiteDatabase db, String orderBy) {
        
        List<AddressEntry> list = new ArrayList<AddressEntry>();
        Cursor cursor = db.query(AddressEntry.TABLE_NAME, null,null, null, null, null, orderBy);
        boolean isRowExist =cursor.moveToFirst();
        
        if(isRowExist) {
            while(!cursor.isAfterLast()) {
                list.add(new AddressEntry(cursor));
                cursor.moveToNext();
            }
        }
       cursor.close();
        return list;
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS '"+AddressEntry.TABLE_NAME+"' (" +
    "'"+AddressEntry.COLUMN_ADDRESS_ID                  +"' INTEGER PRIMARY KEY AUTOINCREMENT," +
    "'"+AddressEntry.COLUMN_ADDRESS_NAME                +"' TEXT NOT NULL,"+
    "'"+AddressEntry.COLUMN_ADDRESS_FACEBOOK            +"' TEXT,"+
    "'"+AddressEntry.COLUMN_ADDRESS_TWITTER             +"' TEXT,"+
    "'"+AddressEntry.COLUMN_ADDRESS_LINE                +"' TEXT,"+
    "'"+AddressEntry.COLUMN_ADDRESS_SKYPE               +"' TEXT,"+
    "'"+AddressEntry.COLUM_ADDRESS_TAB                  +"' INTEGER NOT NULL"+
    ");");
        
    }
    
}
