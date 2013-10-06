package com.example.dmtc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
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

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "oncreate");
        super.onCreate(savedInstanceState);
        Log.d("debug", "start");
        setContentView(R.layout.activity_list);
        
        ListView lv = (ListView)(findViewById(R.id.listView1));
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

            // 要素の追加（1） 
        adapter.add("マイアカウント");
        adapter.add("友達");
        adapter.add("サークル");
        adapter.add("バイト");
        
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                Intent intent = new Intent (MainActivity.this, SubActivity.class);
                startActivity(intent);
                
            }
        });
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id){        
                   
                   //処理
                }
            
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //実行
                }});

               /* @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    // TODO 自動生成されたメソッド・スタブ
                    return false;
                }
        });
        */
    }

    private void lvsetOnItemClickListener(
            OnItemClickListener onItemClickListener) {
        // TODO 自動生成されたメソッド・スタブ
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   
} 


