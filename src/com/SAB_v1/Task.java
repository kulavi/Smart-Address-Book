package com.SAB_v1;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import com.SAB_v1.AddTaskc.MyLocationListener;
import com.SAB_v1.AddTaskdate.MyLocationOverlay;
import com.SAB_v1.AddTaskdate.ProximityIntentReceiver;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Window;           
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;


public class Task extends MapActivity 
{
	DataBaseHelper db;
	private Calendar _calendar;
	TextView text1;
	private EditText mDateDisplay,mTimeDisplay;
	private int mHour,mMinute,mYear,mMonth,mDay;;
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private Button mPickDate,mPickTime,ddone,dcancel,map;
	static final int TIME_DIALOG_ID = 0;
	String date,time,pr,date1;
	int hr;
	private MapView myMap;
	String day1,mnth,yr,lname="",addressInput;
	LinearLayout layout;
	TextView t1,t2,tv;
	private RadioButton rb1,rb2,rb3;
	EditText ename,edesp,eloc;
	LayoutParams params;
	LinearLayout mainLayout;
	String sdate;
	GeoPoint p;
	private double lat=0;
	private double lon=0;
	private Geocoder gc;
	String tname,tdesp,ans,tm;
	static final int DATE_DIALOG_ID = 1;
	private List<Address> foundAdresses;
	private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
	private static final long POINT_RADIUS = 3000; // in Meters
	private static final long PROX_ALERT_EXPIRATION = -1; 
	private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
	private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
	private static final String PROX_ALERT_INTENT = "com.javacodegeeks.android.lbs.ProximityAlert";
	
	private LocationManager locationManager;
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

	private String array_spinner[];

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		 
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     setContentView(R.layout.dotask);
	     setTitle("ADD TASK");
	     db=new DataBaseHelper(this);
		/* First Tab Content */
		
