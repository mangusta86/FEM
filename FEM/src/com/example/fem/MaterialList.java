package com.example.fem;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MaterialList extends ListFragment {

	private int index = 0;
    private ListMaterialSelectedListener selectedListener;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        index = position;
        selectedListener.onListMaterialSelected(position);
    }

    
    //allocare dinamicamente i materiali, creare un adapter e layout adatto
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(ArrayAdapter.createFromResource(getActivity(),
                R.array.modelArray, android.R.layout.simple_list_item_1));

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index", 0);
            selectedListener.onListMaterialSelected(index);
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
            selectedListener = (ListMaterialSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListItemSelectedListener in Activity");
        }
    }

    public interface ListMaterialSelectedListener {
        public void onListMaterialSelected(int index);
    }
}