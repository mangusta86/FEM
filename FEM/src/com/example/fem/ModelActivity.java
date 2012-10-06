package com.example.fem;

import com.example.fem.DB.DBAdapter;
import com.example.fem.DB.ElementDBAdapter;
import com.example.fem.DB.MaterialDBAdapter;
import com.example.fem.DB.ProfileDBAdapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ModelActivity extends Activity implements
		ModelList.OnHeadlineSelectedListener,
		ModelFragment.OnItemSelectedListener {

	DBAdapter DBA = new DBAdapter(this);
	ElementDBAdapter elDBA = new ElementDBAdapter(this);
	MaterialDBAdapter matDBA = new MaterialDBAdapter(this);
	ProfileDBAdapter proDBA = new ProfileDBAdapter(this);

	FragmentManager fragMAN = getFragmentManager();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DBA.open2();
		elDBA.open();
		matDBA.open();
		proDBA.open();

		setContentView(R.layout.activity_main);

		// Check whether the activity is using the layout version with
		// the fragment_container FrameLayout. If so, we must add the first
		// fragment
		// if (findViewById(R.id.fragment_container) != null) {

		// However, if we're being restored from a previous state,
		// then we don't need to do anything and should return or else
		// we could end up with overlapping fragments.
		if (savedInstanceState != null) {
			return;
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

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		DBA.close();
	}

	public void onArticleSelected(int position) {

		ModelFragment fragDet = (ModelFragment) fragMAN
				.findFragmentById(R.id.article_fragment);
		
		switch (position) {
		case 0:
			Cursor C = elDBA.getAllElements();
			String[] Array = getArrayList(C, 1);
			fragDet.updateArticleView(Array);
			break;
		case 1:
			Cursor C = matDBA.getAllMaterials();
			String[] Array = getArrayList(C, 1);
			fragDet.updateArticleView(Array);
			break;
		case 2:
			Cursor C = proDBA.getAllProfiles();
			String[] Array = getArrayList(C, 1);
			fragDet.updateArticleView(Array);
			break;
		}

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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

	@Override
	public void onItemSelected(int position) {
		// /implementare
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

}
