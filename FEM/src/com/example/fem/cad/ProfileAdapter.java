package com.example.fem.cad;

import java.util.ArrayList;

import android.content.Context;
<<<<<<< HEAD
import android.text.Html;
=======
>>>>>>> other stuff
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fem.R;

public class ProfileAdapter extends ArrayAdapter<Profile> {

    private ArrayList<Profile> items;
 //   private Context context; 
    
    public ProfileAdapter(Context context, int textViewResourceId, ArrayList<Profile> items) {
            super(context, textViewResourceId, items);
            this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
            View v = convertView;
            
            	
            	  LayoutInflater vi = LayoutInflater.from(getContext());
              //   LayoutInflater vi = ((Activity)context).getLayoutInflater();
                v = vi.inflate(R.layout.adapter_profile, null);
            
            Profile o = items.get(position);
            if (o != null) {
                    TextView tt = (TextView) v.findViewById(R.id.textView1);
                    TextView bt = (TextView) v.findViewById(R.id.textView2);
                    TextView bb = (TextView) v.findViewById(R.id.textView3);
                    TextView tb = (TextView) v.findViewById(R.id.textView4);
                    ImageView iv= (ImageView) v.findViewById(R.id.imageView1);
                    if (tt != null) {
                          tt.setText(o.getName());                            
                          }
                    if(bt != null){
<<<<<<< HEAD
                          bt.setText(o.getA());
                    }
                    if(bb != null){
                        bb.setText(o.getJx());
                    }	
                    if(tb != null){
                        tb.setText(o.getJy());
=======
                          bt.setText(o.getStringA());
                    }
                    if(bb != null){
                        bb.setText(o.getStringJx());
                    }	
                    if(tb != null){
                        tb.setText(o.getStringJy());
>>>>>>> other stuff
                    }
                
                    	
                    	if (o.isProRectangle()){
                    		iv.setImageResource(R.drawable.recshape);}
                    	
                    	if (o.isProCircle()){
                    		iv.setImageResource(R.drawable.circleshape);}
                    	
                    	if (o.isProCShape()){
                    		iv.setImageResource(R.drawable.cshape);
                    	}
                    
            }
            return v;
    }
}