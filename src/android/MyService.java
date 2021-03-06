package com.red_folder.phonegap.plugin.backgroundservice;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyService extends BackgroundService {

	@Override
	protected JSONObject getConfig() {
	   return null;
	}

	@Override
	protected void setConfig(JSONObject config) {

	}     

	@Override
	protected JSONObject initialiseLatestResult() {
	   return null;
	}

	@Override
	protected JSONObject doWork() throws IOException{
	   JSONObject result = new JSONObject();
			
	   try {
		  // Following three lines simply produce a text string with Hello World and the date & time (UK format)
		  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		  String now = df.format(new Date(System.currentTimeMillis())); 
		  String msg = "Hello World - its currently " + now;

		  // We output the message to the logcat
		  Log.d("MyService", msg);
		  
			URL obj = new URL("http://localhost:8080/SSA/sampleReq");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write("mobile=9500343485".getBytes());
			os.flush();
			os.close();
			int responseCode = con.getResponseCode();
			
			
			
		  // We also provide the same message in our JSON Result
		  result.put("Message", msg);
	   } catch (JSONException e) {
		  // In production code, you would have some exception handling here
	   }
			
	   return result;	
	}
}
