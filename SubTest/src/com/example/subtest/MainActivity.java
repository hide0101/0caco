package com.example.subtest;

import java.util.List;

import com.example.subtest.database.AddressEntry;
import com.example.subtest.database.AddressEntryDao;
import com.example.subtest.database.DatabaseHelper;
import com.example.subtest.database.TabEntry;
import com.example.subtest.database.TabEntryDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
        AddressEntryDao addressEntryDao = new AddressEntryDao();
        
        List<AddressEntry> entryList = addressEntryDao.list(db, AddressEntry.COLUMN_ADDRESS_ID);
        
        db.close();
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<AddressEntry> adapter = new ArrayAdapter<AddressEntry>(this, android.R.layout.simple_list_item_1, entryList);
        
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
        
        // �^�b�v���ꂽ�v�f�̃f�[�^��\�����܂�
        AddressEntry entry = (AddressEntry) parent.getItemAtPosition(position);
        
        String msg = "ID�F"+entry.getId()+",���O�F"+entry.getName()+",FB�F"+entry.getFacebook()+
                ",TW�F"+entry.getTwitter()+",SKYPE�F"+entry.getSkype()+"LINE�F"+entry.getLine()+"TABID�F"+entry.getTab();
        
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        
        
        // �^�b�v���ꂽ�v�f��TABID�̃f�[�^�������o���A�\�����܂�
        SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
        TabEntryDao tabEntryDao = new TabEntryDao();
        
        TabEntry tab = tabEntryDao.load(db, entry.getTab());
        db.close();
        
        msg = "TABID:" + tab.getTabId() + ",Name:" + tab.getIdName();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();;
    }

    
   
} 


