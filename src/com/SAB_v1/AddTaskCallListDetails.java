//To add task
package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.SAB_v1.Import.Task;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;

import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

public class AddTaskCallListDetails extends Activity implements OnClickListener
{
	DataBaseHelper data;
	EditText edescp,loc;
	ArrayAdapter adapter,adapter2,adapter3,adapter4;
	Spinner s,sp;
	GMailSender m;
	String cid4;
	String name2,ans,name3,sn,taskname,email,pwd,ln,descrip;
	private TextView ename;
	private String array_spinner[];   
	private String array_spinner1[];
	String locname="";
	private Button ddone,dcancel;
	String sdate,ddate,stime1,descp,stime,fname,lname,id;
	StringBuilder sdate1;
	private RadioButton rb1,rb2,rb3;
	String[] s1;
	int cid,cid1;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 0;
	private int mHour;
	private int mMinute;
	int hour,minute;
	int flag;
	@Override
	public void onCreate(Bundle icicle)
	{

		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		setTitle("Add Tasks");
		setContentView(R.layout.add_task);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Bundle bundle = getIntent().getExtras(); 
		id=bundle.getString("name");
		System.out.println("My Names: "+id);
		cid=Integer.parseInt(id);
		Cursor c=data.getcontactname(cid);
		while(c.moveToNext())
		{
			fname=c.getString(0);
			lname=c.getString(1);
			System.out.println("Fname "+fname);
			System.out.println("Lname "+lname);
			name3=fname+" "+lname;
		}
		c.close();
		rb1=(RadioButton)findViewById(R.id.rhigh);
		rb2=(RadioButton)findViewById(R.id.rmed);
		rb3=(RadioButton)findViewById(R.id.rlow);
		ename=(TextView)findViewById(R.id.name);
		loc=(EditText) findViewById(R.id.loc);		
		ename.setText("Add Task For "+name3);
		edescp=(EditText)findViewById(R.id.descp);
		//gets current date and time
		final Calendar c1 = Calendar.getInstance();
		mYear = c1.get(Calendar.YEAR);
		mMonth = c1.get(Calendar.MONTH);
		mDay = c1.get(Calendar.DAY_OF_MONTH);
		mHour = c1.get(Calendar.HOUR_OF_DAY);
		mMinute = c1.get(Calendar.MINUTE);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);

		sdate=(+ year + "-" + (month+1)+ "-" + day); 
		stime=(+(hour)+":"+minute);
		array_spinner=new String[2];
		array_spinner[0]="Self";
		array_spinner[1]=name3;

