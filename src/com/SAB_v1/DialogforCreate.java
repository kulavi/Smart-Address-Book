package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
public class DialogforCreate extends Activity
{
	Button b1,b2, mpickDate,mpickTime;
	TextView text1;
	EditText ename,mDate,mTime;
	String sdate,stime1,sdate1;
	DataBaseHelper data;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 0;
	private int mHour;
	private int mMinute;
	String n;
	int count,id;
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ year + "-" + (month+1)+ "-" + day);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.maindialog);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
        ((TextView)findViewById(R.id.title)).setText("Create Call List");
        findViewById(R.id.back).setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v) 
        	{
        		Intent i=new Intent(DialogforCreate.this,Main.class);
        		startActivity(i);
        }
        });
		//setTitle("Create Call List");
		text1=(TextView)findViewById(R.id.text1);
		ename=(EditText)findViewById(R.id.name);
		mDate=(EditText)findViewById(R.id.edate);
		mTime=(EditText)findViewById(R.id.etime);
		mpickDate = (Button)findViewById(R.id.Date);
		mpickDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
		mpickTime = (Button)findViewById(R.id.Time);
		mpickTime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		// get the current date and time
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		updateDisplay();
		updateDisplay1();
		b1 = (Button)findViewById(R.id.ok);
    	b1.setOnClickListener(new OnClickListener() {
 		public void onClick(View v) {
			checkvalidate();
			}

		});
		b1 = (Button)findViewById(R.id.cancel);
   b1.setOnClickListener(new OnClickListener() {
    public void onClick(View v) {

 	 finish();
        }
    });
	//now that the dialog is set up, it's time to show it    
	}
	 /*type :function
  	name:checkvalidate
  	return type:void          
	date:10-2-12
  	purpose:validations*/
	public void checkvalidate()
	{
		if((ename.getText().toString()).equals(""))
    	{
    		text1.setText("Call List Name not entered!!");
    	}
    	else if((mDate.getText().toString()).equals(""))
    	{
    		text1.setText("Date not selected!!");
    	}	
    	else
    	{
    		n=ename.getText().toString();
    		Cursor c=data.checkname(n);
    		count=c.getCount();
    		if(count==0)
    		{
    		data.InsertCall(n,sdate1,stime1);
			call();
    		}
    		else
			{
    			//text1.setText("CallList Already Exists Enter Some other Name");
    			opendialog();
			}
    		c.close();	
    	}
		//sets alaram
		  AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); 
	      Calendar AlarmCal = Calendar.getInstance();
	      AlarmCal.setTimeInMillis(System.currentTimeMillis());
	      AlarmCal.set(Calendar.YEAR,mYear);
	      AlarmCal.set(Calendar.MONTH,mMonth);
	      AlarmCal.set(Calendar.DAY_OF_MONTH,mDay);   
	      AlarmCal.set(Calendar.HOUR_OF_DAY,mHour);  // set user selection
	      AlarmCal.set(Calendar.MINUTE,mMinute);        // set user selection
	      AlarmCal.set(Calendar.SECOND, 0);
	      Intent i = new Intent(DialogforCreate.this,DisplayNotification_call.class);
	      Bundle bun=new Bundle();                   
			bun.putString("listname",n); 
			bun.putInt("NotifID",1);
          //---assign an ID of 1---
          i.putExtras(bun);                                

          PendingIntent displayIntent = PendingIntent.getActivity(
              getBaseContext(), 0, i, 0);         
	      alarmManager.set(AlarmManager.RTC_WAKEUP, 
                  AlarmCal.getTimeInMillis(), displayIntent);
	}
	 /*type :function
  	name:opendialog
  	return type:void          
	date:10-2-12
  	purpose:showa alert box*/
	private void opendialog() 
	{
		
		final AlertDialog.Builder alertbox=new AlertDialog.Builder(this);
		data=new DataBaseHelper(this);
						// set the message to display
                alertbox.setMessage("CallList Already Exists Do You want to continue");
     
                // set a positive/yes button and create a listener
                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {   
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                    	//n=ename.getText().toString();
                    	Cursor c1=data.checkname(n);
                    	while(c1.moveToNext())
                    	{
                    		id=c1.getInt(0);
                    		System.out.println("ID IS "+id);
                    		data.UpdateList(id, n, sdate1, stime1);
                    		call();		
                    	}
                    	c1.close();
                    	
                    
                    	
                    }
                });
     
                // set a negative/no button and create a listener
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) 
                    {
                    	Intent i= new Intent(DialogforCreate.this,DialogforCreate.class);
                    	startActivity(i);
                    }
                });
     
                // display box
                alertbox.show();
 				}
	 /*type :function
  	name:call
  	return type:void          
	date:10-2-12
  	purpose:calls to create call list class*/
	public void call()
	{
		Intent newActivity = new Intent(DialogforCreate.this,CreateList.class);
		Bundle bun=new Bundle();
		bun.putString("listname",n);
		newActivity.putExtras(bun);
		startActivity(newActivity);  
	}
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.maccount:Intent i = new Intent(this, Account.class);
		startActivity(i);
		break;
		case R.id.mallconct:Intent i6 = new Intent(this, AllContacts.class);
		startActivity(i6);
		break;
		case R.id.madd: Intent i1 = new Intent(this, AddContact.class);
		startActivity(i1);
		break;
		
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;

		}
		return true;
	}    
	 /*type :function
  	name:updateDisplay
  	return type:void          
	date:10-2-12
  	purpose:sets date to text view*/
	private void updateDisplay() 
	{
		// updates the date in the TextView

		mDate.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));
		sdate1=mDate.getText().toString();

	}
	 /*type :function
  	name:updateDisplay1
  	return type:void          
	date:10-2-12
  	purpose:sets time to text view*/
	private void updateDisplay1() {
		mTime.setText(new StringBuilder()
		.append(pad(mHour)).append(":")
		.append(pad(mMinute)));
		stime1=mTime.getText().toString();
	}
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
		
			updateDisplay();
		}

	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);


	}
	protected Dialog onCreateDialog(int id)
	{
		switch (id) 
		{
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					mDateSetListener,
					mYear, mMonth, mDay);

		case TIME_DIALOG_ID: return new TimePickerDialog(this,
				mTimeSetListener, mHour, mMinute, false);


		}
		return null;
	}
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay1();
		}
	};
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DialogforCreate.this,CallList.class);
		startActivity(i);
		return;
	}
	
}    



