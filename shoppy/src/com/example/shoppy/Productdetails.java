package com.example.shoppy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Productdetails extends Activity {
	Button back;
	  TextView name,desc,price;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.productdetails);
	        addListenerOnButton();
	        
	        Intent in=getIntent();
	        double prc= getIntent().getDoubleExtra("price", 0);
	       
	     name=(TextView) findViewById(R.id.textView3);
	     desc=(TextView) findViewById(R.id.textView4);
	      price=(TextView) findViewById(R.id.textView6);
	       name.setText(in.getStringExtra("name").toString());
	       desc.setText(in.getStringExtra("desc").toString());
	        price.setText(String.valueOf(prc));
	        
	 }
	        private void addListenerOnButton() {
				// TODO Auto-generated method stub
		    	back = (Button) findViewById(R.id.button2);
		    	 
				back.setOnClickListener(new OnClickListener() {
		 
					@Override
					public void onClick(View arg0) {
		 
						Intent i = new Intent(getApplicationContext(),BuyDetails.class);
						startActivity(i);
		 
					}
		 
				});

}
}