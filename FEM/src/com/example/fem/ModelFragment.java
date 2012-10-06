package com.example.fem;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fem.cad.Profile;
import com.example.fem.cad.ProfileAdapter;

public class ModelFragment extends ListFragment {
	
	OnItemSelectedListener mCallback;

	//final static String ARG_POSITION = "position";
	//int mCurrentPosition = -1;


	// The container Activity must implement this interface so the frag
	// can deliver messages
	public interface OnItemSelectedListener {
		/** Called by HeadlinesFragment when a list item is selected */
		public void onItemSelected(int position);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

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
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, new String[]{"NULL"}));
		
	//	getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
	}

	@Override
	public void onStart() {
		super.onStart();

		
	}
	
	/*@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save the current article selection in case we need to recreate the
		// fragment
		outState.putInt(ARG_POSITION, mCurrentPosition);
	}*/
	
	public void updateProfileView(ArrayList<Profile> list) {
		ProfileAdapter adapter= new ProfileAdapter(getActivity(),
				R.layout.adapter_profile,list);
		 setListAdapter(adapter);

	}
	
	public void updateArticleView(String[] array) {
	
		 setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, array));

	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Notify the parent activity of selected item
		mCallback.onItemSelected(position);

		// Set the item as checked to be highlighted when in two-pane layout
		getListView().setItemChecked(position, true);
	}
}