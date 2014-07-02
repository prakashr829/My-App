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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TabActivity2  extends Fragment{
	EditText ed1;
	EditText ed2;
	EditText ed3;
	EditText ed4;
	EditText ed5;
	EditText ed6;
	Button btn;
	String id,fname,lname,phone,email,address;
	Activity activity=getActivity();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View root = inflater.inflate(R.layout.register , container, false);
         
        
        ed1=(EditText)root.findViewById(R.id.editText1);
		ed2=(EditText)root.findViewById(R.id.editText2);
		ed3=(EditText)root.findViewById(R.id.editText3);
		ed4=(EditText)root.findViewById(R.id.editText4);
		ed5=(EditText)root.findViewById(R.id.editText5);
		ed6=(EditText)root.findViewById(R.id.editText6);
		btn=(Button)root.findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//code to get data input from xml
				
				 id = ed1.getText().toString();
				 fname = ed2.getText().toString();
				 lname = ed3.getText().toString();
				 phone = ed4.getText().toString();
				 email = ed5.getText().toString();
				 address = ed6.getText().toString();
				Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
				Toast.makeText(getActivity(), fname, Toast.LENGTH_SHORT).show();
				Toast.makeText(getActivity(), lname, Toast.LENGTH_SHORT).show();
				Toast.makeText(getActivity(), phone, Toast.LENGTH_SHORT).show();
				Toast.makeText(getActivity(), email, Toast.LENGTH_SHORT).show();
				Toast.makeText(getActivity(), address, Toast.LENGTH_SHORT).show();
				new connect().execute("http://67.23.226.129/~saarwayc/shop/mobile/insertdata.php");
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
		
			value.add(new BasicNameValuePair("username", id));
			value.add(new BasicNameValuePair("password", fname));
			value.add(new BasicNameValuePair("lname", lname));
			value.add(new BasicNameValuePair("phone", phone));
			value.add(new BasicNameValuePair("email", email));
			value.add(new BasicNameValuePair("address", address));
			
			//value.add(new BasicNameValuePair("date",date));
			Log.d("id",id);
			Log.d("fname",fname);
			Log.d("lname",lname);
			Log.d("phone",phone);
			Log.d("email",email);
			Log.d("address",address);
			
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

