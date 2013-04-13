package com.example.fem;

import com.example.fem.DB.ElementDBAdapter;
import com.example.fem.DB.MaterialDBAdapter;
import com.example.fem.DB.ProfileDBAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class AddProfile extends Activity {

	int mNum;
	public String ACTION;
	
	ElementDBAdapter elDBA = new ElementDBAdapter(this);
	MaterialDBAdapter matDBA = new MaterialDBAdapter(this);
	ProfileDBAdapter proDBA = new ProfileDBAdapter(this);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		
		ACTION = intent.getStringExtra("ACTION");
		mNum = intent.getIntExtra("nItem", -1);
		
		proDBA.open();
		
		if (ACTION.equals("create")){
			
		}
		
		setContentView(R.layout.add_profile_rect);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_add_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.menu_save:

		}
		return super.onOptionsItemSelected(item);
	}

}
