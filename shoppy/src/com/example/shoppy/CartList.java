package com.example.shoppy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CartList extends Activity
{
	ListView View;
	   private ArrayAdapter arrayAdapter;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.cartlist);
	        
	        View= (ListView) findViewById(R.id.list_item);
	       // Intent mylist=new Intent();
	        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("array");
	        
	        Log.d("tag", myList.get(0));
	        
	        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,myList);
	        View.setAdapter(arrayAdapter);
	        
	        
	 }

}
