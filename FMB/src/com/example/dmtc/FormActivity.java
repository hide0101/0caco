package com.example.dmtc;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.content.Intent;
import android.database.sqlite.*;
import android.database.*;

public class FormActivity extends Activity implements OnClickListener {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.activity_form);
		Button SendButton=(Button) findViewById(R.id.button1);
		Button PictureButton=(Button) findViewById(R.id.button2);
		
		
		
		
		SendButton.setOnClickListener(this);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText name=(EditText)findViewById(R.id.EditText01);
		EditText facebookId=(EditText)findViewById(R.id.EditText02);
		EditText skypeId=(EditText)findViewById(R.id.EditText03);
		EditText lineId=(EditText)findViewById(R.id.EditText04);
		EditText amebaId=(EditText)findViewById(R.id.EditText05);
		EditText twitterId=(EditText)findViewById(R.id.EditText06);
		
		Intent it=new Intent(FormActivity.this,Card.class);
		it.putExtra("Name", name.getText().toString());
		it.putExtra("FB", facebookId.getText().toString());
		it.putExtra("SI", skypeId.getText().toString());
		it.putExtra("LI", lineId.getText().toString());
		it.putExtra("AI", amebaId.getText().toString());
		it.putExtra("TI", twitterId.getText().toString());
		
		startActivity(it);
		
		
	}

}
