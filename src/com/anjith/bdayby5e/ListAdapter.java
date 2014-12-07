package com.anjith.bdayby5e;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<ArrayList<String>>{


	Context context;
	Filter filter;
	List<ArrayList<String>> originalList;
	List<ArrayList<String>> filterList;
	List<ArrayList<String>> duplicate;
	
	public ListAdapter(Context context, int resource, List<ArrayList<String>> objects) {
		super(context, resource, objects);
		this.context = context;
		this.originalList = objects;
		this.duplicate = objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return originalList.size();
	}
	
	@Override
	public ArrayList<String> getItem(int position) {
		// TODO Auto-generated method stub
		return originalList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return originalList.get(position).hashCode();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>(); //To get the ArrayList inside the ArrayList
		
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.activity_main_custom, parent, false);
		}
		
		temp = getItem(position); //We get the values in the inside ArrayList here
		
		TextView tl = (TextView) convertView.findViewById(R.id.listnumber); //tl means textview left in layout
		TextView tr = (TextView) convertView.findViewById(R.id.listvalues); //tr means textview right in layout
		
		tl.setText(temp.get(0)); //get the id
		tr.setText(temp.get(1)); //get the name
			
		return convertView;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if (filter == null) {
			filter = new ListFilter();
		}
		return filter;
	}
	
	public class ListFilter extends Filter {

		@SuppressLint("DefaultLocale") @Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// TODO Auto-generated method stub
			FilterResults result = new FilterResults();
			
			if (constraint == null || constraint.length() == 0) {
				result.values = duplicate;
				result.count = duplicate.size();
			}
			else {
				filterList = new ArrayList<ArrayList<String>>();
				int temp = 0;
				for (ArrayList<String> duplicateFilter : duplicate) {
					
					if (duplicateFilter.get(1).toUpperCase().startsWith(constraint.toString().toUpperCase())) {
						filterList.add(duplicateFilter);
						temp++;
					}
				}
				
				if (temp == 0)
					filterList = new ArrayList<ArrayList<String>>();
				
					result.values = filterList;
					result.count = filterList.size();
			}
			
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence arg0, FilterResults results) {
			// TODO Auto-generated method stub
			
				originalList = (List<ArrayList<String>>) results.values;
				notifyDataSetChanged();
			
		}
		
	}

}
