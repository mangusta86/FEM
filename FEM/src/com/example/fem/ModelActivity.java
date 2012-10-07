package com.example.fem;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fem.DB.BCDBAdapter;
import com.example.fem.DB.DBAdapter;
import com.example.fem.DB.ElementDBAdapter;
import com.example.fem.DB.LoadDBAdapter;
import com.example.fem.DB.MaterialDBAdapter;
import com.example.fem.DB.ProfileDBAdapter;
import com.example.fem.cad.Profile;

public class ModelActivity extends Activity implements
		ModelList.OnHeadlineSelectedListener,
		ModelFragment.OnItemSelectedListener {

	
	//instantiate all the variable used in the activity
	public int menuLeft=-1;
	public String[] elName;
	public String[] matName;
	public String[] proName;
	public String[] loadList;
	public String[] bcList;
	public Cursor C;
	
	
	/**
	 * 
	 */
	DBAdapter DBA = new DBAdapter(this);
	ElementDBAdapter elDBA = new ElementDBAdapter(this);
	MaterialDBAdapter matDBA = new MaterialDBAdapter(this);
	ProfileDBAdapter proDBA = new ProfileDBAdapter(this);
	LoadDBAdapter loadDBA = new LoadDBAdapter(this);
	BCDBAdapter bcDBA = new BCDBAdapter(this);
	
	/**
	 * 
	 */
	FragmentManager fragMAN = getFragmentManager();

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
			
		openAllDatabases();
		
		//inflate the main layout
		//implement three pane layout for landscape?
		
		setContentView(R.layout.activity_main);
		
		//actually don't remember why...
		if (savedInstanceState != null) {
			return;
		}

	}

	/**
	 * Initialise all public arrays used in the activity
	 */
	@Override
	public void onStart() {
		super.onStart();
		C = elDBA.getAllElements();
		elName = getArrayList(C, 1);
		C = matDBA.getAllMaterials();
		matName = getArrayList(C, 1);
		C = proDBA.getAllProfiles();
		proName = getArrayList(C, 1);
		C = loadDBA.getAllLoads();
		loadList = getArrayList(C, 1);
		C = bcDBA.getAllBCs();
		bcList = getArrayList(C, 1);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		closeAllDatabases();
	}

	/**
	 * Function called when selected an element from the first pane
	 * @param position
	 */
	public void onArticleSelected(int position) {

		ModelFragment fragDet = (ModelFragment) fragMAN
				.findFragmentById(R.id.article_fragment);
		
		switch (position) {
		case 0:

			fragDet.updateArticleView(elName);
		
			break;
		case 1:
			 
			fragDet.updateProfileView(profileCursorToList());
	

			break;
		case 2:
			
			fragDet.updateArticleView(matName);
			break;
		case 3:
			
			fragDet.updateArticleView(loadList);
			break;
		case 4:

			fragDet.updateArticleView(bcList);
			break;
		}
		menuLeft=position;

	}

	
	/**
	 * Function called when selected an element from the second pane
	 * @param position
	 */
	public void onItemSelected(int position) {

		int modelItem=menuLeft;
		
		switch (modelItem){
		case 0:		//Element
			break;
		case 1:		//Profile
			break;
		case 2:		//Material
			break;
		case 3:		//Loads
			break;
		case 4:		//BCs
			break;
		}
		
		/*
		 * // Create an instance of ExampleFragment ModelList firstFragment =
		 * new ModelList();
		 * 
		 * // In case this activity was started with special instructions from
		 * // an Intent, // pass the Intent's extras to the fragment as
		 * arguments firstFragment.setArguments(getIntent().getExtras());
		 * 
		 * // Add the fragment to the 'fragment_container' FrameLayout
		 * getFragmentManager().beginTransaction() .add(R.id.fragment_container,
		 * firstFragment).commit();
		 */
		// The user selected the headline of an article from the
		// HeadlinesFragment

		// Capture the article fragment from the activity layout

		// if (articleFrag != null) {
		// If article frag is available, we're in two-pane layout...

		// Call a method in the ArticleFragment to update its content

		// } else {
		// If the frag is not available, we're in the one-pane layout and
		// must swap frags...

		// Create fragment and give it an argument for the selected article
		/*
		 * ModelFragment newFragment = new ModelFragment(); Bundle args = new
		 * Bundle(); args.putInt(ModelFragment.ARG_POSITION, position);
		 * newFragment.setArguments(args); FragmentTransaction transaction =
		 * getFragmentManager() .beginTransaction();
		 * 
		 * // Replace whatever is in the fragment_container view with this //
		 * fragment, // and add the transaction to the back stack so the user
		 * can // navigate back transaction.replace(R.id.fragment_container,
		 * newFragment); transaction.addToBackStack(null);
		 * 
		 * // Commit the transaction transaction.commit();
		 */

	}

	public String[] getArrayList(Cursor C, int columnIndex) {

		int i = 0;
		String[] Array = new String[C.getCount()];

		try {
			if (C != null && C.moveToNext()) {
				while (C.isAfterLast() == false) {
					Array[i] = ("n" + C.getString(columnIndex));
					C.moveToNext();
					i = i + 1;
				}
			}
		}

		finally {
			if (C != null)
				C.close();
		}

		return Array;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/**
	 * Function called to open all the databases in sequence
	 * 
	 */
	public void openAllDatabases(){
		DBA.open2();
		elDBA.open();
		matDBA.open();
		proDBA.open();
		loadDBA.open();
		bcDBA.open();
	}
	
	/**
	 * Function called to close all the databases in sequence
	 * 
	 */
	public void closeAllDatabases(){
		
		elDBA.close();
		matDBA.close();
		proDBA.close();
		loadDBA.close();
		bcDBA.close();
		DBA.close();
	}
	
	/**
	 * Function called to pass data from the cursor to arraylist
	 * @return arraylist
	 */
	public ArrayList<Profile> profileCursorToList(){

		ArrayList<Profile> mArrayList = new ArrayList<Profile>();
		Cursor mCursor=proDBA.getAllProfiles();
		

		int i=0;
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()) {
	    // The Cursor is now set to the right position
			Profile profile =new Profile();
			profile.setName(mCursor.getString(1));
			profile.setShape(mCursor.getString(2));
			profile.setJx(mCursor.getString(4));
			profile.setJy(mCursor.getString(5));
			profile.setA(mCursor.getString(6));
			mArrayList.add(profile);
		}
	mCursor.close();
	return mArrayList;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/*
		 * case android.R.id.home: NavUtils.navigateUpFromSameTask(this); return
		 * true;
		 */
		// case (android.R.id.menu_add): {

		// return true;
		// }

		}
		return true;
	}
}
