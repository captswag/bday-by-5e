package com.anjith.bdayby5e;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class UpcomingActivity extends ListActivity{

	String names[];
	String bdays[];
	int times[];
	
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
		
		sortBirthdays(); //Function to sort bdays and dates accordingly
		
		String temp;
		//Add names and bdays to HashMap object item and then add it to ArrayList list
		for (int i = 0 ; i < names.length; i++) {
			item = new HashMap<String, String>();
			item.put("name", names[i]);
			
			if (times[i] == 0) 
				temp = "Today";
			else if (times[i] == 1)
				temp = "Tomorrow";
			else 
				temp = times[i] + " days to go";
			
			item.put("bday", temp);
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
	
	@SuppressWarnings("deprecation")
	public void sortBirthdays() {
		int i, j, localTemp;;
		String[] split;
		String temp;
		times = new int[names.length];
		
		Date[] bday = new Date[names.length];
		Date today = new Date(); //Today with all values including current time [Default Constructor]
		Date todayWithoutRest = new Date(today.getYear(), today.getMonth(), today.getDate()); //Today without time
		
		for (i = 0; i < names.length; i++) {
			split = bdays[i].split(" ");
			bday[i] = new Date(Integer.parseInt(split[2])-1900, Integer.parseInt(split[1]), Integer.parseInt(split[0]));
			
			bday[i].setYear(today.getYear()); //To change the birthyear to current year eg: 1993 to 2014
			
			if (bday[i].before(todayWithoutRest)) {
				bday[i].setYear(today.getYear()+1);
			}
			
			times[i] = (int)((bday[i].getTime() / 1000L - todayWithoutRest.getTime() / 1000L) / 86400L);
			Log.d(names[i], times[i]+"");
		}
		
		for (i = 0; i < names.length; i++) {
			for (j = i+1; j < names.length; j++) {
				if (times[i]>times[j]) {
					localTemp = times[i];
					times[i] = times[j];
					times[j] = localTemp;
					
					temp = names[i];
					names[i] = names[j];
					names[j] = temp;
					
					temp = bdays[i];
					bdays[i] = bdays[j];
					bdays[j] = temp;
				}
			}
		}
		
	}

}
