package com.example.shoppy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable.Factory;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;


public class Products extends ListActivity implements FetchDataListener {
	Button back;
	private ProgressDialog dialog;

	ListView list;
	BadgeView badge;
	View target;
	ArrayAdapter<String> adapter;
	// Declare the UI components

	ArrayAdapter arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product);
		addListenerOnButton();
		target = findViewById(R.id.text2);

		badge = new BadgeView(this, target);
		badge.setText("0");
		badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
		badge.hide();
		initView();
		ApplicationAdapter adp = new ApplicationAdapter(getBaseContext(),
				(List<Application>) arrayAdapter);
		// int d=adp.send();
		// System.out.print(d);

		// badge.clearComposingText();
		ImageView view = (ImageView) findViewById(R.id.imageView1);
		list = (ListView) findViewById(android.R.id.list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Object o = list.getItemAtPosition(position);
				
				Application obj_details = (Application) o;
				for(int i=0;i<position;i++){
				String name = obj_details.getProduct_name();
				String desc = obj_details.getProduct_description();
				double price = obj_details.getProduct_price();
               Log.d("name",name);
               Log.d("price",desc);
				
               
				Intent intent = new Intent(Products.this,
						Productdetails.class);
				intent.putExtra("name", name);
				intent.putExtra("desc", desc);
				intent.putExtra("price", price);
				startActivity(intent);
				finish();
				}
			}

		});

		/*
		 * arrayAdapter = new ArrayAdapter(this,
		 * android.R.layout.simple_list_item_1, monthsArray);
		 * monthsListView.setAdapter(arrayAdapter);
		 */
	}

	public void Onclick() {
		String qunt;
		final EditText input1;
		LayoutInflater factory = LayoutInflater.from(this);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		final View textEntryView = factory.inflate(R.layout.userpasslayout,
				null);
		alert.setTitle("Easy Grocery Shopping");
		alert.setMessage("How Much Quantity You Want");
		// Set an EditText view to get user input
		alert.setView(textEntryView);

		// input1 = (EditText) loginPrompt.findViewById(R.id.editTex);
		try {
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// String input =input1.getText().toString();

							EditText pwd_input = (EditText) textEntryView
									.findViewById(R.id.editTex);
							int qun;
							try {
								String m_email = pwd_input.getText().toString();

								Toast.makeText(getBaseContext(), m_email,
										Toast.LENGTH_SHORT).show();
								int i;
								String c = badge.getText().toString();
								// badge.clearComposingText();
								String d = c;
								int co = Integer.parseInt(d);
								qun = Integer.parseInt(m_email);

								Log.d("result", d);
								if (co == 0) {
									badge.setText("" + qun);
									// badge.setText(""+result);
									badge.show();
									AddList();
								} else {
									String qunty = badge.getText().toString();
									i = Integer.parseInt(qunty);

									int result = i + qun;
									badge.setText("" + result);
									badge.show();
									// badge.toggle();

								}
							} catch (NullPointerException e) {
								System.out.println(">>> getText(): " + e);
								e.printStackTrace();
							}

						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Canceled.
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

		alert.show();

	}

	private void initView() {
		// show progress dialog
		dialog = ProgressDialog.show(this, "", "Loading...");

		String url = "http://67.23.226.129/~saarwayc/shop/mobile/app.php";
		FetchDataTask task = new FetchDataTask(this);
		task.execute(url);
	}

	@Override
	public void onFetchComplete(List<Application> data) {
		// dismiss the progress dialog
		if (dialog != null)
			dialog.dismiss();
		// create new adapter
		Log.d("result", data.toString());
		ApplicationAdapter adapter = new ApplicationAdapter(this, data);
		// set the adapter to list
		list.setAdapter(adapter);
		// Log.d("hhh", list.getItemAtPosition(0).toString());

	}

	@Override
	public void onFetchFailure(String msg) {
		// dismiss the progress dialog
		if (dialog != null)
			dialog.dismiss();
		// show failure message
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	

	 private void addListenerOnButton() {
			// TODO Auto-generated method stub
	    	back = (Button) findViewById(R.id.button1);
	    	 
			back.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View arg0) {
	 
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(i);
	 
				}
	 
			});
			//initView();
	 
	 }

	
	

	
		

	

	protected void AddList() {
		//int position = 0;
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Object o = list.getItemAtPosition(position);
				
				Application obj_details = (Application) o;
				for(int i=0;i<position;i++){
				String name = obj_details.getProduct_name();
				String desc = obj_details.getProduct_description();
				double price = obj_details.getProduct_price();
               Log.d("name",name);
               Log.d("price",desc);
				}
               
			}
	});

}
}
