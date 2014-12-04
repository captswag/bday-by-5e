package com.anjith.bdayby5e;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class UpcomingActivity extends ListActivity{

	String names[];
	String bdays[];
	
	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	HashMap<String,String> item;
	SimpleAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upcoming);
		
		names = getResources().getStringArray(R.array.names);
		bdays = getResources().getStringArray(R.array.bday);
		
		getActionBar().setDisplayHomeAsUpEnabled(true); //Back button on ActionBar
		
		//Add names and bdays to HashMap object item and then add it to ArrayList list
		for (int i = 0 ; i < names.length; i++) {
			item = new HashMap<String, String>();
			item.put("name", names[i]);
			item.put("bday", bdays[i]);
			list.add(item);
		}
		
		adapter = new SimpleAdapter(this, list, R.layout.listview_layout, new String[] {"bday", "name"}, new int[] {R.id.row1, R.id.row2});
		setListAdapter(adapter);
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent detailsIntent = new Intent(this, Details.class);
		String name = list.get(position).get("name");
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
		// TODO Auto-generated method stub
		//To navigate to MainActivity
		NavUtils.navigateUpFromSameTask(this);
		
		return super.onOptionsItemSelected(item);
	}

}
