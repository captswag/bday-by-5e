package com.anjith.bdayby5e;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Details extends Activity{

	
	String bday, temp;
	String[] split;
	String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December"
	};
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_layout);
				
		setTitle(getIntent().getStringExtra("name"));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView obj = (TextView)findViewById(R.id.personname); //To set the name
		obj.setText(getIntent().getStringExtra("name"));
		
		bday = getIntent().getStringExtra("bday");

		split = bday.split(" ");
		
		Date today = new Date(); //Current date
		
		Date bdayDate = new Date(Integer.parseInt(split[2])-1900, Integer.parseInt(split[1]), Integer.parseInt(split[0])); //B'Day
		
		Date todayWithoutRest = new Date(today.getYear(), today.getMonth(), today.getDate()); //Today without time
		
		Date realBday = (Date) bdayDate.clone(); //B'Day without changing to current year
		
		obj = (TextView)findViewById(R.id.personbday); //To set the birthday
		obj.setText(String.format("%02d", realBday.getDate())+" "+months[realBday.getMonth()]+" "+(realBday.getYear()+1900));
		
		obj = (TextView)findViewById(R.id.personday); //To set the day of birth
		obj.setText(Html.fromHtml("You were born on <b>"+days[realBday.getDay()]+"</b>"));
		
		obj = (TextView)findViewById(R.id.personstarsign);
		obj.setText(Html.fromHtml("Your star sign is <b>"+getStarSign(realBday)+"</b>"));
		
		int k = (int) (todayWithoutRest.getTime() / 1000L - realBday.getTime() / 1000L);
		temp = "You are <b>"+k/31536000+" years</b>, <b>";
		k %= 31536000;
		temp += k/2592000 + " months</b>, & <b>";
		k %= 2592000;
		temp += k/86400 +" days</b> old";
				
		obj = (TextView)findViewById(R.id.personage);
		obj.setText(Html.fromHtml(temp));
		
		bdayDate.setYear(today.getYear()); //To change the birthyear to current year eg: 1993 to 2014
		
		if (bdayDate.before(todayWithoutRest)) {
			bdayDate.setYear(today.getYear()+1);
		}
		
		k = (int)((bdayDate.getTime() / 1000L - todayWithoutRest.getTime() / 1000L) / 86400L);	
		
		if (k == 0) 
			temp = "<b>Today</b> is your B'DAY";
		else if (k == 1)
			temp = "Your next B'DAY happens to be <b>tomorrow</b>";
		else 
			temp = "It's <b>" + k + " days</b> until your next B'DAY";	
		
		obj = (TextView)findViewById(R.id.personnextdays);
		obj.setText(Html.fromHtml(temp));
		
		k = (int)((todayWithoutRest.getTime() / 1000L - realBday.getTime() / 1000L) / 86400L);
		
		obj = (TextView)findViewById(R.id.persondaysold);
		obj.setText(Html.fromHtml("You are <b>"+k+" days</b> old"));
		
		k = (int)((today.getTime() / 1000L - realBday.getTime() / 1000L) / 3600L);
		
		obj = (TextView)findViewById(R.id.personhour);
		obj.setText(Html.fromHtml("You are approximately <b>"+k+" hours</b> old"));
		
		k = (int)(today.getTime() / 1000L - realBday.getTime() / 1000L);
		
		obj = (TextView)findViewById(R.id.personseconds);
		obj.setText(Html.fromHtml("You are approximately <b>"+k+" seconds</b> old"));
	}
	
	@SuppressWarnings("deprecation")
	public String getStarSign(Date realBday) {
		
		if (getIntent().getStringExtra("name").equals("Josna Baby"))
			return "Capricorn";
		
		Date before, after;
		int tempYear = realBday.getYear();
		
		after = new Date(tempYear, 2, 22);
		before = new Date(tempYear, 3, 20);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Aries";
		
		after = new Date(tempYear, 3, 19);
		before = new Date(tempYear, 4, 21);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Taurus";
		
		after = new Date(tempYear, 4, 20);
		before = new Date(tempYear, 5, 21);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Gemini";
		
		after = new Date(tempYear, 5, 20);
		before = new Date(tempYear, 6, 23);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Cancer";
		
		after = new Date(tempYear, 6, 22);
		before = new Date(tempYear, 7, 23);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Leo";
		
		after = new Date(tempYear, 7, 22);
		before = new Date(tempYear, 8, 23);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Virgo";
		
		after = new Date(tempYear, 8, 22);
		before = new Date(tempYear, 9, 23);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Libra";
		
		after = new Date(tempYear, 9, 22);
		before = new Date(tempYear, 10, 22);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Scorpio";
		
		after = new Date(tempYear, 10, 21);
		before = new Date(tempYear, 11, 22);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Sagittarius";
		
		after = new Date(tempYear, 11, 21);
		before = new Date(tempYear+1, 0, 20);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Capricorn";
		
		after = new Date(tempYear, 0, 19);
		before = new Date(tempYear+1, 1, 19);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Aquarius";
		
		after = new Date(tempYear, 1, 18);
		before = new Date(tempYear, 2, 21);
		
		if (realBday.after(after)&&realBday.before(before))
			return "Pisces";
		
		return null;
	}
	
}