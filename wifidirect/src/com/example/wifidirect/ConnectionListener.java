package com.example.wifidirect;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.util.Log;

public class ConnectionListener implements ConnectionInfoListener{
	private MainActivity activity;
	public ConnectionListener(MainActivity activity){
		this.activity = activity;
	}
	@Override
	public void onConnectionInfoAvailable(WifiP2pInfo info) {
		Log.i(MainActivity.TAG, "グループオーナーIP: " + info.groupOwnerAddress.getHostAddress());
		Log.i(MainActivity.TAG, "グループオーナーか否か: " + info.isGroupOwner);
		Log.i(MainActivity.TAG, "P2Pグループが正常に形成されたか: " + info.groupFormed);
		
		// グループオーナーIPを保持
		this.activity.setOwnerHost(info);
		
		// グループオーナーの場合に待ち受ける常駐スレッドを生成する
		if(info.groupFormed && info.isGroupOwner){
			ServerThread thread = new ServerThread();
			thread.start();
		}
	}
}