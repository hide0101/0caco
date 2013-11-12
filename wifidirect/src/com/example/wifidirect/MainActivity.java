package com.example.wifidirect;

import com.example.wifid03.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public static final String TAG = "SAMPLE";
	public static final int port = 9999;
	private WifiP2pManager manager;
	private final IntentFilter intentFilter = new IntentFilter();
	private Channel channel;
	private BroadcastReceiver receiver = null;
	private String host;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// ピア一覧とその状態を受け取るためのインテントアクション
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
		
		// 接続状態を受け取るためのインテントアクション
		intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
		
		// WifiP2pManagerインスタンスを取得
		manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
		
		// WifiP2pManager.Channelインスタンスを取得
		channel = manager.initialize(this, getMainLooper(), null);

		// ピアの探索を開始する
		manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {

			@Override
			public void onSuccess() {
				Log.i(TAG, "探索成功");
			}

			@Override
			public void onFailure(int reasonCode) {
				Log.i(TAG, "探索失敗");
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		
		// レシーバーを設定
		receiver = new WifiDirectReceiver(this, manager, channel);
		registerReceiver(receiver, intentFilter);
	}

	@Override
	public void onPause() {
		super.onPause();
		
		// レシーバーを解除
		unregisterReceiver(receiver);
	}
	
	public void sendMessage(View view){
		// 送信ボタン押下時に入力されたテキストを取得
		EditText editText = (EditText) findViewById(R.id.send_message);
		String str = editText.getText().toString();
		
		// 入力テキストを送信
		ClientAsyncTask client = new ClientAsyncTask(this.host, str);
		client.execute();
	}
	
	public void setOwnerHost(WifiP2pInfo info){
		this.host = info.groupOwnerAddress.getHostAddress();
	}
}