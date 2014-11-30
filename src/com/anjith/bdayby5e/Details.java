package com.anjith.bdayby5e;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_layout);
				
		setTitle(getIntent().getStringExtra("name"));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView obj = (TextView)findViewById(R.id.personname);
		obj.setText(getIntent().getStringExtra("name"));
	}

}
