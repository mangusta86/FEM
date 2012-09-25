package com.example.fem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SetProperty extends FragmentActivity  implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

	ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_property);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different tab.
        // We can also use ActionBar.Tab#select() to sections, select the corresponding tdo this if we have a reference to the
        // Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
   
  	 try {
		onClickSend();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    @Override
    public void onPause(){
		
    }
    

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            
            switch (position) {
            case 0: 
            	Fragment fragment1 = new Fragment1();
            	return fragment1;
            case 1: 
            	Fragment fragment2 = new Fragment2();
            	return fragment2;
            case 2: 
            	Fragment fragment3 = new Fragment3();
            	return fragment3;
        }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.title_section1).toUpperCase();
                case 1: return getString(R.string.title_section2).toUpperCase();
                case 2: return getString(R.string.title_section3).toUpperCase();
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class Fragment1 extends Fragment {
        public Fragment1() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.profile, container, false);
        }
        @Override
        public void onPause(){
        	

        }
        
        		
    }
    public static class Fragment2 extends Fragment {
        public Fragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.material, container, false);
        }
    }
    public static class Fragment3 extends Fragment {
        public Fragment3() {
        	}
        

        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.section, container, false);
        }
    }
    
    public void onClickSend() throws FileNotFoundException{
    	Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
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
            };
        });

    }
}