		mTimeDisplay = (EditText) findViewById(R.id.timeDisplay);
		mPickTime = (Button) findViewById(R.id.pickTime);
		myMap = (MapView) findViewById(R.id.simpleGM_map);
		myMap.setVisibility(View.GONE);
		mDateDisplay = (EditText) findViewById(R.id.dateDisplay);
		mPickDate = (Button) findViewById(R.id.pickDate);
		map = (Button) findViewById(R.id.map);
		ddone=(Button)findViewById(R.id.ddone);
		ename=(EditText)findViewById(R.id.ename);
		edesp=(EditText)findViewById(R.id.edesp);
		rb1=(RadioButton)findViewById(R.id.rhigh);
		rb2=(RadioButton)findViewById(R.id.rmed);
		rb3=(RadioButton)findViewById(R.id.rlow);
		eloc=(EditText)findViewById(R.id.eloc);
		dcancel=(Button)findViewById(R.id.dcancel);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);         
		int day = cal.get(Calendar.DAY_OF_MONTH);
		//sdate=(+ (month + 1) + "-" + day + "-" + year);
		sdate=(+day+ "-" + (month+1)+ "-" +year);	
		System.out.println("month");
		_calendar = Calendar.getInstance(Locale.getDefault());



		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		updateDisplay();

		// capture our View elements


		// add a click listener to the button
		mPickTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		// get the current time
		final Calendar c1 = Calendar.getInstance();
		mHour = c1.get(Calendar.HOUR_OF_DAY);
		mMinute = c1.get(Calendar.MINUTE);


		// display the current date
		updateDisplay1();
		final LayoutInflater inflater = (LayoutInflater)
		this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		

		ddone.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tname=ename.getText().toString();
				tdesp=edesp.getText().toString();
				if(tname==null)
				{
					Toast t=Toast.makeText(getBaseContext(),"Please enter task name",Toast.LENGTH_LONG);
					t.show();
				}
				
				else
				{	
					 if(rb1.isChecked()==true)
						{
							ans="High";
							System.out.println("ans..."+ans);
							
						}
						if(rb2.isChecked()==true)
						{
							ans="Med";
							
						}
						if(rb3.isChecked()==true)
						{
							ans="Low";
						
						}              

						text1=(TextView)findViewById(R.id.text1);
						if(tname==null)
						{
							Toast t=Toast.makeText(getBaseContext(),"Please enter task name",Toast.LENGTH_LONG);
							t.show();
						}                                                                                                 
				System.out.println("tname:--"+tname);
				date=(+ (mDay) + "-" +(mMonth+1)+ "-" +mYear);
				System.out.println("date........."+date);
				time=mTimeDisplay.getText().toString();
				insertTask();
				 saveProximityAlertPoint(); 
				 Toast.makeText(Task.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
				 AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); 
       			
     		      int mon=mHour+12; 
     		      Calendar AlarmCal = Calendar.getInstance();
     		      AlarmCal.setTimeInMillis(System.currentTimeMillis());
     		      AlarmCal.set(Calendar.YEAR,mYear);
     		      AlarmCal.set(Calendar.MONTH,mMonth);
     		      AlarmCal.set(Calendar.DAY_OF_MONTH,mDay);   
     		      AlarmCal.set(Calendar.HOUR_OF_DAY,mon);  // set user selection
     		      AlarmCal.set(Calendar.MINUTE,mMinute);        // set user selection
     		      AlarmCal.set(Calendar.SECOND, 0);
     		      Intent i = new Intent(Task.this,DisplayNotification.class);
     		      Bundle bun=new Bundle();                   
     				bun.putString("task",tname); 
     				bun.putInt("NotifID",1);
     	          //---assign an ID of 1---
     	          i.putExtras(bun);                                

     	          PendingIntent displayIntent = PendingIntent.getActivity(
     	              getBaseContext(), 0, i, 0);         
     		      alarmManager.set(AlarmManager.RTC_WAKEUP, 
     	                  AlarmCal.getTimeInMillis(), displayIntent);
               	
				Intent i1=new Intent(Task.this,ViewTaskByDate1.class);
				Bundle bun1=new Bundle();
				bun1.putString("date",date);
				i1.putExtras(bun1);
				startActivity(i1);
      		    
			}}         
			});

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 
				MINIMUM_TIME_BETWEEN_UPDATE, 
				MINIMUM_DISTANCECHANGE_FOR_UPDATE,
				new MyLocationListener()
		);
		map.setOnClickListener(new View.OnClickListener()
		{
		
			public void onClick(View v) 
			{                      
				
				myMap.setVisibility(View.VISIBLE);
				lname=eloc.getText().toString();
				System.out.println("lnamee"+lname);
				addressInput=lname;
				try
				{
	                Geocoder gc1 = new Geocoder(
	                        getBaseContext(), Locale.getDefault());

					foundAdresses = gc1.getFromLocationName(addressInput,5);
					showAdressResults.sendEmptyMessage(0);
					
					Toast t1=Toast.makeText(getBaseContext(),"Location...."+foundAdresses,Toast.LENGTH_LONG);
					t1.show();
					System.out.println("faddress:-"+foundAdresses);
				}
				catch (IOException e) 
				{
					
					e.printStackTrace();
				}
				if (foundAdresses.size() == 0)
				{ // if no address found,
					// display an error
					Dialog locationError = new AlertDialog.Builder(
							Task.this).setIcon(0).setTitle(
							"Error").setPositiveButton(R.string.ok, null)
							.setMessage(
							"Sorry, your address doesn't exist.")
							.create();
					locationError.show();

				} else 
				{ // else display address on map
					for (int i = 0; i < foundAdresses.size(); ++i)
					{

						Address x = foundAdresses.get(i);
						lat =  (x.getLatitude() *100);
						lon = (float) x.getLongitude();
						System.out.println("Cmap:=lat:-"+lat+" lang:-"+lon);
					

					}
					navigateToLocation((lat * 1000000), (lon * 1000000),myMap);
				}
			/*JSONObject data=getLocationInfo(addressInput);
			getGeoPoint(data);*/
				}

			}                       
		);
		
	
	
	dcancel.setOnClickListener(new OnClickListener() {

		public void onClick(View v)
		{

			finish();
		} 

	});
	}
	private int getMonthAsNO(String mnth2)
	{
		int m=0;
		for(int i=0;i<months.length;i++)
		{
			if(months[i].equals(mnth2))
			{
				 m=i;
			}
		}
		return m;
	}
	/*
  	type :func
  	name:insertTask
  	param:none
	return type:void          
	date:29-06-2011
  	purpose:to save task in database                     
  */
	protected void insertTask() 
	{	
		
		if(tname.equals(""))
    	{
    		text1.setText("Call List Name not entered!!");
    	}
		
    	else
    	{
    		
    		Cursor c=db.checktname(tname,sdate);
    		int count=c.getCount();
    		if(count==0)
    		{                      
    			if(lname=="")
    			{
    				lname=eloc.getText().toString();	
    			}
    			System.out.println("date new"+sdate);
    			db.Inserttask("self",tname,tdesp,"low",sdate,date,time,-1,lname);
    			Toast t=Toast.makeText(getBaseContext(),"addedd Successfully",Toast.LENGTH_LONG);
    			t.show();
    			/*Intent newActivity1 = new Intent(Task.this,ViewTask.class);     
				startActivity(newActivity1);*/
    		}
    		else if(count>0)
			{
    			
    			opendialog();
			}
    		c.close();	
    	}
	
	
		
		
	
	
	}
	private void opendialog() 
	{
		
		final AlertDialog.Builder alertbox=new AlertDialog.Builder(this);
		db=new DataBaseHelper(this);
						// set the message to display
                alertbox.setMessage("Task Already Exists Do You want to continue");
     
                // set a positive/yes button and create a listener
                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {   
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                    	//n=ename.getText().toString();
                    	Cursor c1=db.checktname(tname,sdate);
                    	while(c1.moveToNext())
                    	{
                    		int id=c1.getInt(0);
                    		System.out.println("ID IS "+id);
                    		db.UpdateTask1(id,tname,tdesp, 1, "low", 0, sdate, date, 0, time,-1);
                    		Toast t=Toast.makeText(getBaseContext(),"Task overriden",Toast.LENGTH_LONG);
                    		t.show();
                    		Intent newActivity1 = new Intent(Task.this,ViewTask.class);     
            				startActivity(newActivity1);
                    	}
                    	c1.close();
                    	
                    
                    	
                    }
                });
     
                // set a negative/no button and create a listener
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) 
                    {
                    	Intent i= new Intent(Task.this,Task.class);
                    	startActivity(i);
                    }
                });
     
                // display box
                alertbox.show();
              }
	private void saveProximityAlertPoint() {
		//navigateToLocation((lat * 1000000), (lon * 1000000),myMap);
		addProximityAlert(lat,lon);
	}
	private void addProximityAlert(double latitude, double longitude) 
	{
		System.out.println("alert lat:"+latitude+" alert long:--"+longitude);
		Intent intent = new Intent(PROX_ALERT_INTENT);
		PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

		locationManager.addProximityAlert(
				latitude, // the latitude of the central point of the alert region
				longitude, // the longitude of the central point of the alert region
				POINT_RADIUS, // the radius of the central point of the alert region, in meters
				PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration 
				proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
		);
		IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);  
		registerReceiver(new ProximityIntentReceiver(), filter);

	}
	private String getMonthAsString(int i)
	{
		return months[i];
	}
	//time and date display
	private void updateDisplay1() {
		if(mHour>12)
		{
			mHour=(mHour-12);                                      
			tm="PM";
		}else
		{
			tm="AM";
		}
		mTimeDisplay.setText(new StringBuilder()
		.append(pad(mHour)).append(":")
		.append(pad(mMinute)).append(tm));

	}

	private static String pad(int c) {                 
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);


	}
	private void updateDisplay() 
	{
		// updates the date in the TextView

		mDateDisplay.setText
		(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mDay).append("-")
				.append(mMonth + 1).append("-")
				.append(mYear).append(" "));
		}
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) 
		{
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

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
		public void onTimeSet(TimePicker view, int hourOfDay, int minute)
		{
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay1();
			
		      
		 
		}
	};
	private Handler showAdressResults = new Handler() {
		@Override
		//shows the map for the address entered on map view
		public void handleMessage(Message msg) {
			//pd.dismiss();
			MyLocationOverlay myLocationOverlay = new MyLocationOverlay();
			List<Overlay> list = myMap.getOverlays();
			list.add(myLocationOverlay);
			myMap.setSatellite(false);
			myMap.setBuiltInZoomControls(true);
			myMap.displayZoomControls(true);
			if (foundAdresses.size() == 0) { // if no address found,
				// display an error
				Dialog locationError = new AlertDialog.Builder(
						Task.this).setIcon(0).setTitle(
								"Error").setPositiveButton(R.string.ok, null)
								.setMessage(
								"Sorry, your address doesn't exist.")
								.create();
				locationError.show();

			} else { // else display address on map
				for (int i = 0; i < foundAdresses.size(); ++i) {
					// Save results as Longitude and Latitude
					// @todo: if more than one result, then show a
					// select-list
					Address x = foundAdresses.get(i);
					lat = (float) x.getLatitude();
					lon = (float) x.getLongitude();
					System.out.println("lat:-"+lat+" lang:-"+lon);

				}

			
			} // display the found address
			navigateToLocation((lat * 1000000), (lon * 1000000),myMap);
		}
	};	
	public class ProximityIntentReceiver extends BroadcastReceiver 
	{
		//setting notification for proximity alert

		int tid; 

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		
		String key = LocationManager.KEY_PROXIMITY_ENTERING;

		Boolean entering = intent.getBooleanExtra(key, false);
		
		if (entering) {
			Log.d(getClass().getSimpleName(), "entering");
		}
		else {
			Log.d(getClass().getSimpleName(), "exiting");
		}
		 Cursor c=db.getTaskid(tname);
	        while(c.moveToNext())
	        {
	        	tid=c.getInt(0);
	        	System.out.println("Task id"+tid);
	        }
	        c.close();
		 Intent i = new Intent(context,Taskdetails.class);
        //---PendingIntent to launch activity if the user selects 
        // the notification---
      
        Bundle bun=new Bundle();                   
		bun.putInt("tid",tid); 
		bun.putInt("NotifID", 1);
		 i.putExtras(bun); 
		NotificationManager notificationManager = 
			(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,i, 0);		
		Notification notification = createNotification();
		notification.setLatestEventInfo(context, "Task Alert!", "You have task "+tname+" at location "+lname, pendingIntent);
		int notify_id=1;
		notificationManager.notify(notify_id,notification);
		 
	      
	  
			
		                                      
	}
	private Notification createNotification() 
	{
		Notification notification = new Notification();
		notification.icon = R.drawable.alert;
		notification.when = System.currentTimeMillis();
		
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.ledARGB = Color.WHITE;
		notification.ledOnMS = 1500;
		notification.ledOffMS = 1500;
		return notification;
	}
	}
	protected class MyLocationOverlay extends com.google.android.maps.Overlay
	{	//to display marker

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			Paint paint = new Paint();

			super.draw(canvas, mapView, shadow);
			// Converts lat/lng-Point to OUR coordinates on the screen.
			Point myScreenCoords = new Point();
			mapView.getProjection().toPixels(p,myScreenCoords);

			paint.setStrokeWidth(1);
			paint.setARGB(255, 255, 255, 255);
			paint.setStyle(Paint.Style.STROKE);
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
			canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
			canvas.drawText("I am here...", myScreenCoords.x, myScreenCoords.y, paint);
			return true;
		}
		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) 
		{   
			//---when user lifts his finger---
			if (event.getAction() == 1) {                
				p = mapView.getProjection().fromPixels(
						(int) event.getX(),
						(int) event.getY());

				Geocoder geoCoder = new Geocoder(
						getBaseContext(), Locale.getDefault());
				try {
					List<Address> addresses = geoCoder.getFromLocation(
							p.getLatitudeE6()  / 1E6, 
							p.getLongitudeE6() / 1E6, 1);

					String add = "";
					lat=p.getLatitudeE6()  / 1E6;
					lon=p.getLongitudeE6() / 1E6;
					System.out.println("finally lat:--"+lat+"long---"+lon);
					System.out.println("address new"+addresses);
					if (addresses.size() > 0) 
					{	
						for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); i++)
						{
							/*Address x = addresses.get(i);
							lat = (float) x.getLatitude();
							lon = (float) x.getLongitude();
							System.out.println(" finally got lat:-"+lat+" lang:-"+lon);*/
							add += addresses.get(0).getAddressLine(i) + "\n";
							lname=add;
						}
						}
					Toast.makeText(getBaseContext(),"new address"+add+"lattt lon...."+lat+lon, Toast.LENGTH_SHORT).show();
				}
				catch (IOException e) {                
					e.printStackTrace();
				}   
				return true;
			}
			else                
				return false;
		}        

	}
	public class MyLocationListener implements LocationListener 
	{
		public void onLocationChanged(Location location) 
		{
			Location pointLocation = retrievelocationFromPreferences();
			System.out.println(" pointlocation entered:---"+pointLocation+" location to go:--"+location);
			float distance = location.distanceTo(pointLocation);
			/*Toast.makeText(Gotask.this, 
					"Distance from Point:"+distance, Toast.LENGTH_LONG).show();*/
			System.out.println("distance:---"+distance);
		}
		public void onStatusChanged(String s, int i, Bundle b) {			
		}
		public void onProviderDisabled(String s) {
		}
		public void onProviderEnabled(String s) {			
		}
	}
	public void navigateToLocation(double latitude, double longitude, MapView mv) 
	{
		p = new GeoPoint((int) latitude, (int) longitude); // new
		// GeoPoint
		mv.displayZoomControls(true); // display Zoom (seems that it doesn't
		// work yet)
		MapController mc = mv.getController();
		mc.animateTo(p); // move map to the given point
		int zoomlevel = mv.getMaxZoomLevel(); // detect maximum zoom level
		mc.setZoom(zoomlevel - 1);
		mv.setSatellite(true); 
	}                                                  
	private Location retrievelocationFromPreferences() 
	{
		
		Location location = new Location("POINT_LOCATION");
    	location.setLatitude(lat);
    	location.setLongitude(lon);
    	System.out.println("location::----"+location);
    	return location;
		/*if (location==null) {
			Toast.makeText(this, "No last known location. Aborting...", Toast.LENGTH_LONG).show();
			return;
		}*/
	}
@Override
	public void onBackPressed()
	{
		Intent i=new Intent(Task.this,ViewTask.class);
		startActivity(i);
		return;
	}
	


	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}