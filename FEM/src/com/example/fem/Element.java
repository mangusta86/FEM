package com.example.fem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fem.Profile.Rectangle;

public class Element extends Activity {
int a=0;
public Profile rec=new Profile();
public Profile.Rectangle rectangle = rec.new Rectangle();
public Material mat = new Material();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_element);
	}

	@Override
	public void onResume() {
		super.onResume();
		
		if (a==1) {
			try {
				openFile();
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		TextView t = new TextView(this);

		t = (TextView) findViewById(R.id.prova);
		t.setText(mat.getName() + rectangle.base);
		}			
	}

	public void openFile() throws StreamCorruptedException, IOException, ClassNotFoundException{

		
	String FILENAME1 = "hello_file1";
	String FILENAME2 = "hello_file2";
	String FILENAME3 = "hello_file3";


	FileInputStream fis1 = openFileInput(FILENAME1);
	ObjectInputStream iis1 = new ObjectInputStream(fis1);
	rec=(Profile) iis1.readObject();
	iis1.close();
	fis1.close();

	FileInputStream fis2 = openFileInput(FILENAME2);
	ObjectInputStream iis2 = new ObjectInputStream(fis2);
	rectangle=(Rectangle) iis2.readObject();
	iis2.close();
	fis2.close();
	
	FileInputStream fis3 = openFileInput(FILENAME3);
	ObjectInputStream iis3 = new ObjectInputStream(fis3);
	mat= (Material) iis3.readObject();
	iis3.close();
	fis3.close();		
	}
	
	public void modella(View view) {
		a=1;
		Intent myIntent = new Intent(Element.this, SetProperty.class);
		Element.this.startActivity(myIntent);
	}
}
