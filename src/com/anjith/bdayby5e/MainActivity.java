package com.anjith.bdayby5e;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


public class MainActivity extends ListActivity {

	SearchView searchView;
	Resources resources;
	
	//Strings to store names and bdays of friends
	String names[];
	String bdays[];
	
	ListView listView;
	ArrayList<ArrayList<String>> list;
	ListAdapter customAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        resources = getResources();
        
        //Get the values from string resource
        names = resources.getStringArray(R.array.names);
        bdays = resources.getStringArray(R.array.bday);
        
        list = new ArrayList<ArrayList<String>>();
        ArrayList<String> temp;
        
        for (int i = 0 ; i < names.length; i++) {
        	
        	temp = new ArrayList<String>();
        	
        	if (i>=8) { //Number 9 left college, so incrementing the value by 1 
        		temp.add((i+2)+"");
        		temp.add(names[i]);        		
        	}
        	else {
            	temp.add((i+1)+"");
            	temp.add(names[i]);
        	}
        	
        	list.add(temp);
        }
        listView = getListView();
        customAdapter = new ListAdapter(this, R.layout.activity_main_custom, list);
        
        listView.setAdapter(customAdapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	int searchViewId;
    	
        getMenuInflater().inflate(R.menu.main, menu);
        
        searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS); //To capitalize the first alphabet in name
        
        //Get the SearchPlate id
        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        
        //Get the SearchPlate view and set it's background to transparent to remove blue EditText default drawable
        View searchPlate = searchView.findViewById(searchPlateId);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);
        
        //Get the id of SearchView's EditText
        searchViewId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        
        //Get the EditText in SearchView using the above id
        EditText searchEdit = (EditText) searchView.findViewById(searchViewId);
        searchEdit.setBackgroundResource(R.drawable.bdaysearc_edit_text_holo_light);
        
        searchEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				customAdapter.getFilter().filter(arg0.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		}); //End of onTextChangedListener();
        
        return true; //of onCreateOptionsMenu()
    }
    
    

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent detailsIntent = new Intent(this, Details.class);
		TextView temp = (TextView)v.findViewById(R.id.listvalues);
		String name = temp.getText().toString(); //Getting the name from the listview itself
		detailsIntent.putExtra("name", name);
		
		//To check for bday of person according to name
		for (int i = 0 ; i < names.length ; i ++ ) {
			if (names[i].equals(name)) {
				detailsIntent.putExtra("bday", bdays[i]);
				break;
			}
		}
		
		startActivity(detailsIntent);
	}


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
    	switch(item.getItemId()) {
    	
    	case R.id.upcoming: startActivity(new Intent(this, UpcomingActivity.class));
    		break;
    		
    	case R.id.about: startActivity(new Intent(this, AboutActivity.class));
    		break;
    		
    	}
    	
        return super.onOptionsItemSelected(item);
    }
}
