package com.example.sqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.view.View;
import android.content.ContentValues;
 
public class sqlitedemo extends Activity{
	static final String DB = "sqlite_sample.db";
    static final int DB_VERSION = 1;
    static final String DROP_TABLE = "drop table mytable;";
    public static final String TABLE_NAME = "addressbook";
    public static final String COLUMN_ADDRESS_ID = "address_id";
    public static final String COLUMN_ADDRESS_NAME = "address_name";
    public static final String COLUMN_ADDRESS_FACEBOOK = "address_facebook";
    public static final String COLUMN_ADDRESS_TWITTER = "addres_twitter";
    public static final String COLUMN_ADDRESS_LINE = "address_line";
    public static final String COLUMN_ADDRESS_SKYPE = "address_skype";
    public static final String COLUM_ADDRESS_TAB = "address_tab";
    static SQLiteDatabase mydb;
 
    private SimpleCursorAdapter myadapter;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
 
        MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(getApplicationContext());
        mydb = hlpr.getWritableDatabase();
        
 
    }
 
    private static class MySQLiteOpenHelper extends SQLiteOpenHelper {
        public MySQLiteOpenHelper(Context c) {
            super(c, DB, null, DB_VERSION);
        }
           
			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO 自動生成されたメソッド・スタブ
			    db.execSQL("CREATE TABLE IF NOT EXISTS '"+TABLE_NAME+"' (" +
			            "'"+COLUMN_ADDRESS_ID                  +"' INTEGER PRIMARY KEY AUTOINCREMENT," +
			            "'"+COLUMN_ADDRESS_NAME                +"' TEXT NOT NULL,"+
			            "'"+COLUMN_ADDRESS_FACEBOOK            +"' TEXT,"+
			            "'"+COLUMN_ADDRESS_TWITTER             +"' TEXT,"+
			            "'"+COLUMN_ADDRESS_LINE                +"' TEXT,"+
			            "'"+COLUMN_ADDRESS_SKYPE               +"' TEXT,"+
			            "'"+COLUM_ADDRESS_TAB                  +"' INTEGER NOT NULL"+
			            ");");
			                
			}
			
        
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
    
    private static void InsertSQL(String sql){
    	mydb.execSQL(sql);
    	
    	
    	
    }
    
    private static void InsertAdress(String NAME,String ADDRESS_FACEBOOK,String ADDRESS_TWITTER,String ADDRESS_LINE,String ADDRESS_SKYPE,String ADDRESS_TAB){
    	//if there is same name,call update.
    	ContentValues insertValues = new ContentValues();
    	                insertValues.put(COLUMN_ADDRESS_NAME,NAME );
    	                insertValues.put(COLUMN_ADDRESS_FACEBOOK,ADDRESS_FACEBOOK );
    	                insertValues.put(COLUMN_ADDRESS_TWITTER,ADDRESS_TWITTER );
    	                insertValues.put(COLUMN_ADDRESS_LINE, ADDRESS_LINE);
    	                insertValues.put(COLUMN_ADDRESS_SKYPE,ADDRESS_SKYPE );
    	                insertValues.put(COLUM_ADDRESS_TAB,ADDRESS_TAB );
    	                mydb.insert(TABLE_NAME, null, insertValues);
    }
    
    private static void UpdateAdress(String updateNAME,String ADDRESS_FACEBOOK,String ADDRESS_TWITTER,String ADDRESS_LINE,String ADDRESS_SKYPE,String ADDRESS_TAB){
    
    	ContentValues updateValues = new ContentValues();
    	updateValues.put(COLUMN_ADDRESS_FACEBOOK,ADDRESS_FACEBOOK );
    	updateValues.put(COLUMN_ADDRESS_TWITTER,ADDRESS_TWITTER );
    	updateValues.put(COLUMN_ADDRESS_LINE, ADDRESS_LINE);
    	updateValues.put(COLUMN_ADDRESS_SKYPE,ADDRESS_SKYPE );
    	updateValues.put(COLUM_ADDRESS_TAB,ADDRESS_TAB );
        mydb.update(TABLE_NAME,updateValues,"COLUMN_ADDRESS_NAME="+updateNAME,null);
    	
    }
    private static boolean SearchName(String searchNAME){
    	// This time,I use rawQuery.
    	String SQL_SELECT = "SELECT * FROM "+ DB + " WHERE "+ COLUMN_ADDRESS_NAME + "=" + searchNAME + ";";
    	//selectionArgs : If you want to use where phrase　,this sentence will be used.
    	Cursor mCursor = mydb.rawQuery(SQL_SELECT, null);
    	 
    	// if there is not same data,mCursor.moveToFirst() return false.(maybe...)
    	if (mCursor.moveToFirst()) {
    	    //update data.
    		
    		
    	} else {
    	    // Create new data.
    	    
    	}
    	
    	return mCursor.moveToFirst();
    	
    }
    
    
    
    
}
 
    
   
