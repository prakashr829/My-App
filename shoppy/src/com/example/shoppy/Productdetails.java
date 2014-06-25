package com.example.shoppy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Productdetails extends Activity {
	Button back;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.productdetails);
	        addListenerOnButton();
	 }
	        private void addListenerOnButton() {
				// TODO Auto-generated method stub
		    	back = (Button) findViewById(R.id.button1);
		    	 
				back.setOnClickListener(new OnClickListener() {
		 
					@Override
					public void onClick(View arg0) {
		 
						Intent i = new Intent(getApplicationContext(), Products.class);
						startActivity(i);
		 
					}
		 
				});

}
}