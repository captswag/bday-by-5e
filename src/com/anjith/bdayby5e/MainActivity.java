package com.anjith.bdayby5e;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;


public class MainActivity extends ListActivity {

	SearchView searchView;
	Resources resources;
	
	//Strings to store names and bdays of friends
	String names[];
	String bdays[];
	
	ListView listView;
	ArrayAdapter<String> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        resources = getResources();
        
        //Get the values from string resource
        names = resources.getStringArray(R.array.names);
        bdays = resources.getStringArray(R.array.bday);
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        	
        setListAdapter(adapter);
        
        listView = getListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	int searchViewId;
    	
        getMenuInflater().inflate(R.menu.main, menu);
        
        searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        
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
				adapter.getFilter().filter(arg0.toString());
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
