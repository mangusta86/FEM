package com.example.fem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class SetProperty extends FragmentActivity {

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
		super.onPause();
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
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);

		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}

	public void saveData(View view) throws IOException {

		Profile rec = new Profile();
		rec.setName(getText(R.id.proName).toString());
		Profile.Rectangle rectangle = rec.new Rectangle();
		rectangle.base = Float.parseFloat(getText(R.id.proBase).toString());
		rectangle.height = Float.parseFloat(getText(R.id.proHeight).toString());

		Material mat = new Material();
		mat.setName(getText(R.id.matName).toString());
		// Profile.Rectangle rectangle=rec.new Rectangle();
		mat.ElasticModulus = Double.valueOf(getText(R.id.matYoung).toString());
		mat.Nu = Double.valueOf(getText(R.id.matPoisson).toString());

	//	String FILENAME1 = "hello_file1";
	//	String FILENAME2 = "hello_file2";
	//	String FILENAME3 = "hello_file3";
		File file1 = new File(Environment.getExternalStorageDirectory().getPath(),"file1.txt");
		File file2 = new File(Environment.getExternalStorageDirectory().getPath(),"file2.txt" );
		File file3 = new File(Environment.getExternalStorageDirectory().getPath(),"file3.txt" );
		
	//	File resolveMeSDCard = new File("/sdcard/download/media/hello_file.txt");
		file1.createNewFile();
		file2.createNewFile();
		file3.createNewFile();
		
	//	FileOutputStream fos = new FileOutputStream(resolveMeSDCard);
		
		FileOutputStream fos1 = new FileOutputStream(file1);
		ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
		oos1.writeObject(rec);
		oos1.close();
		fos1.close();

		FileOutputStream fos2 = new FileOutputStream(file2);
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		oos2.writeObject(rectangle);
		oos2.close();
		fos2.close();
		
		FileOutputStream fos3 = new FileOutputStream(file2);
		ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
		oos3.writeObject(mat);
		oos3.close();
		fos3.close(); 

		// Intent myIntent = new Intent(SetProperty.this, Element.class);
		// SetProperty.this.startActivity(myIntent);
		finish();

	}
}