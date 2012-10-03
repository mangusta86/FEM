package com.example.fem;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ModelFragment extends ListFragment {
	OnItemSelectedListener mCallback;

	final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	
	//public ElementDBAdapter elDBA=new ElementDBAdapter(getActivity());
	
	//ElementDBAdapter elDBA=new ElementDBAdapter(getActivity());
	//ElementDBAdapter elHE=elDBA.open();
	//MaterialDBAdapter matDBA=new MaterialDBAdapter(getActivity()).open();
	//ProfileDBAdapter proDBA=new ProfileDBAdapter(getActivity()).open();

	// The container Activity must implement this interface so the frag
	// can deliver messages
	public interface OnItemSelectedListener {
		/** Called by HeadlinesFragment when a list item is selected */
		public void onItemSelected(int position);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
			mCallback = (OnItemSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnItemSelectedListener");
		}
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		
		//tornato da 1 pane view... quindi bisogna ciclare sul valore
		// Create an array adapter for the list view, using the
		//if (mCurrentPosition==1)
		setListAdapter(ArrayAdapter.createFromResource(getActivity(),
				R.array.modelArray2,
				android.R.layout.simple_list_item_activated_1));
		//else
		//	setListAdapter(ArrayAdapter.createFromResource(getActivity(),
		//			R.array.modelArray,
		//			android.R.layout.simple_list_item_activated_1));
	}

	@Override
	public void onStart() {
		super.onStart();

		// When in two-pane layout, set the listview to highlight the selected
		// list item
		// (We do this during onStart because at the point the listview is
		// available.)
		Bundle args = getArguments();
		if (args != null) {
			// Set article based on argument passed in
			updateArticleView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			updateArticleView(mCurrentPosition);
		}
		
		
		if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save the current article selection in case we need to recreate the
		// fragment
		outState.putInt(ARG_POSITION, mCurrentPosition);
	}
	
	public void updateArticleView(int position) {
		
		mCurrentPosition = position;
		
		//used in two pane mode to set the list
		switch (mCurrentPosition){
		case -1: setListAdapter(ArrayAdapter.createFromResource(getActivity(),
				R.array.modelArray,
				android.R.layout.simple_list_item_activated_1));
		break;
		case 1:
			setListAdapter(ArrayAdapter.createFromResource(getActivity(),
					R.array.modelArray,
					android.R.layout.simple_list_item_activated_1));
			break;
		case 2:
				setListAdapter(ArrayAdapter.createFromResource(getActivity(),
						R.array.modelArray,
						android.R.layout.simple_list_item_activated_1));
				break;
		case 3:
			setListAdapter(ArrayAdapter.createFromResource(getActivity(),
					R.array.modelArray,
					android.R.layout.simple_list_item_activated_1));
			break;
		case 4:
			setListAdapter(ArrayAdapter.createFromResource(getActivity(),
					R.array.modelArray,
					android.R.layout.simple_list_item_activated_1));
			break;
		case 5:
			setListAdapter(ArrayAdapter.createFromResource(getActivity(),
					R.array.modelArray,
					android.R.layout.simple_list_item_activated_1));
			break;
		}
		
		
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Notify the parent activity of selected item
		mCallback.onItemSelected(position);

		// Set the item as checked to be highlighted when in two-pane layout
		getListView().setItemChecked(position, true);
	}
}