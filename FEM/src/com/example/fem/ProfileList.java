package com.example.fem;

import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ProfileList extends ListFragment {

	private int index = 0;
	private ListProfileSelectedListener selectedListener;

	Cursor model = null;
//	ProfileAdapter adapter = null;
	EditText name = null;
	EditText address = null;
	EditText notes = null;
	RadioGroup types = null;
	//ProfileHelper db = null;
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		index = position;
		selectedListener.onListProfileSelected(position);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
	ViewGroup container, Bundle savedInstanceState) { 
		return inflater.inflate(R.layout.array_profile, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    /*// create new DBAdapter
	    db = new ProfileHelper(getActivity());
	    db.open();

	    Cursor c = db.getAllRecords();

	    String[] from = new String[] { DBAdapter.KEY_IDNO, DBAdapter.KEY_MATERIAL };
	    int[] to = new int[] { R.id.idno, R.id.materials };        

	    // Now create an array adapter and set it to display using our row
	    MyListAdapter materials = new MyListAdapter(getActivity(), R.layout.list_cell, c, from, to);	 
	    setListAdapter(materials);*/
	 }

	public interface ListProfileSelectedListener {
		public void onListProfileSelected(int index);
	}

	/*class ProfileAdapter extends CursorAdapter {
		ProfileAdapter(Cursor c) {
			super(ProfileList.this, c);
		}

		@Override
		public void bindView(View row, Context ctxt, Cursor c) {
			RestaurantHolder holder = (RestaurantHolder) row.getTag();

			holder.populateFrom(c, helper);
		}

		@Override
		public View newView(Context ctxt, Cursor c, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.row, parent, false);
			RestaurantHolder holder = new RestaurantHolder(row);

			row.setTag(holder);

			return (row);
		}
	}

	static class RestaurantHolder {
		private TextView name = null;
		private TextView address = null;
		private ImageView icon = null;

		RestaurantHolder(View row) {
			name = (TextView) row.findViewById(R.id.title);
			address = (TextView) row.findViewById(R.id.address);
			icon = (ImageView) row.findViewById(R.id.icon);
		}

		void populateFrom(Cursor c, ProfileHelper helper) {
			name.setText(helper.getName(c));
			address.setText(helper.getAddress(c));

			if (helper.getType(c).equals("sit_down")) {
				icon.setImageResource(R.drawable.ball_red);
			} else if (helper.getType(c).equals("take_out")) {
				icon.setImageResource(R.drawable.ball_yellow);
			} else {
				icon.setImageResource(R.drawable.ball_green);
			}
		}
	}
	
	public class MyListAdapter extends SimpleCursorAdapter {

		public MyListAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to) {
		    super(context, layout , cursor, from, to);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {

		    // Create the idno textview with background image
		    TextView idno = (TextView) view.findViewById(R.id.idno);
		    idno.setText(cursor.getSring(3));

		    // create the material textview
		    TextView materials = (TextView) view.findViewById(R.id.materials);
		    materials.setText(cursor.getString(1));
		}
}*/
}