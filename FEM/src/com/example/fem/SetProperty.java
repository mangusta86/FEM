package com.example.fem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class SetProperty extends FragmentActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_property);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter();
        ViewPager myPager = (ViewPager) findViewById(R.id.pager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);
    }
    @Override
    public void onPause() {	
    }
    
    public class SectionsPagerAdapter extends PagerAdapter {

        
        public Object instantiateItem(View collection, int position) {
            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int resId = 0;
            switch (position) {
            case 0:
                resId = R.layout.profile;
                break;
            case 1:
                resId = R.layout.material;
                break;
            case 2:
                resId = R.layout.section;
                break;
            }
            View view = inflater.inflate(resId, null);
            ((ViewPager) collection).addView(view, 0);
            return view;
        }


        @Override
        public int getCount() {
            return 3;
        }


		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == ((View) arg1);
		}
		
	    @Override
	    public void destroyItem(View arg0, int arg1, Object arg2) {
	            ((ViewPager) arg0).removeView((View) arg2);
	    }      
    }

    
    public void saveData(View view) {
            	
            	Profile rec=new Profile();
            	rec.setName(getText(R.id.proName).toString());
            	Profile.Rectangle rectangle=rec.new Rectangle();
            	rectangle.base= Float.valueOf(getText(R.id.proBase).toString());
            	rectangle.height= Float.valueOf(getText(R.id.proHeight).toString());
            	
            	Material mat=new Material();
            	mat.setName(getText(R.id.matName).toString());
            	//Profile.Rectangle rectangle=rec.new Rectangle();
            	mat.ElasticModulus= Double.valueOf(getText(R.id.matYoung).toString());
            	mat.Nu= Double.valueOf(getText(R.id.matPoisson).toString());
            	
            	String FILENAME = "hello_file";
            	FileOutputStream fos = null;
				try {
					fos = openFileOutput(FILENAME, Context.MODE_WORLD_READABLE);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            	ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(fos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            	try {
					oos.writeObject(rec);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	try {
					oos.writeObject(rectangle);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	try {
					oos.writeObject(mat);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            	try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	Intent Play = new Intent(SetProperty.this, Element.class);
            	SetProperty.this.startActivity(Play);
            	finish();


    }
}