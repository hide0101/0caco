package com.example.collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
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
	static ArrayAdapter<String> adapter;
	ListView list;
	Context context;
	private SimpleCursorAdapter myadapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);

		MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(getApplicationContext());
		mydb = hlpr.getWritableDatabase();
		ListView list = (ListView)findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		Cursor cursor = mydb.query(TABLE_NAME, new String[] {COLUMN_ADDRESS_ID,COLUMN_ADDRESS_NAME,
				COLUMN_ADDRESS_FACEBOOK,COLUMN_ADDRESS_TWITTER,
				COLUMN_ADDRESS_LINE,COLUMN_ADDRESS_SKYPE}, null, null, null, null,null);

		boolean isEof = cursor.moveToFirst();
		while (isEof) {
			String listdata = String.format("Name: %s",cursor.getString(1));
			adapter.add(listdata); 
			isEof = cursor.moveToNext();
		}


		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				// クリックされたアイテムを取得します
				String item = (String) listView.getItemAtPosition(position);
				String result=SearchName(item);
				TextView TV= (TextView)findViewById(R.id.textView1);
				TV.setText(result);
				setContentView(R.layout.activity_main);
			}


		});
		setContentView(R.layout.list);
		list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				// 選択されたアイテムを取得します
				String item = (String) listView.getSelectedItem();
				String result=SearchName(item);
				TextView TV= (TextView)findViewById(R.id.textView1);
				TV.setText(result);
				setContentView(R.layout.activity_main);

			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}


	private static class MySQLiteOpenHelper extends SQLiteOpenHelper {
		public MySQLiteOpenHelper(Context c) {
			super(c, DB, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// create table.
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
	private static  String SearchName(String searchNAME){
		// This time,I use rawQuery.
		String SQL_SELECT = "SELECT * FROM "+ DB + " WHERE "+ COLUMN_ADDRESS_NAME + "=" + searchNAME + ";";
		//selectionArgs : If you want to use where phrase,this sentence will be used.
		Cursor mCursor = mydb.rawQuery(SQL_SELECT, null);

		// if there is not same data,mCursor.moveToFirst() return false.(maybe...)
		if (mCursor.moveToFirst()) {
			String text="";
			text += "Name:"+mCursor.getString(mCursor
					.getColumnIndex(COLUMN_ADDRESS_NAME))+"/n";
			text += "Facebook;"+mCursor.getString(mCursor
					.getColumnIndex(COLUMN_ADDRESS_FACEBOOK))+"/n";
			text += "Twitter:"+mCursor.getString(mCursor
					.getColumnIndex(COLUMN_ADDRESS_TWITTER))+"/n";
			text += "Line:"+mCursor.getString(mCursor
					.getColumnIndex(COLUMN_ADDRESS_LINE))+"/n";
			text += "Skype:"+mCursor.getString(mCursor
					.getColumnIndex(COLUMN_ADDRESS_SKYPE))+"/n";
			// return text
			return text;
		} else {
			// 
			return null;
		}



	}




}






