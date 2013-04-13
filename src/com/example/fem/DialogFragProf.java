package com.example.fem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogFragProf extends DialogFragment {
	
	int modelItem;
	String ITEM="itemSelected";
	int itemSelected;
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
        Bundle bundle=getArguments(); 

        modelItem=bundle.getInt("modelItem",-1); 
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    
	    builder.setNegativeButton("back",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
            }
 });		
	    
	    switch (modelItem){
	    case 0:
	    	
	    	break;
	    case 1:
	    	builder.setTitle(R.string.pick_shape);
		    builder.setItems(R.array.shapeArray, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int which) {
		               // The 'which' argument contains the index position
	               // of the selected item
		            		   itemSelected=which;
		               }
		    });		
 		   break;
 		   
 	   case 2:
 		   break;
 	   case 3:
 		   break;
 	   case 4:
 		   break;	
	    
	    }
	    
	    bundle.putInt(ITEM, itemSelected);
	    return builder.create();
	    
	    
	}
}