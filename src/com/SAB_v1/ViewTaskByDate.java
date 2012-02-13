package com.SAB_v1;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import android.view.ViewGroup.LayoutParams;


public class ViewTaskByDate extends Activity 
{
	DataBaseHelper db;
	
	private int mYear,mMonth,mDay;;
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
	GridView grid_main,grid_main1;
	DataBaseHelper data;

	String tdate,ttime;
	int tid;
	EditText es;
	int cnt=0;
	String tn="";
	String tn1,mno;
	String id,fn,ln; 
	final static String LOG_TAG = "PocketMagic";  
            
	int increment;
	int maximum;                                                       
	ImageView iv1;
	int count;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	
	ArrayAdapter<String> adapter;
	
	String date,time,pr,date1;
	int hr;
	String day1,mnth,yr;
	LinearLayout layout;
	TextView t1,t2,tv;
	EditText ename,edesp;
	LayoutParams params;
	LinearLayout mainLayout;
	String sdate;
	String tname,tdesp,ans,tm;

	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		 
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 
	    /* First Tab Content */
		
		
		Bundle bundle = getIntent().getExtras(); 
		date1 = bundle.getString("date");
		/*String[]s=date1.split("-");
		day1=s[0];
		mnth=s[1];
		yr=s[2];*/
		System.out.println("date bun"+date1);
		final Calendar c = Calendar.getInstance();
		/*mYear =Integer.parseInt(yr);
		mMonth = getMonthAsNO(mnth);
		mDay = Integer.parseInt(day1);
		date=(+ (mDay) + "-" +(mMonth+1)+ "-" +mYear);*/
		data=new DataBaseHelper(this);          
		data.open();
		setContentView(R.layout.gridtask);
    	tv=(TextView)findViewById(R.id.txt);
    	tv.setVisibility(View.GONE);
		showtask();
		if(results.size()==0)   
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Tasks!!");
		}
         grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		es=(EditText)findViewById(R.id.esearch);    
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        data.close();
	}
	/*Type :Function
	name:showtask
	return type:void
	date:10-2-12
	purpose:to get tasks*/
	private void showtask()             
	{
		Cursor c=data.gettaskname1(date1);
		cnt=c.getCount();
		while (c.moveToNext())
		{	
			id=c.getString(0);
			tname=c.getString(1);
			
			tdesp=c.getString(2);
			ttime=c.getString(3);
			results4.add(tdesp);
			results.add(tname);
			results2.add(ttime);
			results1.add(tdate);
			results3.add(id);
		}
       c.close();
      
	}
	
	/*Type :Function
	name:getMonthsAsNO
	return type:void
	date:10-2-12
	purpose:to get months in number*/
	
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
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(ViewTaskByDate.this,ViewTask.class);
		startActivity(i);
		return;
	}

	public class ImageAdapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() {
			int cnt1=results.size();
			 
			return cnt1;
		} 
		/*Type :Function
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.iconlist, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position)+" ,"+results2.get(position)+"\n "+results4.get(position));
				
				System.out.println("name=="+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id=results3.get(pos);
						tid=Integer.parseInt(id);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(ViewTaskByDate.this,Taskdetails.class);
						Bundle bun=new Bundle();
						bun.putInt("tid",tid);
						i.putExtras(bun);
						startActivity(i);
					}
                                                
				});
			
				
			
			return v;
		}
		public Object getItem(int arg0) {
    
			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	
}
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.taskmenudate, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.delete:Intent i = new Intent(this,DeleteTask.class);
		Bundle bun=new Bundle();
		bun.putString("date",date1);
		i.putExtras(bun);
	    startActivity(i);
		break;
		case R.id.add:Intent i6 = new Intent(this,AddTaskdate.class);
		Bundle bun1=new Bundle();
		bun1.putString("date",date1);
		i6.putExtras(bun1);
	    startActivity(i6);
		break;
		
		}
		return true;
	}  
	
}	

