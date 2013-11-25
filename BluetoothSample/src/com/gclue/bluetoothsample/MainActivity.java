package com.gclue.bluetoothsample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "BLUETOOTH_SAMPLE";
	private BluetoothAdapter mAdapter;
	private ArrayList<BluetoothDevice> foundDeviceList = new ArrayList<BluetoothDevice>();
	private ArrayAdapter<String> DeviceAdapter;
	public static final String ACTION_DISCOVERY_STARTED = BluetoothAdapter.ACTION_DISCOVERY_STARTED;
	public static final String ACTION_FOUND = BluetoothDevice.ACTION_FOUND;
	public static final String ACTION_NAME_CHANGED = BluetoothDevice.ACTION_NAME_CHANGED;
	public static final String ACTION_DISCOVERY_FINISHED = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
	private ArrayAdapter<String> pairedDeviceAdapter;
	private ArrayAdapter<String> nonPairedDeviceAdapter;
	private Button mButton1;
	private Button mButton2;
	private Button mButton3;
	private Button mButton4;
	public static final int FIRST= 0;
	private UUID MY_UUID = UUID.fromString("1111111-0000-1000-1111-00AAEECCAAFF");
	private ServerThread serverThread;
	private ClientThread clientThread;
	private static final String NAME = "BLUETOOTH_ANDROID";
	private ConnectedThread connection;
	private EditText editText;
	private TextView textView;
	private static final int REQUEST_ENABLE_BT = 123456789;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mButton2 = (Button) findViewById(R.id.button2);
		mButton2.setOnClickListener(this);
		mButton4 = (Button) findViewById(R.id.button4);
		mButton4.setOnClickListener(this);
		editText = (EditText) findViewById(R.id.EditText01);
		textView = (TextView) findViewById(R.id.textView);
		
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mAdapter == null) {
		} else {
			if (!mAdapter.isEnabled()) {

				Intent intent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(intent, REQUEST_ENABLE_BT);
			}
		}
		pairedDeviceAdapter = new ArrayAdapter<String>(this, R.layout.row);
		
		Set<BluetoothDevice> pairedDevices = mAdapter.getBondedDevices();
		if(pairedDevices.size() > 0){
			
			for(BluetoothDevice device:pairedDevices){
				
				pairedDeviceAdapter.add(device.getName() + "\n" + device.getAddress());
				foundDeviceList.add(device);
			}
			ListView deviceList = (ListView)findViewById(R.id.listView);
			deviceList.setAdapter(pairedDeviceAdapter);
		}
	}
	public void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		if (requestCode == REQUEST_ENABLE_BT) {
			if (resultCode == Activity.RESULT_OK) {
				Log("Bluetooth is now enabled");
			} else {
				Log("Bluetooth not enabled");
			}
		}
	}



	@Override
	protected void onDestroy() {
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		super.onDestroy();
		mAdapter.cancelDiscovery();
		unregisterReceiver(DevieFoundReceiver);
	}	

	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		menu.add(0, Menu.FIRST, Menu.NONE, "search new device.");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) { 
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		if(item.getItemId() == Menu.FIRST){
			
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
			IntentFilter filter = new IntentFilter();
			filter.addAction(ACTION_DISCOVERY_STARTED);
			filter.addAction(ACTION_FOUND);
			filter.addAction(ACTION_NAME_CHANGED);
			filter.addAction(ACTION_DISCOVERY_FINISHED);
			registerReceiver(DevieFoundReceiver, filter);
		
			nonPairedDeviceAdapter = new ArrayAdapter<String>(this, R.layout.row);
			if(mAdapter.isDiscovering()){
			
				mAdapter.cancelDiscovery();
			}
			
			mAdapter.startDiscovery();
		}
		return false;
	}

	private final BroadcastReceiver DevieFoundReceiver = new BroadcastReceiver(){
		
		@Override
		public void onReceive(Context context, Intent intent){
			String action = intent.getAction();
			String dName = null;
			BluetoothDevice foundDevice;
			ListView nonpairedList = (ListView)findViewById(R.id.listView);
			if(ACTION_DISCOVERY_STARTED.equals(action)){
				Log("start scan.");
			}
/*
			if(ACTION_FOUND.equals(action)){
			
				Log("ACTION_FOUND");
				foundDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if((dName = foundDevice.getName()) != null){
					if(foundDevice.getBondState() != BluetoothDevice.BOND_BONDED){
						
						nonPairedDeviceAdapter.add(dName + "\n" + foundDevice.getAddress()); 
						//Log("Daddress:" + foundDevice.getAddress());

					}
					nonpairedList.setAdapter(nonPairedDeviceAdapter);
				}
			}    */
			if(ACTION_NAME_CHANGED.equals(action)){
				
				Log("ACTION_NAME_CHANGED");
				foundDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if((dName = foundDevice.getName()) != null){
					if(foundDevice.getBondState() != BluetoothDevice.BOND_BONDED){
						
						nonPairedDeviceAdapter.add(dName + "\n" + foundDevice.getAddress()); 
						//Log("Daddress:" + foundDevice.getAddress());
						foundDeviceList.add(foundDevice);

					}
					nonpairedList.setAdapter(nonPairedDeviceAdapter);
				}


				nonpairedList.setAdapter(nonPairedDeviceAdapter);
				nonpairedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					
					@Override
					public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
						// TODO Auto-generated method stub
						ListView listView = (ListView) parent;
						BluetoothDevice device =foundDeviceList.get(position);
						clientThread = new ClientThread(device);
						clientThread.start();
					}
				});


				Log("name:"+dName + "address:" + foundDevice.getAddress());
			}

			if(ACTION_DISCOVERY_FINISHED.equals(action)){
				Log("スキャン終了");
			}
		}
	};

	public void Log(String string){
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();    	
	}



	@Override
	public void onClick(View view) {
		// Get pairringed device list.
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		// Lunch Server.
		if (view.equals(mButton2)) {
			serverThread = new ServerThread();
			serverThread.start();
		}
		// Send message.
		else if (view.equals(mButton4)) {
			String message = editText.getText().toString();
			if (message != null && !message.equals("")) {
				connection.write(message.getBytes());
			}
		}
	}
	/**
	 * search BT
	 */
	private void doDiscovery() {
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		if (!mAdapter.isDiscovering()) {
			mAdapter.startDiscovery();

			
			Toast.makeText(getApplicationContext(),"Searching",
					Toast.LENGTH_LONG).show();
		}
	}
	/**
	 * Server's Thread
	 */
	private class ServerThread extends Thread {
		private final BluetoothServerSocket mmServerSocket;
		public ServerThread() {
			BluetoothServerSocket tmp = null;
			try {
				
				mAdapter = BluetoothAdapter.getDefaultAdapter();
				tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
			} catch (IOException e) {
			}
			mmServerSocket = tmp;
		}

		/**
		 * Run method.
		 */
		public void run() {
			BluetoothSocket socket = null;

			/**
			 * In While　always Client Polling
			 */
			while (true) {
				Log("Polling");
				try {
					socket = mmServerSocket.accept();

				} catch (Exception e) {
					break;
				}

				// If Client connect to Server,Socket become not null.
				if (socket != null) {
					// 接続されると呼び出される
					manageConnectedSocket(socket);
					try {
						mmServerSocket.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}

		
		public void cancel() {
			try {
				mmServerSocket.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Client's Thread
	 */
	private class ClientThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		public ClientThread(BluetoothDevice device) {

			BluetoothSocket tmp = null;
			mmDevice = device;

			try {
			
				tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
			} catch (Exception e) {
				Log.i(TAG, "Error:" + e);
			}
			mmSocket = tmp;
		}

		public void run() {

			// End Discovery.
			mAdapter = BluetoothAdapter.getDefaultAdapter();
			mAdapter.cancelDiscovery();

			try {
				// Connect to Server.
				mmSocket.connect();
			} catch (IOException connectException) {

				try {
					mmSocket.close();
				} catch (IOException closeException) {
				}
				return;
			}

			
			manageConnectedSocket(mmSocket);
		}

		
		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
			}
		}
	}

	
	public void manageConnectedSocket(BluetoothSocket socket) {
		Log("Connection");
		connection = new ConnectedThread(socket);
		connection.start();
	}

	
	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

		public ConnectedThread(BluetoothSocket socket) {
			Log("ConnectedThread");
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;

			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
			}

			
			mmInStream = tmpIn;
			
			mmOutStream = tmpOut;
		}

		public void run() {
			Log("ConnectionThread#run()");
			byte[] buffer = new byte[1024];
			int bytes;

			
			while (true) {
				try {
					
					bytes = mmInStream.read(buffer);
					
					String readMsg = new String(buffer, 0, bytes, "UTF-8");
					
					Log("GET: " + readMsg);

					
					Message msg = new Message();
					msg.obj = readMsg;
					mHandler.sendMessage(msg);

				} catch (IOException e) {
					break;
				}
			}
		}

		
		public void write(byte[] bytes) {
			try {
				mmOutStream.write(bytes);
			} catch (IOException e) {
			}
		}

		
		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
			}
		}
	}

	final Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			String readMsg = (String) msg.obj;
			textView.setText(readMsg);
			textView.invalidate();
		}
	};

}