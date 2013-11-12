package com.example.wifidirect;

import java.util.ArrayList;
import java.util.List;

import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;

public class PeerListener implements PeerListListener {

	private List<WifiP2pDevice> peerList = new ArrayList<WifiP2pDevice>();
	private WifiP2pManager manager;
	private Channel channel;

	public PeerListener(WifiP2pManager manager, Channel channel) {
		this.manager = manager;
		this.channel = channel;
	}

	@Override
	public void onPeersAvailable(WifiP2pDeviceList peers) {
		
		// ピア一覧を取得
		peerList.addAll(peers.getDeviceList());
		if (peerList.size() > 0) {
			for (int i = 0; i < peerList.size(); i++) {
				// 各ピアのデバイス情報を取得
				WifiP2pDevice device = peerList.get(i);

				if(device.status == WifiP2pDevice.AVAILABLE){

	                WifiP2pConfig config = new WifiP2pConfig();
	                config.deviceAddress = device.deviceAddress;
	                config.wps.setup = WpsInfo.PBC;
	                
	                manager.connect(channel, config, new ActionListener() {
	
	                    @Override
	                    public void onSuccess() {
	                    	Log.i(MainActivity.TAG, "接続リクエスト成功");
	                    }
	
	                    @Override
	                    public void onFailure(int reason) {
	                    	Log.i(MainActivity.TAG, "接続リクエスト失敗");
	                    }
	                });
				}
			}
		}
	}

}