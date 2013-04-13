package com.example.fem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fem.DB.ElementDBAdapter;
import com.example.fem.DB.MaterialDBAdapter;
import com.example.fem.DB.ProfileDBAdapter;
import com.example.fem.cad.Element;

public class AddElement extends Activity implements OnItemSelectedListener {

	int mNum;
	public String ACTION;
	public Element element = new Element();
	public String[] matName;
	public String[] proName;
	public String selectedMAT;
	public String selectedPRO;

	ElementDBAdapter elDBA = new ElementDBAdapter(this);
	MaterialDBAdapter matDBA = new MaterialDBAdapter(this);
	ProfileDBAdapter proDBA = new ProfileDBAdapter(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		ACTION = intent.getStringExtra("ACTION");
		mNum = intent.getIntExtra("nItem", -1);

		setContentView(R.layout.add_element);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		matDBA.open();
		proDBA.open();
		elDBA.open();

		matName = matDBA.getArrayMatName();

		proName = proDBA.getArrayProName();

		EditText name = (EditText) findViewById(R.id.editText1);
		EditText X1 = (EditText) findViewById(R.id.editX1);
		EditText Y1 = (EditText) findViewById(R.id.editY1);
		EditText Z1 = (EditText) findViewById(R.id.editZ1);
		EditText X2 = (EditText) findViewById(R.id.editX2);
		EditText Y2 = (EditText) findViewById(R.id.editY2);
		EditText Z2 = (EditText) findViewById(R.id.editZ2);
		Spinner spinnerMat = (Spinner) findViewById(R.id.spinner2);
		Spinner spinnerPro = (Spinner) findViewById(R.id.spinner1);
		
		spinnerMat.setOnItemSelectedListener(this);
		spinnerPro.setOnItemSelectedListener(this);
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, matName);

		spinnerMat.setAdapter(adapter);

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,

				proName);

		spinnerPro.setAdapter(adapter2);

		// action to take if we are modifying a element
		if (ACTION.equals("modify")) {

			Long pp = (long) mNum + 1;

			Cursor elC = elDBA.getElement(pp);
			// C.moveToFirst();
			Element elementC = new Element(elC);
			elC.close();
			element = elementC;

			//

			name.setText(element.getName());

			X1.setText(element.getStringX1());

			Y1.setText(element.getStringY1());

			Z1.setText(element.getStringZ1());

			X2.setText(element.getStringX2());

			Y2.setText(element.getStringY2());

			Z2.setText(element.getStringZ2());

			String myString = element.getMaterial();
			int spinnerPosition = adapter.getPosition(myString);
			spinnerMat.setSelection(spinnerPosition);

			String myString2 = element.getProfile();
			int spinnerPosition2 = adapter2.getPosition(myString2);
			spinnerPro.setSelection(spinnerPosition2);

		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		matDBA.close();
		proDBA.close();
		elDBA.close();
	}

	public void savePreference(MenuItem item) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_add_element, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
			
		case R.id.save_element: {


			Element elementMOD = getFromLayout();



			if (ACTION.equals("modify")) {
				Long pp = (long) mNum + 1;
				Boolean elCheck = elDBA.updateElement(pp, elementMOD.getName(),
						elementMOD.getProfile(), elementMOD.getMaterial(),
						elementMOD.getDoubleX1(), elementMOD.getDoubleY1(),
						elementMOD.getDoubleZ1(), elementMOD.getDoubleX2(),
						elementMOD.getDoubleY2(), elementMOD.getDoubleZ2());
				if (elCheck) {
					Context context = getApplicationContext();
					CharSequence text = "Element Saved, you can come back";
					int duration = Toast.LENGTH_SHORT;
					Toast.makeText(context, text, duration).show();
				} else {
					Context context = getApplicationContext();
					CharSequence text = "There was an error";
					int duration = Toast.LENGTH_SHORT;
					Toast.makeText(context, text, duration).show();
				}
			} else if (ACTION.equals("create")) {
				Long result = elDBA.createElement(elementMOD.getName(),
						elementMOD.getProfile(), elementMOD.getMaterial(),
						elementMOD.getDoubleX1(), elementMOD.getDoubleY1(),
						elementMOD.getDoubleZ1(), elementMOD.getDoubleX2(),
						elementMOD.getDoubleY2(), elementMOD.getDoubleZ2());
				if (result == -1) {
					Context context = getApplicationContext();
					CharSequence text = "There was an error";
					int duration = Toast.LENGTH_SHORT;
					Toast.makeText(context, text, duration).show();
				} else {
					Context context = getApplicationContext();
					CharSequence text = "Element Saved, you can come back";
					int duration = Toast.LENGTH_SHORT;
					Toast.makeText(context, text, duration).show();
				}
			}
			return true;
		}

		}
		return super.onOptionsItemSelected(item);
	}

	public Element getFromLayout() {
		Element el = new Element();
		EditText name = (EditText) findViewById(R.id.editText1);
		EditText X1 = (EditText) findViewById(R.id.editX1);
		EditText Y1 = (EditText) findViewById(R.id.editY1);
		EditText Z1 = (EditText) findViewById(R.id.editZ1);
		EditText X2 = (EditText) findViewById(R.id.editX2);
		EditText Y2 = (EditText) findViewById(R.id.editY2);
		EditText Z2 = (EditText) findViewById(R.id.editZ2);
	//	Spinner spinnerMat = (Spinner) findViewById(R.id.spinner2);
	//	Spinner spinnerPro = (Spinner) findViewById(R.id.spinner1);

		el.setName(name.getText().toString());
		
		el.setMaterial(selectedMAT);
		el.setProfile(selectedPRO);
		el.setX1(X1.getText().toString());
		el.setY1(Y1.getText().toString());
		el.setZ1(Z1.getText().toString());
		el.setX2(X2.getText().toString());
		el.setY2(Y2.getText().toString());
		el.setZ2(Z2.getText().toString());

		return el;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		switch (parent.getId()) {
		case R.id.spinner2:
			selectedMAT = parent.getItemAtPosition(pos).toString();
			break;
		case R.id.spinner1:
			selectedPRO = parent.getItemAtPosition(pos).toString();
			break;
		}

		// An item was selected. You can retrieve the selected item using
		// parent.getItemAtPosition(pos)
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
