package com.example.shoppy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity1 extends Fragment{
	TextView user,pass;
	Button btn;
	String username,password;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View root = inflater.inflate(R.layout.signup, container, false);
         user=(TextView) root.findViewById(R.id.user);
         pass=(TextView) root.findViewById(R.id.pass);
         btn=(Button) root.findViewById(R.id.login);
         btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				username=user.getText().toString();
				password=pass.getText().toString();
				new connect().execute();
				// TODO Auto-generated method stub
				
			}
		});
        return root;
    
       
	}
	
	class connect extends AsyncTask<String,String,String>{

		@Override
		
		protected String doInBackground(String... uri) {
			InputStream is=null;
			String result=null;
			// TODO Auto-generated method stub
			ArrayList<NameValuePair> value = new ArrayList<NameValuePair>();
		
			value.add(new BasicNameValuePair("username", username));
			value.add(new BasicNameValuePair("password", password));
			
			
			//value.add(new BasicNameValuePair("date",date));
			
			//http post
            try{
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(uri[0]);
                    httppost.setEntity(new UrlEncodedFormEntity(value));
                    Log.d("value",value.toString());
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    
                     result = InputStreamToString(is);
                    Log.d("log_tag", result);
 
               }
           
           
            catch(Exception e)
            {
                    Log.d("log_tag", "Error in http connection "+e.toString());

            }
			
			return result;
		}
	

		@Override
		protected void onPostExecute(String result){
			if(result.equals("")){
				
			}else{
				
			
			Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
			}	
		}
		
	}
	private static String InputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		
		return result;


}


}
