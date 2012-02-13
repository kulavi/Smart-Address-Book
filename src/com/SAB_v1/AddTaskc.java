package com.SAB_v1;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
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
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
public class AddTaskc extends MapActivity 
{	
	private List<Address> foundAdresses;
	DataBaseHelper db;
	private Calendar _calendar;
	private RadioButton rb1,rb2,rb3;
	private EditText mDateDisplay,mTimeDisplay;
	private int mHour,mMinute,mYear,mMonth,mDay;;
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private Button mPickDate,mPickTime,ddone,dcancel,map;
	static final int TIME_DIALOG_ID = 0;
	String date,time,pr,lname,addressInput;
	private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
	private static final long POINT_RADIUS = 3000; // in Meters
	private static final long PROX_ALERT_EXPIRATION = -1; 
	private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
	private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
	private static final String PROX_ALERT_INTENT = "com.javacodegeeks.android.lbs.ProximityAlert";
	int hr,lid;
	GeoPoint p;
	private Geocoder gc;
	private MapView myMap; 
	private double lat=0;
	private double lon=0;
	String day1,mnth,yr;
	LinearLayout layout;
	TextView t1,t2,tv;
	EditText ename,edesp,eloc;
	LayoutParams params;
	LinearLayout mainLayout;
	String sdate,sdate1;
	String tname,tdesp,ans,tm;
	static final int DATE_DIALOG_ID = 1;
	private static final NumberFormat nf = new DecimalFormat("##.########");
	private LocationManager locationManager;
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
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
		Bundle bundle = getIntent().getExtras(); 
		date = bundle.getString("date");
		String[]s=date.split("-");
		day1=s[0];
		mnth=s[1];
		yr=s[2];
		int mntc = getMonthAsNO(mnth);
		int yearc=Integer.parseInt(yr);
		int dayc=Integer.parseInt(day1);
		System.out.println("date bun"+date);       
		mDateDisplay = (EditText) findViewById(R.id.dateDisplay);
		mPickDate = (Button) findViewById(R.id.pickDate);
		ename=(EditText)findViewById(R.id.ename);
		edesp=(EditText)findViewById(R.id.edesp);
		eloc=(EditText)findViewById(R.id.eloc);
		map = (Button) findViewById(R.id.map);
		myMap = (MapView) findViewById(R.id.simpleGM_map);
		myMap.setVisibility(View.GONE);
		ddone=(Button)findViewById(R.id.ddone);
		dcancel=(Button)findViewById(R.id.dcancel);
		rb1=(RadioButton)findViewById(R.id.rhigh);
		rb2=(RadioButton)findViewById(R.id.rmed);
		rb3=(RadioButton)findViewById(R.id.rlow);
		//sdate=(+ (month + 1) + "-" + day + "-" + year);
		//sdate1=(+yearc+ "-" + (mntc+1)+ "-" + dayc);	
		sdate1=(+dayc+ "-" + (mntc+1)+ "-" + yearc);	
		System.out.println("month");
		_calendar = Calendar.getInstance(Locale.getDefault());



		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) 
			{
				showDialog(DATE_DIALOG_ID);
			}
		});

		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear =Integer.parseInt(yr);
		mMonth = getMonthAsNO(mnth);
		mDay = Integer.parseInt(day1);
		updateDisplay();

		// capture our View elements


		// add a click listener to the button
		mPickTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
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
				//views the map
				myMap.setVisibility(View.VISIBLE);
				lname=eloc.getText().toString();
				System.out.println("lnamee"+lname);
				addressInput=lname;
				try
				{
					
	                Geocoder gc1 = new Geocoder(getBaseContext(), Locale.getDefault());

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
							AddTaskc.this).setIcon(0).setTitle(
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
		
		// get the current time
		final Calendar c1 = Calendar.getInstance();
		mHour = c1.get(Calendar.HOUR_OF_DAY);
		mMinute = c1.get(Calendar.MINUTE);


		// display the current date
		updateDisplay1();
		final LayoutInflater inflater = (LayoutInflater)
		this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		

		ddone.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
	
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
						date=(+ (mDay) + "-" +(mMonth+1)+ "-" +mYear);
						System.out.println("date........."+date);                   
						time= mTimeDisplay.getText().toString();
						insertTask();
						 saveProximityAlertPoint(); 
						 Toast.makeText(AddTaskc.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
	          			
				}}
				
		});                         
			                 
	dcancel.setOnClickListener(new OnClickListener() {

		public void onClick(View v)
		{

			finish();
		} 

	});
	}
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
						AddTaskc.this).setIcon(0).setTitle(
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
	/*Type :Function
	name:getMonthsAsNo
	return type:void
	date:29-6-11
	purpose:compares months and gets in number format*/
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
	
	private void saveProximityAlertPoint() {
		//navigateToLocation((lat * 1000000), (lon * 1000000),myMap);
		addProximityAlert(lat,lon);
	}
	private void addProximityAlert(double latitude, double longitude) {
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
	/*Type :Function
	name:insertTask
	return type:void
	date:29-6-11
	purpose:inserts task to database*/
	protected void insertTask() 
	{
		
		String mnth=getMonthAsString(mMonth);
		date=(+ (mDay) + "-" +(mMonth+1)+ "-" +mYear);
		if(tdesp==null)
		{
			tdesp="";
		}
		sdate=(+mDay+ "-" + (mnth)+ "-" +mYear);	
		System.out.println("date new"+sdate+".......lname"+lname);
		db.Inserttask("self",tname,tdesp,ans,sdate1,date,time,-1,lname);
		 
		
		
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
	      Intent i = new Intent(AddTaskc.this,DisplayNotification.class);
	      Bundle bun=new Bundle();                   
			bun.putString("task",tname); 
			bun.putInt("NotifID",1);
          //---assign an ID of 1---
          i.putExtras(bun);                                

          PendingIntent displayIntent = PendingIntent.getActivity(
              getBaseContext(), 0, i, 0);         
	      alarmManager.set(AlarmManager.RTC_WAKEUP, 
                  AlarmCal.getTimeInMillis(), displayIntent);
	      Intent i1=new Intent(AddTaskc.this,ViewTaskByDate.class);
			Bundle b=new Bundle();
			b.putString("date",sdate1);  
			System.out.println("task Name........."+tname);
			i1.putExtras(b);
			startActivity(i1);
     	
		
	}
	/*Type :class
	name:ProximityIntentReceiver
	return type:class
	date:29-6-11
	purpose:to broadcast alerts*/
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
		
			 i.putExtras(bun); 
			NotificationManager notificationManager = 
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,i, 0);		
			Notification notification = createNotification();
			notification.setLatestEventInfo(context, "Task Alert!", "You have task "+tname+" at location "+lname, pendingIntent);
			int notify_id=2;
			notificationManager.notify(notify_id,notification);
			 
		      
		  
				
			                                      
		}
		/*Type :Function
		name:createNotification
		return type:void
		date:29-6-11
		purpose:creates notification*/
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
	/*Type :class
	name:MyLocationOverLay
	return type:class
	date:29-6-11
	purpose:to override google map class*/
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
	/*Type :Function
	name:getMonthsAsString
	return type:string
	date:29-6-11
	purpose: gets months as string*/
	private String getMonthAsString(int i)
	{
		return months[i];
	}
	/*Type :Function
	name:updateDisplay1
	return type:void
	date:29-6-11
	purpose:displays time and date*/
	private void updateDisplay1() {
		if(mHour>=12)
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
	/*Type :class
	name:MyLocationListener
	return type:class
	date:29-6-11
	purpose: overrides android class Location Listener*/
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
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute)
		{
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay1();
			
		      
		 
		}
	};
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
		Intent i=new Intent(AddTaskc.this,ViewTask.class);
		startActivity(i);
		return;
	}                
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}                          
	
}
