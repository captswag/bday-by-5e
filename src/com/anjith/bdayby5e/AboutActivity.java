package com.anjith.bdayby5e;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

public class AboutActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutactivity);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		NavUtils.navigateUpFromSameTask(this);
		return super.onOptionsItemSelected(item);
	}
	
	public void githubOpen(View view) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/4k3R/bday-by-5e")));
	}

}
