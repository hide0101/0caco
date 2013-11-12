package com.example.wifidirect;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class ServerThread extends Thread{
	public void run() {

		/*get a data and preserve in sqlite.
		 */
		try {
			ServerSocket serverSocket = new ServerSocket(MainActivity.port);
            while(true){
				Socket socket = serverSocket.accept();
	            InputStream inputstream = socket.getInputStream();
	            char[] buf = new char[1024];
	            Reader reader = new InputStreamReader(inputstream, "UTF-8");
	            StringBuilder string = new StringBuilder();
	            int len;
	            while ( (len = reader.read(buf)) != -1) {
	              string.append(buf, 0, len);
	            }
	            inputstream.close();
	            Log.i(MainActivity.TAG, string.toString());
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}