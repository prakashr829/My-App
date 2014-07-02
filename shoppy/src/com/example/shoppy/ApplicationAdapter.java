package com.example.shoppy;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ApplicationAdapter extends ArrayAdapter<Application>{
    
	private List<Application> items;
    int count,send;

	 private Context mContext;
    
    public ApplicationAdapter(Context context, List<Application> items) {
        super(context, R.layout.app_custom_list, items);
        this.mContext        = context;
        this.items = items;
    }
    
    
	@Override
    public int getCount() {
        return items.size();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageView imgIcon;

        TextView name,rate,desc;

        if(v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.app_custom_list, null);            
        }
        
        Application app = items.get(position);
        
        if(app != null) {
            imgIcon = (ImageView)v.findViewById(R.id.imageVie);

            name = (TextView)v.findViewById(R.id.titleTxt);
           // LinearLayout desc = (LinearLayout)v.findViewById(R.id.ratingCntr);
             rate = (TextView)v.findViewById(R.id.dlTxt);
             desc = (TextView)v.findViewById(R.id.textdesc);

            try{
            	
            
            imgIcon.setOnClickListener(new View.OnClickListener() {
			

				@Override
				public void onClick(View v) {
		                  
					 ((Products)mContext).Onclick();
					}
			
				
            
			});
            }
            catch(Exception e){
            	e.printStackTrace();
            	
            	
            }
          
            /*if(icon != null) {
                Resources res = getContext().getResources();
                String sIcon = "com.sj.jsondemo:drawable/" + app.getIcon();
                icon.setImageDrawable(res.getDrawable(res.getIdentifier(sIcon, null, null)));
            }*/
            
            if(name != null) name.setText(app.getProduct_name());
            
            if(rate != null) {
                NumberFormat nf = NumberFormat.getNumberInstance();
                rate.setText(nf.format(app.getProduct_price())+" RS");            
            }
            
            if(desc != null) desc.setText(app.getProduct_description());
            
        }
		return v;
		
    }
    


}
    
	

            
            /*if(desc != null && desc.getChildCount() == 0) //{        
                
                 * max rating: 5
                 
                for(int i=1; i<=5; i++) {
                    ImageView iv = new ImageView(getContext());
                    
                    if(i <= app.getProduct_description()) {
                        iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_checked));
                    }
                    else {                
                        iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_unchecked));
                    }
                    
                    ratingCntr.addView(iv);
                }
            }
        }*/
        
       /* return v;
    }
}*/
        
