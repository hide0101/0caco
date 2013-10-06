package com.example.dmtc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileNotFoundException;

public class DBActivity extends Activity implements OnClickListener{
    private final int FP = ViewGroup.LayoutParams.FILL_PARENT; 
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 
    private Button button;

    private int DB_VERSION = 1;
    private int DB_MODE = Context.MODE_PRIVATE;

    @Override protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(linearLayout);

        button = new Button(this);
        button.setText("Create Database");
        button.setOnClickListener(this);
        linearLayout.addView(button, createParam(WC, WC));
    }

    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

    public void onClick(View v) {
        //SQLiteDatabase db;
        //getBaseContext() {
           SQLiteDatabase da = SQLiteDatabase.openOrCreateDatabase(new File("ƒfƒŒƒNŽæ‚è" + getBaseContext().getPackageName() + "//",
            ""),null);
      //  }
    }
}