		array_spinner1=new String[5];
		array_spinner1[0]="Select Option";
		array_spinner1[1]="Schedule Call";
		array_spinner1[2]="SMS";
		array_spinner1[3]="Schedule Meeting";
		array_spinner1[4]="Send E-mail";
		ddone=(Button)findViewById(R.id.ddone);
		dcancel=(Button)findViewById(R.id.dcancel);
		s= (Spinner) findViewById(R.id.actionby);
		adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
		s.setAdapter(adapter);
		sp= (Spinner) findViewById(R.id.action);
		adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner1);
		sp.setAdapter(adapter2);
		int spi=sp.getSelectedItemPosition();

		sp.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position>0)
						{
							taskname= (String) sp.getItemAtPosition(position);
							showDateTimeDialog();
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				}); 

		ddone.setOnClickListener(new OnClickListener() {
  
			public void onClick(View v)
			{
				cid4=""+cid;
				System.out.println("cid will...."+cid4);
				descrip=edescp.getText().toString();
				Cursor c9=data.getesettings();
				while(c9.moveToNext())
				{
					flag=c9.getInt(0);
					System.out.println("Flag will......."+flag);
				}
				c9.close();
				if(flag==1)
				{
					Cursor c=data.getAccDetails();
					while(c.moveToNext())
					{
						email=c.getString(0);
						pwd=c.getString(1);
						System.out.println("email"+email+"pwd"+pwd);
					}
					if(c.getCount()>0)
					{
						email_alert();
					}
					else
					{
						final AlertDialog.Builder alertbox1=new AlertDialog.Builder(AddTaskCallListDetails.this);
						
						// set the message to display
						alertbox1.setMessage("Account not created!! \n Press menu to create a new Account");
	 
	            // set a positive/yes button and create a listener
						alertbox1.setPositiveButton("OK", new DialogInterface.OnClickListener() 
						{
	 
	                // do something when the button is clicked
							public void onClick(DialogInterface arg0, int arg1)
							{
								data.Inserttask(name3,taskname,descrip,ans,sdate,ddate,stime,cid,locname);
		          			    Toast.makeText(AddTaskCallListDetails.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
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
		          		      Intent i = new Intent(AddTaskCallListDetails.this,DisplayNotification.class);
		          		      Bundle bun=new Bundle();                   
		          				bun.putString("task",taskname); 
		          				bun.putInt("NotifID",1);
		          	          //---assign an ID of 1---
		          	          i.putExtras(bun);                                

		          	          PendingIntent displayIntent = PendingIntent.getActivity(
		          	              getBaseContext(), 0, i, 0);         
		          		      alarmManager.set(AlarmManager.RTC_WAKEUP, 
		          	                  AlarmCal.getTimeInMillis(), displayIntent);
		                    	
		                    	 Intent i1 =new Intent(AddTaskCallListDetails.this,UpdateStatusCallListContactsDetails.class);
		                    	 Bundle bun1=new Bundle();
		     					bun1.putString("name",cid4);
		     					System.out.println("Cid will be "+cid4);
		     					i1.putExtras(bun1);
		     					startActivity(i1);
		                        
							}
						});
						alertbox1.show();
							
					
						
				    //email_alert();
					} 
					c.close();
				}
				else
				{
					//inserts tasks in database
					data.Inserttask(name3,taskname,descrip,ans,sdate,ddate,stime,cid,locname);
      			    Toast.makeText(AddTaskCallListDetails.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
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
      		      Intent i = new Intent(AddTaskCallListDetails.this,DisplayNotification.class);
      		      Bundle bun=new Bundle();                   
      				bun.putString("task",taskname); 
      				bun.putInt("NotifID",1);
      	          //---assign an ID of 1---
      	          i.putExtras(bun);                                

      	          PendingIntent displayIntent = PendingIntent.getActivity(
      	              getBaseContext(), 0, i, 0);         
      		      alarmManager.set(AlarmManager.RTC_WAKEUP, 
      	                  AlarmCal.getTimeInMillis(), displayIntent);
                	 Intent i1 =new Intent(AddTaskCallListDetails.this,UpdateStatusCallListContactsDetails.class);
                	 Bundle bun1=new Bundle();
 					bun1.putString("name",cid4);
 					System.out.println("cid1 will be................. "+cid4);
 					i1.putExtras(bun1);
 					startActivity(i1);
                   
				}
				
				}
			});
		
		dcancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{

				finish();
			} 

		});   

	}
	/*Type :Function
	return type:void
	date:29-6-11
	purpose:To show date time dialog*/
	private void showDateTimeDialog() {
		// Create the dialog
		final Dialog mDateTimeDialog = new Dialog(this);
		// Inflate the root layout
		final RelativeLayout mDateTimeDialogView = (RelativeLayout) getLayoutInflater().inflate(R.layout.date_time_dialog, null);
		// Grab widget instance
		final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);
		// Check is system is set to use 24h time (this doesn't seem to work as expected though)
		final String timeS = android.provider.Settings.System.getString(getContentResolver(), android.provider.Settings.System.TIME_12_24);
		final boolean is24h = !(timeS == null || timeS.equals("12"));

		// Update demo TextViews when the "OK" button is clicked 
		((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				((TextView) findViewById(R.id.Date)).setText(mDateTimePicker.get(Calendar.YEAR) + "/" + (mDateTimePicker.get(Calendar.MONTH)+1) + "/"
						+ mDateTimePicker.get(Calendar.DAY_OF_MONTH));
				ddate=mDateTimePicker.get(Calendar.DAY_OF_MONTH)+"-"+(mDateTimePicker.get(Calendar.MONTH)+1)+"-"+mDateTimePicker.get(Calendar.YEAR);
				System.out.println("Due Date "+ddate);
				if (mDateTimePicker.is24HourView()) {
					((TextView) findViewById(R.id.Time)).setText(mDateTimePicker.get(Calendar.HOUR_OF_DAY) + ":" + mDateTimePicker.get(Calendar.MINUTE));
					//stime1=mDateTimePicker.get(Calendar.HOUR_OF_DAY) + ":" + mDateTimePicker.get(Calendar.MINUTE);
					//System.out.println("Due time "+stime1);

				} else {
					((TextView) findViewById(R.id.Time)).setText(mDateTimePicker.get(Calendar.HOUR) + ":" + mDateTimePicker.get(Calendar.MINUTE) + " "
							+ (mDateTimePicker.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"));
					stime1=mDateTimePicker.get(Calendar.HOUR)+":" + mDateTimePicker.get(Calendar.MINUTE);
					System.out.println("Due time1 "+stime1);
				}
				//stime1=mDateTimePicker.get(Calendar.HOUR)+":" + mDateTimePicker.get(Calendar.MINUTE);
				mDateTimeDialog.dismiss();
			}
		});

		// Cancel the dialog when the "Cancel" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDateTimeDialog.cancel();
			}
		});

		// Reset Date and Time pickers when the "Reset" button is clicked
		((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDateTimePicker.reset();
			}
		});

		// Setup TimePicker
		mDateTimePicker.setIs24HourView(is24h);
		// No title on the dialog window
		mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Set the dialog content view
		mDateTimeDialog.setContentView(mDateTimeDialogView);
		// Display the dialog
		mDateTimeDialog.show();
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;
		
		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Sending Email", "Please Wait...", true);
			 dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
				//return flag1;
			sendmail();
			return null ;
			
			
		}
		protected void onProgressUpdate(String... progress) 
		{
			System.out.println("IN update");
		
	    }
		@Override
		protected void onPostExecute(Void unused) {
			
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");

		      try { 
		      
		 
		        if(m.send()) { 
		          Toast.makeText(AddTaskCallListDetails.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
		          
		          } else { 
		          Toast.makeText(AddTaskCallListDetails.this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
		        } 
		      } catch(Exception e) { 
		         
		        Log.e("MailApp", "Could not send email", e); 
		      } 
		   
		      data.Inserttask(name3,taskname,descrip,ans,sdate,ddate,stime,cid,locname);
		    
			Toast.makeText(AddTaskCallListDetails.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
			call();		
			 
		}

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
		case R.id.mimport: Intent i3 = new Intent(this, Import.class);
		startActivity(i3);
		break; 
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;
		case R.id.mdelete: Intent i5 = new Intent(this, DeleteContacts.class);
		startActivity(i5);
		break;
		}
		return true;
	
	} 
	/*Type :Function
	name:senmail
	return type:void
	date:29-6-11
	purpose:to send email as reminder*/
	public void sendmail() {
		   
			m = new GMailSender(email,pwd); 
			 String[] toArr = {email}; 
		      m.setTo(toArr); 
		      m.setFrom(email); 
		      m.setSubject(taskname); 
		      m.setBody("Hello,"+"\n This is email reminder of task "+taskname+" for "+name3+"\n"+descrip+"\n\n");

          }
		
	/*Type :Function
	name:email_alert
	return type:void
	date:29-6-11
	purpose:gives alert whether user wants to sent mail or not*/
	public void email_alert()
	{
		final AlertDialog.Builder alertbox=new AlertDialog.Builder(this);
		
						// set the message to display
                alertbox.setMessage("Do you want to send an email??");
     
                // set a positive/yes button and create a listener
                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
                {
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                    	Task task = new Task();
                		task.applicationContext = AddTaskCallListDetails.this;
                		task.execute();
                		
                    }
                });
                
                
     
                // set a negative/no button and create a listener
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) 
                    {
                    	descrip=edescp.getText().toString();
                    	System.out.println("Description "+descrip);
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
 						data.Inserttask(name3,taskname,descrip,ans,sdate,ddate,stime,cid,locname);
        				Toast.makeText(AddTaskCallListDetails.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
        			call();
                    }
                });
     
                // display box
	         alertbox.show();
	}
	/*Type :Function
	name:call
	return type:void
	date:29-6-11
	purpose:calls updatestatus class*/
	public void call()
	{
		Intent intent=new Intent(this,UpdateStatusCallListContactsDetails.class);
		Bundle bun=new Bundle();
		bun.putString("name",id);
		System.out.println("Task will .. "+id);
		intent.putExtras(bun);
		startActivity(intent);
	}
	@Override
	public void onBackPressed()
	{
		Intent intent=new Intent(this,UpdateStatusCallListContactsDetails.class);
		Bundle bun=new Bundle();
		bun.putString("name",id);
		System.out.println("Task will .. "+id);
		intent.putExtras(bun);
		startActivity(intent);
		return;
	}           
                
	}

