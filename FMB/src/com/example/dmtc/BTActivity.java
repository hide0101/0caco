package com.example.dmtc;


import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class BTActivity extends Activity {
private static final int REQUEST_ENABLE_BT = 1;
private static final int REQUEST_STATE_CHANGE_BT = 2;
private TextView tv_result = null;
private BluetoothAdapter mBluetoothAdapter = null;
private BluetoothReceiver mBluetoothReceiver = null;

/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_bt);

tv_result = (TextView) findViewById(R.id.textView1);
tv_result.setText("");

checkBluetooth();
}

private void checkBluetooth() {
tv_result.append("Step 1:Bluetoothの利用可能状態\n");
mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
if (mBluetoothAdapter == null) {
tv_result.append("Bluetoothはサポートされてません\n");
} else {
tv_result.append("Bluetoothはサポートされています\n");
if (mBluetoothAdapter.isEnabled()) {
 tv_result.append("Bluetoothは利用可能です\n");
 getLocalInformation();
} else {
 Intent enableBTIntent = new Intent(
   BluetoothAdapter.ACTION_REQUEST_ENABLE);
 startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
 Intent stateChangedBTIntent = new Intent(
   BluetoothAdapter.ACTION_STATE_CHANGED);
 startActivityForResult(stateChangedBTIntent,
   REQUEST_STATE_CHANGE_BT);
}
}
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
if (requestCode == REQUEST_ENABLE_BT) {
if (resultCode == RESULT_OK) {
 tv_result.append("Bluetoothが利用可能になりました\n");
 getLocalInformation();
} else if (resultCode == RESULT_CANCELED) {
 tv_result.append("Bluetoothは利用不可です\n");
}
} else if (requestCode == REQUEST_STATE_CHANGE_BT) {
switch (resultCode) {
case BluetoothAdapter.STATE_TURNING_ON:
 tv_result.append("STATE_TURNING_ON\n");
 break;
case BluetoothAdapter.STATE_ON:
 tv_result.append("STATE_ON\n");
 break;
case BluetoothAdapter.STATE_TURNING_OFF:
 tv_result.append("STATE_TURNING_OFF\n");
 break;
case BluetoothAdapter.STATE_OFF:
 tv_result.append("STATE_OFF\n");
 break;
}
}
}

private void getLocalInformation() {
tv_result.append("Bluetooth");

tv_result.append(mBluetoothAdapter.getName() + ":"
 + mBluetoothAdapter.getAddress() + "\n");
switch (mBluetoothAdapter.getScanMode()) {
case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
tv_result.append("SCAN_MODE_CONNECTABLE:");
break;
case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
tv_result.append("SCAN_MODE_CONNECTABLE_DISCOVERABLE:");
break;
case BluetoothAdapter.SCAN_MODE_NONE:
tv_result.append("SCAN_MODE_NONE:");
break;
}
switch (mBluetoothAdapter.getState()) {
case BluetoothAdapter.STATE_OFF:
tv_result.append("STATE_OFF\n");
break;
case BluetoothAdapter.STATE_ON:
tv_result.append("STATE_ON\n");
break;
case BluetoothAdapter.STATE_TURNING_OFF:
tv_result.append("STATE_TURNING_OFF\n");
break;
case BluetoothAdapter.STATE_TURNING_ON:
tv_result.append("STATE_TURNING_ON\n");
break;
}
findPairedDevices();
}

private void findPairedDevices() {
tv_result.append("\nStep 3:登録済みのBluetoothの調査\n");

Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
 .getBondedDevices();
if (pairedDevices.size() > 0) {
for (BluetoothDevice device : pairedDevices) {
 tv_result.append(device.getName() + ":" + device.getAddress()
   + ":" + device.getBluetoothClass() + "\n");
}
} else {
tv_result.append("登録されているBluetoothデバイスはありません\n");
}
discoverDevices();
}

private void discoverDevices() {
tv_result.append("\nStep 4:Bluetoothデバイスの探索\n");

mBluetoothReceiver = new BluetoothReceiver();
registerReceiver(mBluetoothReceiver, new IntentFilter(
 BluetoothDevice.ACTION_FOUND));
mBluetoothAdapter.startDiscovery();
tv_result.append("探索開始\n");
}

class BluetoothReceiver extends BroadcastReceiver {
@Override
public void onReceive(Context context, Intent intent) {
tv_result.append("Bluetoothデバイスを発見\n");
String action = intent.getAction();
if (BluetoothDevice.ACTION_FOUND.equals(action)) {
 BluetoothDevice device = intent
   .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
 tv_result.append(device.getName() + ":" + device.getAddress()
   + ":" + device.getBluetoothClass() + "\n");
}
}
}
}