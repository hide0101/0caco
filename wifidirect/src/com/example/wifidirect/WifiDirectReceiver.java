package com.example.wifidirect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.util.Log;

public class WifiDirectReceiver extends BroadcastReceiver {

	private WifiP2pManager manager;
	private Channel channel;
	private MainActivity activity;

	public WifiDirectReceiver(MainActivity activity, WifiP2pManager manager, Channel channel) {
		super();
		this.activity = activity;
		this.manager = manager;
		this.channel = channel;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
        if (manager == null) {
            return;
        }
		if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
			// リスナーを設定してピア一覧を取得
			manager.requestPeers(channel, new PeerListener(manager, channel));
		} else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            NetworkInfo networkInfo = (NetworkInfo) intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {
            	Log.i(MainActivity.TAG, "接続された");

                // 接続情報をリクエスト
                manager.requestConnectionInfo(channel, new ConnectionListener(this.activity));
            } else {
            	Log.i(MainActivity.TAG, "切断された");
            }
        }
	}

}