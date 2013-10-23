package com.example.subtest;

import java.security.PublicKey;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        
        ListView lv = (ListView)(findViewById(R.id.listView1));
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
             android.R.layout.simple_list_item_1);
        
        adapter.add("a");
        adapter.add("b");
        adapter.add("c");
        adapter.add("d");
        
        lv.setAdapter(adapter);
        
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){        
                //èàóù
            }
        
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //é¿çs
            }
            
        });
            

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub, menu);
        return true;
    }
}

