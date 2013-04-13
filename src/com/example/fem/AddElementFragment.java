package com.example.fem;

import com.example.fem.DB.ElementDBAdapter;
import com.example.fem.DB.MaterialDBAdapter;
import com.example.fem.DB.ProfileDBAdapter;
import com.example.fem.cad.Element;

import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AddElementFragment extends DialogFragment {

	int mNum;
	ElementDBAdapter elDBA = new ElementDBAdapter(getActivity());
	MaterialDBAdapter matDBA = new MaterialDBAdapter(getActivity());
	ProfileDBAdapter proDBA = new ProfileDBAdapter(getActivity());

	/**
	 * Create a new instance of MyDialogFragment, providing "num" as an
	 * argument.
	 */
	static AddElementFragment newInstance(int num) {
		AddElementFragment f = new AddElementFragment();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = getArguments().getInt("num");
		elDBA.open();
		// Pick a style based on the num.
		int style = DialogFragment.STYLE_NORMAL, theme = 0;
		// switch ((mNum-1)%6) {
		Cursor C = elDBA.getElement(mNum);
		Element modElem = new Element(C);
		/*
		 * 
		 * case 1: style = DialogFragment.STYLE_NO_TITLE; break; case 2: style =
		 * DialogFragment.STYLE_NO_FRAME; break; case 3: style =
		 * DialogFragment.STYLE_NO_INPUT; break; case 4: style =
		 * DialogFragment.STYLE_NORMAL; break; case 5: style =
		 * DialogFragment.STYLE_NORMAL; break; case 6: style =
		 * DialogFragment.STYLE_NO_TITLE; break; case 7: style =
		 * DialogFragment.STYLE_NO_FRAME; break; case 8: style =
		 * DialogFragment.STYLE_NORMAL; break; } switch ((mNum-1)%6) { case 4:
		 * theme = android.R.style.Theme_Holo; break; case 5: theme =
		 * android.R.style.Theme_Holo_Light_Dialog; break; case 6: theme =
		 * android.R.style.Theme_Holo_Light; break; case 7: theme =
		 * android.R.style.Theme_Holo_Light_Panel; break; case 8: theme =
		 * android.R.style.Theme_Holo_Light; break; }
		 */
		setStyle(style, theme);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.add_element, container, false);

		/*
		 * View tv = v.findViewById(R.id.text);
		 * ((TextView)tv).setText("Dialog #" + mNum + ": using style " +
		 * getNameForNum(mNum));
		 * 
		 * // Watch for button clicks. Button button =
		 * (Button)v.findViewById(R.id.show); button.setOnClickListener(new
		 * OnClickListener() { public void onClick(View v) { // When button is
		 * clicked, call up to owning activity.
		 * ((FragmentDialog)getActivity()).showDialog(); } });
		 */
		return v;
	}
}