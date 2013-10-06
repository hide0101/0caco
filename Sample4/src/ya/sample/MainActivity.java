package ya.sample;

import android.os.*;
import android.app.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {
	TextView tv;
	Button bt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);
		
		tv = new TextView(this);
		tv.setText("��������Ⴂ�܂�");
		bt = new Button(this);
		bt.setText("�w��");
		
		ll.addView(tv);
		ll.addView(bt);
		
		bt.setOnClickListener(new SampleClickListener());
		
	}
	
	class SampleClickListener implements OnClickListener{
		public void onClick(View v) {
			tv.setText("���肪�Ƃ��������܂�");

		}

		
	}
	
	

}



