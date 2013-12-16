package net.iconoclazt.duracalc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void calculate(View v) {
    	// get references to the necessary components
    	TextView txvwDuration = (TextView)findViewById(R.id.txvwDuration);
    	DatePicker dpStart = (DatePicker)findViewById(R.id.dtpkStartDate);
    	DatePicker dpEnd = (DatePicker)findViewById(R.id.dtpkEndDate);
    	// set the start and end years chosen in the datepickers
    	int startYear	= dpStart.getYear(),
    		endYear		= dpEnd.getYear();
    	// get calendars
    	Calendar calStart = Calendar.getInstance();
    	Calendar calEnd = Calendar.getInstance();
    	// set the calendars
    	calStart.set(
    			startYear, 
    			dpStart.getMonth(), 
    			dpStart.getDayOfMonth()
    	);
    	calEnd.set(
    			endYear, 
    			dpEnd.getMonth(), 
    			dpEnd.getDayOfMonth()
    	);
    	// get times in milliseconds
		long	end		= calEnd.getTimeInMillis(),
				start	= calStart.getTimeInMillis();
		
    	// calculate the difference in time
    	long difference = end > start ? end - start : start - end;
    	
    	// calculate units of time
    	long 	seconds = difference / 1000,
    			minutes = difference / (60 * 1000),
    			hours	= difference / (60 * 60 * 1000),
    			days	= difference / (24 * 60 * 60 * 1000),
    			years	= endYear - startYear;
    	
    	// show the duration in various units
    	txvwDuration.setText("Duration:" +
    						 "\ns:\t\t" + NumberFormat.getNumberInstance(Locale.US).format(seconds) + 
    						 "\nm:\t\t" + NumberFormat.getNumberInstance(Locale.US).format(minutes) +
    						 "\nh:\t\t" + NumberFormat.getNumberInstance(Locale.US).format(hours) + 
    						 "\nd:\t\t" + NumberFormat.getNumberInstance(Locale.US).format(days) +
    						 "\ny:\t\t" + years);
    }
}
