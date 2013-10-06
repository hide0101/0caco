package ya.sample;

import android.os.*;
import android.app.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class Sample3_5 extends Activity {
	TextView tv;
	Button bt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv = new TextView(this);
		tv.setText("Ç¢ÇÁÇ¡ÇµÇ·Ç¢Ç‹Çπ");
		bt = new Button(this);
		bt.setText("çwì¸");
		ll.addView(tv);
		ll.addView(bt);

		bt.setOnTouchListener(new SampleTouch());
	}

	class SampleTouch implements OnTouchListener {
		public boolean onTouch(View v, MotionEvent e) {
			if (e.getAction() == MotionEvent.ACTION_DOWN) {
				tv.setText("Ç±ÇÒÇ…ÇøÇÌ");
			} else if (e.getAction() == MotionEvent.ACTION_UP) {
				tv.setText("Ç≥ÇÊÇ§Ç»ÇÁ");
			}
			return true;
		}
	}

}
