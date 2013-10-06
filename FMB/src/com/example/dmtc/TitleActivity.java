package com.example.dmtc;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.*;

public class TitleActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_title);
		Button Settingbt = (Button) findViewById(R.id.button1);	
		Button Changebt = (Button) findViewById(R.id.button2);		
		Button Managementbt = (Button) findViewById(R.id.button3);
		Settingbt.setOnClickListener(new SetOnClickListener1());
		Changebt.setOnClickListener(new SetOnClickListener2());
		Managementbt.setOnClickListener(new SetOnClickListener3());

	}

	class SetOnClickListener1 implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(TitleActivity.this, FormActivity.class);
			startActivity(intent);
		}

	}

	class SetOnClickListener2 implements OnClickListener {

		public void onClick(View v) {
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TitleActivity.this);
	        // アラートダイアログのタイトルを設定します
	        alertDialogBuilder.setTitle("送信方法");
	        // アラートダイアログのメッセージを設定します
	        alertDialogBuilder.setMessage("送信方法を選んでください。");
	        // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
	        alertDialogBuilder.setPositiveButton("送信",
	                new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                    	Intent intent = new Intent(TitleActivity.this, BTActivity.class);
	            			startActivity(intent);
	                    }
	                });
	        // アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
	        alertDialogBuilder.setNegativeButton("受信",
	                new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                    	Intent intent = new Intent(TitleActivity.this, BTActivity.class);
	            			startActivity(intent);
	                    }
	                });
	        // アラートダイアログのキャンセルが可能かどうかを設定します
	        alertDialogBuilder.setCancelable(true);
	        AlertDialog alertDialog = alertDialogBuilder.create();
	        // アラートダイアログを表示します
	        alertDialog.show();
			
		}
	}
	
	class SetOnClickListener3 implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(TitleActivity.this, MainActivity.class);
			startActivity(intent);
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title, menu);
		return true;
	}

}
