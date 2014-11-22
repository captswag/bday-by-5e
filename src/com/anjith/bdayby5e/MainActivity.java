package com.anjith.bdayby5e;

import android.app.ListActivity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;


public class MainActivity extends ListActivity {

	SearchView searchView;
	Resources resources;
	
	//Strings to store names and bdays of friends
	String names[];
	String bdays[];
	
	ListView listView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        resources = getResources();
        
        //Get the values from string resource
        names = resources.getStringArray(R.array.names);
        bdays = resources.getStringArray(R.array.bday);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        	
        setListAdapter(adapter);
        
        listView = getListView();
        listView.setTextFilterEnabled(true);
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
        
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(arg0)) {
					listView.clearTextFilter();
				} else {
					listView.setFilterText(arg0.toString());
				}
				return true; //of onQueryTextChange(String arg0)
			}
		});
        
        return true; //of onCreateOptionsMenu()
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
    	switch(item.getItemId()) {
    	
    	case R.id.upcoming: Toast.makeText(this, R.string.upcoming, Toast.LENGTH_SHORT).show();
    		break;
    		
    	case R.id.about: Toast.makeText(this, R.string.about, Toast.LENGTH_SHORT).show();
    		break;
    		
    	}
    	
        return super.onOptionsItemSelected(item);
    }
}
