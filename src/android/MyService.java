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
	protected JSONObject doWork()  throws IOException {
	   JSONObject result = new JSONObject();
	   try {
	   
		  // Following three lines simply produce a text string with Hello World and the date & time (UK format)
		  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		  String now = df.format(new Date(System.currentTimeMillis())); 
		  String msg = "Hello World - its currently " + now;

		  // We output the message to the logcat
		  Log.d("MyService", msg);

		  
		  
		final String POST_URL = "http://192.168.1.11:8080/SSA/sampleReq";
		final String POST_PARAMS = "mobile=9500343485";
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();


		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(	con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
		  
		  
		  
		  
		  
			  // We also provide the same message in our JSON Result
		  result.put("Message", msg);
	   } catch (JSONException e) {
		  // In production code, you would have some exception handling here
	   }
			
	   return result;	
	}

}
