package com.example.fem;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ModelFragment extends ListFragment {
	final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;

	ElementDBAdapter elementDBA = new ElementDBAdapter(this.getActivity())
			.open();
	ProfileDBAdapter profileDBA = new ProfileDBAdapter(this.getActivity())
			.open();
	MaterialDBAdapter materialDBA = new MaterialDBAdapter(this.getActivity())
			.open();

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// If activity recreated (such as from screen rotate), restore
		// the previous article selection set by onSaveInstanceState().
		// This is primarily necessary when in the two-pane layout.
		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}

		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_container, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();

		// During startup, check if there are arguments passed to the fragment.
		// onStart is a good place to do this because the layout has already
		// been
		// applied to the fragment at this point so we can safely call the
		// method
		// below that sets the article text.
		Bundle args = getArguments();
		if (args != null) {
			// Set article based on argument passed in
			updateArticleView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			// Set article based on saved instance state defined during
			// onCreateView
			updateArticleView(mCurrentPosition);
		}
	}

	// modificare con popolare la listview con gli elementi del database

	public void updateArticleView(int position) {
		ListView detail=(ListView) getActivity().findViewById(R.id.article);
		
		switch (position) {
		case 0:
			Cursor elCursor = elementDBA.getAllElements();
			getActivity().startManagingCursor(elCursor);
			// Now create a new list adapter bound to the cursor.
			// SimpleListAdapter is designed for binding to a Cursor.
			// Specify the row template to use (here, two columns bound to the two retrieved cursor rows).

			
			ListAdapter adapter = new SimpleCursorAdapter(this.getActivity(), // Context.
					android.R.layout.two_line_list_item, elCursor, new String[] {"NAME","PROFILE"},
					new int[] { android.R.id.text1, android.R.id.text2 });
					// Pass in the cursor to bind to.
					// Array of cursor columns to bind to.	
					// Parallel array of which template objects to bind to those
					// columns.
					
			// Bind to our new adapter.
			setListAdapter(adapter);
			break;
		case 1:
			Cursor proCursor = profileDBA.getAllProfiles();
			
			
		}

		//TextView article = (TextView) getActivity().findViewById(R.id.article);
		//article.setText("aaaaa");
		// Ipsum.Articles[position]
		mCurrentPosition = position;
		
		
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save the current article selection in case we need to recreate the
		// fragment
		outState.putInt(ARG_POSITION, mCurrentPosition);
	}

}