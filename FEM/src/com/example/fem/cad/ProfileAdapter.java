package com.example.fem.cad;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fem.R;

public class ProfileAdapter extends ArrayAdapter<Profile> {

    private ArrayList<Profile> items;
    private Context context; 
    
    public ProfileAdapter(Context context, int textViewResourceId, ArrayList<Profile> items) {
            super(context, textViewResourceId, items);
            this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = ((Activity)context).getLayoutInflater();
                v = vi.inflate(R.layout.adapter_profile, null);
            }
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
                          bt.setText(o.getA()+" m2  ");
                    }
                    if(bb != null){
                        bb.setText(o.getJx()+" m4  ");
                    }	
                    if(tb != null){
                        tb.setText(o.getJy()+" m4  ");
                    }
                    if(iv != null){
                    	String shape=o.getShape();
                    	if (shape=="rectangle"){
              
                    		iv.setImageResource(R.drawable.recshape);}
                    
                    	if (shape=="circle"){
                    		iv.setImageResource(R.drawable.circleshape);}
              
                    	if (shape=="cshape"){
                    		iv.setImageResource(R.drawable.cshape);
                    	}
                    }
            }
            return v;
    }
}