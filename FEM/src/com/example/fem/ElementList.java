package com.example.fem;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ElementList extends ListFragment {
	
	 private int index = 0;
	    private ListElementSelectedListener selectedListener;

	    @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        index = position;
	        selectedListener.onListElementSelected(position);
	    }

	    
	    //modificare la lista dinamicamente
	    //cambiare il layout in un layout personalizzato??
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        
	        setListAdapter(ArrayAdapter.createFromResource(getActivity(),
	                R.array.modelArray, android.R.layout.simple_list_item_1));

	        if (savedInstanceState != null) {
	            index = savedInstanceState.getInt("index", 0);
	            selectedListener.onListElementSelected(index);
	        }
	    }

	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("index", index);
	    }

	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        try {
	            selectedListener = (ListElementSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement ListItemSelectedListener in Activity");
	        }
	    }

	    public interface ListElementSelectedListener {
	        public void onListElementSelected(int index);
	    }
	}

		