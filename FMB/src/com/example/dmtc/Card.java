package com.example.dmtc;

import com.example.dmtc.TitleActivity.SetOnClickListener1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Card extends Activity {

	
	String name,fb,si,li,ai,ti;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);
		Intent It = getIntent();
		if(It != null){
			name=It.getStringExtra("Name");
			fb=It.getStringExtra("FB");
			 si=It.getStringExtra("SI");
			 li=It.getStringExtra("LI");
			 ai=It.getStringExtra("AI");
			 ti=It.getStringExtra("TI");
		}
		TextView Name1=(TextView) findViewById(R.id.TextView06);
		TextView FB1=(TextView) findViewById(R.id.TextView05);
		TextView SI1=(TextView) findViewById(R.id.TextView04);
		TextView LI1=(TextView) findViewById(R.id.TextView03);
		TextView AI1=(TextView) findViewById(R.id.TextView02);
		TextView TI1=(TextView) findViewById(R.id.TextView01);
		Button bt = (Button) findViewById(R.id.button1);
		bt.setOnClickListener(new SetOnClickListener1());
		
		Name1.setText("名前:  "+ name);
		FB1.setText("facebook:id　 " + fb);
		SI1.setText("skype:id　 " + si);
		LI1.setText("LINE:id　 "+ li);
		AI1.setText("ameba:id　 "+ ai);
		TI1.setText("twitter:id　 " + ti);
	}
	class SetOnClickListener1 implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(Card.this, TitleActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	}
