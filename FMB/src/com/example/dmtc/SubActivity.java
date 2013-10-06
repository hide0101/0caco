package com.example.dmtc;

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
        
        adapter.add("田中太郎");
        adapter.add("田中次郎");
        adapter.add("山田良子");
        adapter.add("田中良子");
        adapter.add("佐藤良子");
        adapter.add("良子よしこ");
        
        lv.setAdapter(adapter);
        
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){        
                //処理
            }
        
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //実行
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

