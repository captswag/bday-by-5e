package com.anjith.bdayby5e;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

	SearchView searchView;
	Resources resources;
	String names[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        resources = getResources();
        
        names = resources.getStringArray(R.array.names);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        return true;
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
