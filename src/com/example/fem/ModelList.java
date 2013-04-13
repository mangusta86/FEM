package com.example.fem;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ModelList extends ListFragment {
	OnHeadlineSelectedListener mCallback;

	// The container Activity must implement this interface so the frag
	// can deliver messages
	public interface OnHeadlineSelectedListener {
		/** Called by HeadlinesFragment when a list item is selected */
		public void onArticleSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create an array adapter for the list view, using the
		setListAdapter(ArrayAdapter.createFromResource(getActivity(),
				R.array.modelArray,
				android.R.layout.simple_list_item_activated_1));

		// (getActivity(), android.R.layout.simple_list_item_activated_1,
		// Ipsum.Headlines));
	}

	@Override
	public void onStart() {
		super.onStart();

		// When in two-pane layout, set the listview to highlight the selected
		// list item
		// (We do this during onStart because at the point the listview is
		// available.)
		if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
			mCallback = (OnHeadlineSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Notify the parent activity of selected item
		mCallback.onArticleSelected(position);

		// Set the item as checked to be highlighted when in two-pane layout
		getListView().setItemChecked(position, true);
	}
}