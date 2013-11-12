package com.example.wifidirect;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.os.AsyncTask;

public class ClientAsyncTask extends AsyncTask<Void, Void, Void> {

    private String host;
	private String message;

	public ClientAsyncTask(String host, String message) {
		this.host = host;
		this.message = message;
	}

	@Override
    protected Void doInBackground(Void... params) {
		/**
		 * グループオーナーIPに接続して入力メッセージを送信
		 */
		Socket socket = new Socket();
        try {
            socket.bind(null);
            socket.connect((new InetSocketAddress(this.host, MainActivity.port)), 0);
            OutputStream stream = socket.getOutputStream();
            byte[] byteArray = this.message.getBytes("UTF-8");
            stream.write(byteArray, 0, byteArray.length);
            stream.close();
            
        } catch (IOException e) {
        } finally {
            if (socket != null) {
                if (socket.isConnected()) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
		return null;
    }

}