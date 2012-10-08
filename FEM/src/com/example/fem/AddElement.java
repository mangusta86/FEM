package com.example.fem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fem.DB.ElementDBAdapter;
import com.example.fem.DB.MaterialDBAdapter;
import com.example.fem.DB.ProfileDBAdapter;
import com.example.fem.cad.Element;

public class AddElement extends Activity {

	int mNum;
    ElementDBAdapter elDBA = new ElementDBAdapter(this);
    MaterialDBAdapter matDBA = new MaterialDBAdapter(this);
	ProfileDBAdapter proDBA = new ProfileDBAdapter(this);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mNum = intent.getIntExtra("nItem",-1);
<<<<<<< HEAD
        		
=======
        Long pp=(long) mNum+1;		
>>>>>>> other stuff
        setContentView(R.layout.add_element);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        elDBA.open();
        matDBA.open();
        proDBA.open();
<<<<<<< HEAD
        Cursor C = elDBA.getElement(mNum);
        Element element=new Element(C);
        
=======
        Cursor elC = elDBA.getElement(pp);
       // C.moveToFirst();
        Element element=new Element(elC);
        elC.close();
        
        String[] matName=matDBA.getArrayMatName();
        
        String[] proName=proDBA.getArrayProName();
>>>>>>> other stuff
        
        
        //
        EditText name=(EditText)findViewById(R.id.editText1);
        name.setText(element.getName());
        
        EditText X1=(EditText)findViewById(R.id.editX1);
        X1.setText(element.getStringX1());
        
        EditText Y1=(EditText)findViewById(R.id.editY1);
        Y1.setText(element.getStringY1());
        
        EditText Z1=(EditText)findViewById(R.id.editZ1);
        Z1.setText(element.getStringZ1());
        
        
        EditText X2=(EditText)findViewById(R.id.editX2);
        X2.setText(element.getStringX2());
        
        EditText Y2=(EditText)findViewById(R.id.editY2);
        Y2.setText(element.getStringY2());
        
        EditText Z2=(EditText)findViewById(R.id.editZ2);
        Z2.setText(element.getStringZ2());
        
        
        Spinner spinnerMat = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        		this,
        		android.R.layout.simple_spinner_item,
<<<<<<< HEAD
        		new String[]{"mat1","pluto","paperino","topolino"}
        		);            
        String myString = element.getMaterial(); 
        int spinnerPosition = adapter.getPosition(myString);
        spinnerMat.setSelection(spinnerPosition);
        spinnerMat.setAdapter(adapter);
=======
        		matName
        		);            
        String myString = element.getMaterial(); 
        int spinnerPosition = adapter.getPosition(myString);
       
        spinnerMat.setAdapter(adapter);
        spinnerMat.setSelection(spinnerPosition);
>>>>>>> other stuff
        
        Spinner spinnerPro = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
        		this,
        		android.R.layout.simple_spinner_item,
<<<<<<< HEAD
        		new String[]{"pippo","pro2","pro1","pro3"}
        		);
        String myString2 = element.getProfile(); 
        int spinnerPosition2 = adapter2.getPosition(myString2);
        spinnerMat.setSelection(spinnerPosition2);
        spinnerPro.setAdapter(adapter2); 
        
=======
        		proName
        		);
        String myString2 = element.getProfile(); 
        int spinnerPosition2 = adapter2.getPosition(myString2);

        spinnerPro.setAdapter(adapter2); 
        spinnerPro.setSelection(spinnerPosition2);
>>>>>>> other stuff
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
           //     ActionBar.navigateUpFromSameTask(this);
                return true;
          /*  case android.R.id.menu_save: {
            	Elem element;           	
            	getElementFromLayout(element);
            	saveElementInDatabaseModel(element);
            	finish();
            	return true;
            	}*/
            	
        }
        return super.onOptionsItemSelected(item);
    }

}
