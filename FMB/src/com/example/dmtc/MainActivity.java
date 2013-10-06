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

            // �v�f�̒ǉ��i1�j 
        adapter.add("�}�C�A�J�E���g");
        adapter.add("�F�B");
        adapter.add("�T�[�N��");
        adapter.add("�o�C�g");
        
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
                   
                   //����
                }
            
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //���s
                }});

               /* @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    // TODO �����������ꂽ���\�b�h�E�X�^�u
                    return false;
                }
        });
        */
    }

    private void lvsetOnItemClickListener(
            OnItemClickListener onItemClickListener) {
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   
} 


