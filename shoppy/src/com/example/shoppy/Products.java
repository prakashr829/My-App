package com.example.shoppy;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppy.ApplicationAdapter.EditPlayerAdapterCallback;
import com.readystatesoftware.viewbadger.BadgeView;

public class Products extends ListActivity  implements FetchDataListener,EditPlayerAdapterCallback {
	   Button back;
	   private ProgressDialog dialog;
	   
	   ListView list;
	    BadgeView badge ;
	    View target ;
				    // Declare the UI components
				   
				     ArrayAdapter arrayAdapter;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        addListenerOnButton();
	      target = findViewById(R.id.text2);
	       
	       badge = new BadgeView(this, target);
	       badge.setText("1");
	       badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
	        badge.hide();
	        initView(); 
	        ApplicationAdapter adp=new ApplicationAdapter(getBaseContext(),(List<Application>) arrayAdapter);
	       // int d=adp.send();
	     //  System.out.print(d);
	       
	       // badge.clearComposingText();
	        ImageView view =(ImageView) findViewById(R.id.imageView1);
	      list = (ListView) findViewById(android.R.id.list);
	        /*arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthsArray);
	        monthsListView.setAdapter(arrayAdapter);*/
	       view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i;
   				String c=badge.getText().toString();
   			// badge.clearComposingText();
 	        String d=c;
   				Log.d("result",d);
   				int j=Integer.parseInt(c);
   				int result=1+j;
   				badge.setText(""+result);
   				badge.show();
				
			}
		});
	    }  
	 
	 public void Onclick(){
		Toast.makeText(getApplicationContext(), "helo", Toast.LENGTH_LONG).show();
		 
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
	        if(dialog != null)  dialog.dismiss();
	        // create new adapter
	        Log.d("result",data.toString());
	        ApplicationAdapter adapter = new ApplicationAdapter(this, data);
	        // set the adapter to list
	        setListAdapter(adapter); 
	        
	      
	    }

	   
		@Override
	    public void onFetchFailure(String msg) {
	        // dismiss the progress dialog
	        if(dialog != null)  dialog.dismiss();
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

	
	public void checkForLogo() {
		Toast.makeText(getApplicationContext(), "helo", Toast.LENGTH_LONG).show();
		 
		
		// TODO Auto-generated method stub
		
	}
}
